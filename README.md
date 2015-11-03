
Configure `hosts` inventory file

Configure `group_vars/all/secret.yml` variables file

Run ad-hoc command

    ansible -i hosts -m ping hostname
    ansible -i hosts -m setup hostname

Run the playbook

    ansible-playbook -i hosts l0kvmhosts.yml
    ansible-playbook -i hosts l1kvmhosts.yml
    ansible-playbook -i hosts upgrade.yml --limit develop1


## Install Root Server

TODO

## Install VM

    ansible-playbook -i hosts vms.yml --limit demo2

## Destroy VM

    ansible-playbook -i hosts destroyvm.yml --extra-vars "target=demo2"


