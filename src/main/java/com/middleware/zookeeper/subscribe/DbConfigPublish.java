package com.middleware.zookeeper.subscribe;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述    : 基于zookeeper发布/订阅
 * Author :Qing_X
 * Date   :2019-12-06 19:09
 */
public class DbConfigPublish {

    static Logger logger = LoggerFactory.getLogger(DbConfigPublish.class);

    static String db = "abc";

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Watcher watcher;
        String projectName =  "/sandbox";
        String node = "/dbconfig";
        String zkIp = "192.168.247.132:2181";
        int timeOut = 30000;
        ZkClient zkClient = new ZkClient(zkIp,timeOut);
        logger.info("连接成功");
        String nodePath = StringUtils.join(projectName, node);
        boolean exists = zkClient.exists(nodePath);
        if(!exists){
            zkClient.createPersistent(nodePath, true);
            zkClient.writeData(nodePath, db);
        }

        zkClient.subscribeDataChanges(nodePath, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                count++;
                logger.warn(dataPath+ " 数据变更："+data+" , time: "+count);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                logger.warn(dataPath+ " 数据节点删除：");
            }
        });




        Thread.sleep(1000*60*60);
    }
}
