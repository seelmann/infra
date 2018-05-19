Infra
=====

Configure `inventory` inventory file.

Configure `host_vars/*` host variables files.

Configure `group_vars/all/secret.yml` group variables file.


## Run ad-hoc commands

    ansible -m ping hostname
    ansible -m setup hostname
    ansible -m debug -a var=hostvars hostname

## Define alias

    alias ap=ansible-playbook

## Install servers (LXC host, IPv4 GW)

    ap bootstrap/hcloud-install-server.yml --limit hcX
    ap bootstrap/lxc-hosts.yml --limit hcX
    ap plays/ipv4gw.yml

# Install LXC guest

    ap bootstrap/lxc-install-guest.yml --limit hcXguestY

## Install services

    ap plays/ldap-servers.yml
    ap plays/jenkins-servers.yml
    ap plays/nextcloud-servers.yml
    ap plays/mail-servers.yml
    ap plays/website-servers.yml

## Integrate into shared infrastructure

    ap plays/backup.yml
    ap plays/munin.yml
    ap plays/ipv4gw.yml --limit hcX

## Renew Let's Encrypt certificates

    ap plays/letsencrypt.yml --limit hcXguestY
    ap plays/ipv4gw.yml --limit hcX

## Upgrade packages and restart

    ap plays/upgrade --limit hcX-all
    ap plays/reboot-hcloudserver.yml --limit hcX

