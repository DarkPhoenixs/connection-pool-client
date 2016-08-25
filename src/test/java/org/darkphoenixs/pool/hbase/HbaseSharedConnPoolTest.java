package org.darkphoenixs.pool.hbase;

import org.apache.hadoop.hbase.client.Connection;
import org.junit.Test;

public class HbaseSharedConnPoolTest {

    @Test
    public void test() throws Exception {

        HbaseSharedConnPool pool = HbaseSharedConnPool.getInstance(HbaseConfig.DEFAULT_HOST, HbaseConfig.DEFAULT_PORT, HbaseConfig.DEFAULT_MASTER, HbaseConfig.DEFAULT_ROOTDIR);

        try {

            pool = HbaseSharedConnPool.getInstance(null, HbaseConfig.DEFAULT_PORT, "localhost:60000", "hdfs://localhost:8020/hbase");

            pool = HbaseSharedConnPool.getInstance(HbaseConfig.DEFAULT_HOST, null, "localhost:60000", "hdfs://localhost:8020/hbase");

        } catch (Exception e) {

        }

        Connection conn = pool.getConnection();

        pool.returnConnection(conn);

        pool.returnConnection(null);

        pool.returnConnection(conn);

        pool.returnConnection(conn);

        pool.returnConnection(null);

    }
}