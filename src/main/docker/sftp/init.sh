#!/bin/bash
# The mounted delivery volume is created by docker as root, give it to the sftp user
# so that deliveries can be uploaded (atmoz/sftp only chowns the directories it creates).
chown 185:185 /home/numahop/delivery

# The chroot (/home/numahop) must stay owned by root, so open the sessions in the delivery folder.
sed -i 's|^ForceCommand internal-sftp.*|ForceCommand internal-sftp -d /delivery|' /etc/ssh/sshd_config
