# Trivial library Makefile that can build libhello either as a shared
# library (default) or as a static archive, selected by LIBTYPE.

NAME    := hello
SOVER   := 1
LIBTYPE ?= shared

CFLAGS  ?= -O2
# Use 'override' so flags we need stick even when CFLAGS is supplied on the
# make command line (as OE/bitbake does via EXTRA_OEMAKE).
override CFLAGS  += -Wall -Wextra -fPIC
override CPPFLAGS += -I.

SHARED_LIB    := lib$(NAME).so
SHARED_SONAME := lib$(NAME).so.$(SOVER)
STATIC_LIB    := lib$(NAME).a

OBJS := hello.o

ifeq ($(LIBTYPE),static)
TARGETS := $(STATIC_LIB)
else ifeq ($(LIBTYPE),shared)
TARGETS := $(SHARED_SONAME)
else
$(error LIBTYPE must be either 'shared' or 'static', got '$(LIBTYPE)')
endif

DESTDIR    ?=
prefix     ?= /usr
libdir     ?= $(prefix)/lib
includedir ?= $(prefix)/include

all: $(TARGETS)

$(SHARED_SONAME): $(OBJS)
	$(CC) $(LDFLAGS) -shared -Wl,-soname,$(SHARED_SONAME) -o $@ $^

$(STATIC_LIB): $(OBJS)
	$(AR) rcs $@ $^

%.o: %.c hello.h
	$(CC) $(CFLAGS) $(CPPFLAGS) -c $< -o $@

install: all
	install -d $(DESTDIR)$(includedir)
	install -m 0644 hello.h $(DESTDIR)$(includedir)/
	install -d $(DESTDIR)$(libdir)
ifeq ($(LIBTYPE),static)
	install -m 0644 $(STATIC_LIB) $(DESTDIR)$(libdir)/
else
	install -m 0755 $(SHARED_SONAME) $(DESTDIR)$(libdir)/
	ln -sf $(SHARED_SONAME) $(DESTDIR)$(libdir)/$(SHARED_LIB)
endif

clean:
	rm -f $(OBJS) $(SHARED_SONAME) $(STATIC_LIB)

.PHONY: all install clean
