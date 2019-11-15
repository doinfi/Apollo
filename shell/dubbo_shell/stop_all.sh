#!/bin/bash
# 脚本放在dubbo目录下
# usage:
# sh ./stop_all.sh

# 脚本默认放在/mnt/dubbo/dubbo_shell下
curPath=$(pwd)
dubbo_shell_path=$(cd `dirname $0`; pwd)
cd ${curPath}

sh ${dubbo_shell_path}/start.sh stop user
sh ${dubbo_shell_path}/start.sh stop data
sh ${dubbo_shell_path}/start.sh stop device
sh ${dubbo_shell_path}/start.sh stop rest
sh ${dubbo_shell_path}/start.sh stop profile
sh ${dubbo_shell_path}/start.sh stop crossRegion
sh ${dubbo_shell_path}/start.sh stop thirdparty