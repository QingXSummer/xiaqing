package com.middleware.zookeeper.master;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;

/**
 * 描述    : 基于zookeeper的master选举
 * Author :Qing_X
 * Date   :2019-12-07 22:23
 */
public class MasterElection_delete {

    Logger logger = LoggerFactory.getLogger(MasterElection_delete.class);
    String path = "/master/elect/binding1";
    String node = "1";

    String zk = "192.168.247.132:2181";
    int sto = 3000;
    int cto = 3000;
    ZkClient zkClient = new ZkClient(zk,sto, cto);

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("zookeeper.sasl.client", "false");
        MasterElection_delete delete = new MasterElection_delete();
        delete.zkClient.subscribeDataChanges("/asdas", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("123");
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("456");
            }
        });


        Thread.sleep(1000*60*5);
    }




}
