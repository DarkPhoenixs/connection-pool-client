package org.darkphoenixs.pool.hbase;

import org.junit.Test;

public class HbaseTest {

    @Test
    public void test() throws Exception {

//		PoolConfig config = new PoolConfig();
//		config.setMaxTotal(20);
//		config.setMaxIdle(5);
//		config.setMaxWaitMillis(1000);
//		config.setTestOnBorrow(true);
//
//		Configuration hbaseConfig = new Configuration();
//		hbaseConfig.set("hbase.zookeeper.quorum", "localhost");
//		hbaseConfig.set("hbase.zookeeper.property.clientPort", "2181");
//		hbaseConfig.set("hbase.master", "localhost:60000");
//		hbaseConfig.set("hbase.rootdir", "hdfs://localhost:9000/hbase");
//
//		HbaseConnectionPool pool = new HbaseConnectionPool(config, hbaseConfig);
//
//		for (int i = 0; i < 1000; i++) {
//
//			Connection conn = pool.getConnection();
//
//			Table table = conn.getTable(TableName.valueOf("TableTest"));
//
//			Put put = new Put(Bytes.toBytes("rowkey" + i));
//
//			put.addColumn(Bytes.toBytes("family"), Bytes.toBytes("qualifier"),
//					Bytes.toBytes("value" + i));
//
//			table.put(put);
//
//			table.close();
//
//			pool.returnConnection(conn);
//		}
//
//		pool.close();

    }
}
