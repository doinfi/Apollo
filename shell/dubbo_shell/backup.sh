#!/bin/bash
# 脚本放在dubbo目录下
# usage:
# backup.sh 20171208195600

echo "begin backup ..."

# 脚本默认放在/mnt/dubbo/dubbo_shell下，因此要cd ..
curPath=$(pwd)
instance=$(basename $(cd `dirname $0`; cd ..; pwd))
cd ${curPath}
echo "instance=${instance}"
version=$1
echo "version=" ${version}
if [ "${version}" = "" ]; then
   echo "please input version!"
   exit 1
fi

#1 分实例分微服务全量备份（每个微服务在50M左右），备份整个微服务，方便回退，不涉及变更的微服务不备份
#2 备份目录：/data/backup/dubbo/20171207103000/微服务
#20171207103000: 表示变更开始的时间戳，为区分一天有多次变更的情况；
#3 命令如下：

echo "mkdir -p /data/backup/${instance}/${version}"
mkdir -p /data/backup/${instance}/${version}
echo "cp -r /mnt/${instance}/ /data/backup/${instance}/${version}/"
cp -r /mnt/${instance}/ /data/backup/${instance}/${version}/

echo "end backup ..."



