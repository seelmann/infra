
Configure `hosts` inventory file

Configure `group_vars/all/secret.yml` variables file

Run ad-hoc command

    ansible -i hosts -m ping hostname
    ansible -i hosts -m setup hostname

Run the playbook

    ansible-playbook -i hosts rootservers.yml
    ansible-playbook -i hosts vms.yml
    ansible-playbook -i hosts upgrade.yml --limit develop1


