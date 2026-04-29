// SPDX-License-Identifier: GPL-2.0-only
#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>

static int __init khello_init(void)
{
	pr_info("khello: Hello from KISS!\n");
	return 0;
}

static void __exit khello_exit(void)
{
	pr_info("khello: Goodbye from KISS!\n");
}

module_init(khello_init);
module_exit(khello_exit);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("KISS");
MODULE_DESCRIPTION("Hello world kernel module for the KISS distro");
MODULE_VERSION("0.1");
