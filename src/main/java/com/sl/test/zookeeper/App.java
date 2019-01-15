package com.sl.test.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] agrs) throws IOException, KeeperException, InterruptedException {

        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183",20000,new ZkWatcher());
        String node = "/node2";
        Stat stat = zooKeeper.exists(node, false);
        List<String> children = zooKeeper.getChildren(node, false);
//        zooKeeper.setData(node,"say dear sandylee to sandylee".getBytes(),stat.getVersion());
//        ZooKeeper.States stat = zooKeeper.getState();

        byte[] datas = zooKeeper.getData(node,new ZkWatcher(),stat);
        System.out.println("data:"+new String(datas));
     //   String result = zooKeeper.create(node,"test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.close();
    }
}
class ZkWatcher implements Watcher{

    public void process(WatchedEvent watchedEvent) {
        System.out.println("path:"+watchedEvent.getPath());
        System.out.println("type:"+ watchedEvent.getType());
    }
}
