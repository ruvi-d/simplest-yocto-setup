CFLAGS  ?= -O2
# Use 'override' so our additions stick even when CFLAGS is supplied on
# the make command line (as OE/bitbake does via EXTRA_OEMAKE).
override CFLAGS  += -Wall -Wextra
LDLIBS  += -lhello

PROG := chello
OBJS := chello.o

DESTDIR ?=
prefix  ?= /usr
bindir  ?= $(prefix)/bin

all: $(PROG)

$(PROG): $(OBJS)
	$(CC) $(LDFLAGS) -o $@ $^ $(LDLIBS)

%.o: %.c
	$(CC) $(CFLAGS) $(CPPFLAGS) -c $< -o $@

install: $(PROG)
	install -d $(DESTDIR)$(bindir)
	install -m 0755 $(PROG) $(DESTDIR)$(bindir)/

clean:
	rm -f $(OBJS) $(PROG)

.PHONY: all install clean
