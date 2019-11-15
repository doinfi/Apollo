#!/bin/bash
# 脚本放在dubbo目录下
# usage:
# sh ./restart_all.sh

# 脚本默认放在/mnt/dubbo/dubbo_shell下
curPath=$(pwd)
dubbo_shell_path=$(cd `dirname $0`; pwd)
cd ${curPath}

sh ${dubbo_shell_path}/start.sh restart user
sh ${dubbo_shell_path}/start.sh restart data
sh ${dubbo_shell_path}/start.sh restart device
sh ${dubbo_shell_path}/start.sh restart rest
sh ${dubbo_shell_path}/start.sh restart profile
sh ${dubbo_shell_path}/start.sh restart crossRegion
sh ${dubbo_shell_path}/start.sh restart thirdparty
sh ${dubbo_shell_path}/start.sh restart dataParser