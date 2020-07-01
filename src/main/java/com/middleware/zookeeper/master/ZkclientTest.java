package com.middleware.zookeeper.master;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.*;
import org.apache.zookeeper.client.ZKClientConfig;

import java.io.IOException;

/**
 * 描述    :
 * Author :Qing_X
 * Date   :2019-12-10 15:10
 */
public class ZkclientTest implements Watcher {
    public static void main(String[] args) {
//        String zk = "192.168.247.132:2181";
//        int sto = 3000;
//        int cto = 30000;
//        ZkClient zkClient = new ZkClient(zk,sto, cto);
//        System.out.println("结束");

        String zkIp = "127.0.0.1:2180";
        int timeOut = 3000;
        String path = "/xiaqing";
        try {
            ZKClientConfig clientConfig = new ZKClientConfig();
            System.setProperty("zookeeper.sasl.client", "false");
            ZooKeeper zooKeeper = new ZooKeeper(zkIp, timeOut, new ZkclientTest());
            System.out.println(clientConfig.isSaslClientEnabled()
            );
//            zooKeeper.addAuthInfo("digest", "xiaqing:xiaqing".getBytes());
            zooKeeper.create(path, "xiaqing".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println();
            Thread.sleep(1000 * 30);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {

    }
}
