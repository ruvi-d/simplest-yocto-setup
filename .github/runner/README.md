# Self-hosted GitHub Actions runner

Long-lived container that registers itself as a self-hosted runner with the
`yocto-build` label, then runs the build jobs defined in
`.github/workflows/build.yml`. The image extends `crops/yocto:ubuntu-22.04-base`
and adds `kas`, `oelint-adv`, and the GitHub `actions/runner` binary —
matching the `.devcontainer/dev/` toolchain so CI builds behave the same as
local devcontainer builds.

## One-time setup

1. **Create the host directories:**

   ```sh
   mkdir -p ~/yocto-caches ~/yocto-runner-work
   ```

   - `~/yocto-caches` is **shared** with the local devcontainer (mounted to
     `/workspaces/caches` — `DL_DIR` and `SSTATE_DIR` per
     `.config.devcontainer.yaml`). A CI build seeds the cache for a later
     local build and vice versa.
   - `~/yocto-runner-work` is **CI-only** — the runner workspace and build
     dirs live here. The build workflow cleans this dir at the end of every
     job, so it should stay small (a few hundred MB at most).

2. **Get a registration token** from the repo on GitHub:
   `Settings → Actions → Runners → New self-hosted runner`. Tokens expire
   after about an hour — register straight after copying.

3. **Create `.env`** from the template and paste the token in:

   ```sh
   cp .env.example .env
   $EDITOR .env
   ```

4. **Build and start the runner:**

   ```sh
   docker compose up -d --build
   docker compose logs -f yocto-runner
   ```

   You should see `Connected to GitHub` followed by `Listening for Jobs`. The
   runner appears as **Idle** in the repo's runner list.

## Day-to-day operations

- **Check status:** `docker compose logs -f yocto-runner`
- **Rebuild the image** (e.g. after editing the Dockerfile or bumping the
  runner version): `docker compose up -d --build`
- **Stop the runner:** `docker compose stop`
- **Reset a stuck workspace** (rare — the per-job cleanup should make this
  unnecessary): `docker compose down && rm -rf ~/yocto-runner-work/* && docker compose up -d`
- **Deregister the runner:** stop the container, remove the runner from
  GitHub's UI, then `docker compose down -v` and delete the `.runner` /
  `.credentials` files inside the named runner work dir if you want to
  re-register from scratch.

## Why a single runner

The build workflow has no `concurrency:` block — instead, queue
serialization comes from registering exactly **one** runner with the
`yocto-build` label. Multiple jobs (the four-machine matrix) queue up and
execute one at a time. Don't register a second runner with the same label
unless you intend to allow parallel builds.
