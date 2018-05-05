package com.cjj.learn.zookeeper.base;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZooKeeperWatcher {

	public static void main(String[] args) {

		ZooKeeper zk = null;
		try {

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

			System.out.println("开始连接ZooKeeper...");

			ZooKeeperConnect zkConn = new ZooKeeperConnect();
			zk = zkConn.connect();

			System.out.println("ZooKeeper连接创建成功！");

			Thread.sleep(1000);

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

			// 创建根目录节点
			// 路径为/tmp_root_path
			// 节点内容为字符串"我是根目录/tmp_root_path"
			// 创建模式为CreateMode.PERSISTENT
			System.out.println("开始创建根目录节点/tmp_root_path...");
			zk.create("/tmp_root_path", "我是根目录/tmp_root_path".getBytes(),
					Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("根目录节点/tmp_root_path创建成功！");

			Thread.sleep(1000);

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

			// 创建第一个子目录节点
			// 路径为/tmp_root_path/childPath1
			// 节点内容为字符串"我是第一个子目录/tmp_root_path/childPath1"
			// 创建模式为CreateMode.PERSISTENT
			System.out.println("开始创建第一个子目录节点/tmp_root_path/childPath1...");
			zk.create("/tmp_root_path/childPath1",
					"我是第一个子目录/tmp_root_path/childPath1".getBytes(),
					Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("第一个子目录节点/tmp_root_path/childPath1创建成功！");

			Thread.sleep(1000);

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

			Thread.sleep(1000);

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

			// 创建第二个子目录节点
			// 路径为/tmp_root_path/childPath2
			// 节点内容为字符串"我是第二个子目录/tmp_root_path/childPath2"
			// 创建模式为CreateMode.PERSISTENT
			System.out.println("开始创建第二个子目录节点/tmp_root_path/childPath2...");
			zk.create("/tmp_root_path/childPath2",
					"我是第二个子目录/tmp_root_path/childPath2".getBytes(),
					Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			System.out.println("第二个子目录节点/tmp_root_path/childPath2创建成功！");

			Thread.sleep(1000);

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

			// 获取第二个子目录节点/tmp_root_path/childPath2节点数据
			System.out.println("开始获取第二个子目录节点/tmp_root_path/childPath2节点数据...");
			System.out.println(new String(zk.getData(
					"/tmp_root_path/childPath2", true, null)));
			System.out.println("第二个子目录节点/tmp_root_path/childPath2节点数据获取成功！");

			Thread.sleep(1000);

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

			// 修改第一个子目录节点/tmp_root_path/childPath1数据
			System.out.println("开始修改第一个子目录节点/tmp_root_path/childPath1数据...");
			zk.setData("/tmp_root_path/childPath1",
					"我是修改数据后的第一个子目录/tmp_root_path/childPath1".getBytes(), -1);
			System.out.println("修改第一个子目录节点/tmp_root_path/childPath1数据成功！");

			Thread.sleep(1000);

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

			// 修改第二个子目录节点/tmp_root_path/childPath2数据
			System.out.println("开始修改第二个子目录节点/tmp_root_path/childPath2数据...");
			zk.setData("/tmp_root_path/childPath2",
					"我是修改数据后的第二个子目录/tmp_root_path/childPath2".getBytes(), -1);
			System.out.println("修改第二个子目录节点/tmp_root_path/childPath2数据成功！");

			Thread.sleep(1000);

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

			// 删除第一个子目录节点
			System.out.println("开始删除第一个子目录节点/tmp_root_path/childPath1...");
			zk.delete("/tmp_root_path/childPath1", -1);
			System.out.println("第一个子目录节点/tmp_root_path/childPath1删除成功！");

			Thread.sleep(1000);

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

			// 删除第二个子目录节点
			System.out.println("开始删除第二个子目录节点/tmp_root_path/childPath2...");
			zk.delete("/tmp_root_path/childPath2", -1);
			System.out.println("第二个子目录节点/tmp_root_path/childPath2删除成功！");

			Thread.sleep(1000);

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

			// 删除根目录节点
			System.out.println("开始删除根目录节点/tmp_root_path...");
			zk.delete("/tmp_root_path", -1);
			System.out.println("根目录节点/tmp_root_path删除成功！");

			Thread.sleep(1000);

			System.out.println("...");
			System.out.println("...");
			System.out.println("...");
			System.out.println("...");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			if (zk != null) {
				try {
					zk.close();
					System.out.println("释放ZooKeeper连接成功！");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
