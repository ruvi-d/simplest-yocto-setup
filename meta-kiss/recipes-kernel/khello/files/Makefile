obj-m += khello.o
SRC := $(shell pwd)

all default: modules

install: modules_install

modules modules_install help clean:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) $@
