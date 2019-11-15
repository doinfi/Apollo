[root@iZm5e88jr3twpzdn0jcd2vZ:/mnt/dubbo]#diff  start.sh start.sh.220180110
14c14
< log_dir=/data/log/dubbo/${microServiceName}
---
> log_dir=${home_dir}/${microServiceName}



[root@iZm5e88jr3twpzdn0jcd2vZ:/mnt/dubbo2]#diff  start.sh start.sh.220180110
14c14
< log_dir=/data/log/dubbo2/${microServiceName}
---
> log_dir=${home_dir}/${microServiceName}

脚本要放到/mnt/dubbo/dubbo_shell下