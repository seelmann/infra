

1. Start rescue system from robot and restart the server

2. Make sure key-based ssh login to the rescue system works

    SERVER_IP=<server IP>
    ssh-keygen -R $SERVER_IP
    ssh-copy-id -i ~/.ssh/id_rsa root@$SERVER_IP
    ssh root@$SERVER_IP

3. Configure and test ansible connection

    ansible all -i "$SERVER_IP," -u root -m ping

4. Run the playbook

    ansible-playbook -i "$SERVER_IP," -u root install.yml



