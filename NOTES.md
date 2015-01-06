
Delete VM

    vm=demo1
    virsh destroy $vm
    virsh undefine $vm
    virsh vol-wipe --pool vg0 --algorithm zero $vm.img
    virsh vol-delete --pool vg0 $vm.img

Remove SSH key from `~/.ssh/known_hosts`

    ssh-keygen -R <hostname or IP>
