#!/bin/bash
# 脚本放在dubbo目录下
# usage:
# 如果文件格式有问题，先执行dos2unix start.sh，修改文件格式
# sh ./start.sh start rest/data/user/device
# sh ./start.sh stop rest/data/user/device
# sh ./start.sh restart rest/data/user/device

now=$(date +%Y%m%d)
#进入jar目录
microServiceName=$2

curPath=$(pwd)
dubbo_home_dir=$(cd `dirname $0`; cd ..; pwd)
cd ${curPath}

command_for_search=" ${dubbo_home_dir}/${microServiceName}/coros-${microServiceName}-2.0.jar"
log_dir=/data/log/dubbo/${microServiceName}

# rest/user/data/open 测试环境内存不够，使用512内存启动就可以
jvm_cmd_param_high="java -Xms256m -Xmx512m -javaagent:/root/ArmsAgent_20190102/ArmsAgent/arms-bootstrap-1.7.0-SNAPSHOT.jar -Darms.licenseKey=cdwp4@0f8cd66f7806451 -Darms.appId=cdwp4@58bf61b3c1c8870 -jar "
# other
jvm_cmd_param_normal="java -Xms128m -Xmx256m -javaagent:/root/ArmsAgent_20190102/ArmsAgent/arms-bootstrap-1.7.0-SNAPSHOT.jar -Darms.licenseKey=cdwp4@0f8cd66f7806451 -Darms.appId=cdwp4@58bf61b3c1c8870 -jar "

jvm_cmd=${jvm_cmd_param_normal}

if [ "${microServiceName}" == "rest" -o "${microServiceName}" == "user" -o "${microServiceName}" == "thirdparty" -o "${microServiceName}" == "data" ]
then
  jvm_cmd=${jvm_cmd_param_high}
fi

# jvm崩溃日志放到日志路径下 -XX:ErrorFile
command="${jvm_cmd} ${command_for_search} -XX:ErrorFile=${log_dir}/hs_err_pid_%p.log"
mkdir -p ${log_dir}
log_file_url="${log_dir}/catalina.${now}.out"

start(){
    # 不输出控制台日志，在业务debug*.log里已经有了
    exec ${command} > /dev/null &
}


stop(){
 ps -ef | grep "${command_for_search}" | grep -v "grep" | awk '{print $2}' | while read pid
 do
    C_PID=$(ps --no-heading ${pid} | wc -l)
    echo "current PID=${pid}"
    if [ "${C_PID}" == "1" ]; then
        echo "PID=${pid} killing"
        kill -9 ${pid}
        echo "PID=${pid} killed"
    else
        echo "PID=${pid} not exist"
    fi
 done
}


case "$1" in
start)
start
;;
stop)
stop
;;
restart)
stop
start
;;
*)
printf 'Usage: %s {start|stop|restart}\n' "${prog}"
exit 1
;;
esac
