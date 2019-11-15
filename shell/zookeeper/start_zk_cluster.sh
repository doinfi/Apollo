#!/bin/bash
cd /mnt/zookeeper-cluster/zookeeper-3.4.10-1/bin; ./zkServer.sh start
cd /mnt/zookeeper-cluster/zookeeper-3.4.10-2/bin; ./zkServer.sh start
cd /mnt/zookeeper-cluster/zookeeper-3.4.10-3/bin; ./zkServer.sh start