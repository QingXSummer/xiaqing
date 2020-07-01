package com.middleware.zookeeper.subscribe;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述    : 基于zookeeper发布/订阅
 * Author :Qing_X
 * Date   :2019-12-06 19:09
 */
public class DbConfigPublishChange {

    static Logger logger = LoggerFactory.getLogger(DbConfigPublishChange.class);

    static String db = "12345";

    public static void main(String[] args) throws InterruptedException {
        String projectName =  "/sandbox";
        String node = "/dbconfig";
        String zkIp = "192.168.247.132:2181";
        int timeOut = 50000;
        ZkClient zkClient = new ZkClient(zkIp,timeOut);
        logger.info("连接成功");
        String nodePath = StringUtils.join(projectName, node);
        boolean exists = zkClient.exists(nodePath);
        if(exists){
//            zkClient.createPersistent(nodePath, true);
            for (int i = 0;i<100;i++){
                zkClient.writeData(nodePath, i+"");
            }
        }

    }
}
