package com.middleware.zookeeper.master;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述    : 基于zookeeper的master选举
 * Author :Qing_X
 * Date   :2019-12-07 22:23
 */
public class MasterElection_3 {

    Logger logger = LoggerFactory.getLogger(MasterElection_3.class);

    String path = "/master/elect/binding";

    String node = "3";

    String zk = "192.168.247.132:2181";
    int sto = 30000;
    int cto = 30000;
    ZkClient zkClient = null;
    {
        zkClient = new ZkClient(zk,sto, cto);
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("变换");

            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("shanchu");

                masterElec();
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("zookeeper.sasl.client", "false");
        new MasterElection_3().masterElec();
        Thread.sleep(1000*60*5);
    }


    public void masterElec() {
        boolean exists = zkClient.exists(path);
        if (!exists) {
            try {
                zkClient.createEphemeral(path,node);
                logger.info("节点{}成为主节点",node);
                return;
            } catch (ZkNodeExistsException e) {
                e.printStackTrace();
            }
        }
        String _node = zkClient.readData(path, true);
        if (path == null) {
            masterElec();
        } else if (!node.equals(_node)) {

        }

    }


}
