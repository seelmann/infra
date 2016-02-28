Infra
=====

Configure `inventory` inventory file.

Configure `host_vars/*` host variables files.

Configure `group_vars/all/secret.yml` group variables file.


## Run ad-hoc commands

    ansible -m ping hostname
    ansible -m setup hostname
    ansible -m debug -a var=hostvars hostname


## Run playbooks

    alias ap=ansible-playbook
    ap plays/upgrade.yml
    ap plays/reboot-all.yml
    ap plays/open-luks.yml
    ap plays/start-vms.yml


## Install Root Server

    ap bootstrap/hetzner-install-rootserver.yml --limit hostname
    ap bootstrap/kvm-hosts.yml --limit hostname


## Install VM

    ap bootstrap/kvm-install-guest.yml --limit hostname
    ap bootstrap/lxc-hosts.yml --limit hostname


## Destroy VM

    ap bootstrap/kvm-delete-guest.yml --limit hostname


## Install LXC

    ap bootstrap/lxc-install-guest/yml --limit hostname


## Destroy LXC

    ap bootstrap/lxc-delete-guest/yml --limit hostname

