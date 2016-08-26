package org.darkphoenixs.pool.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

public class HbaseSharedConnPoolTest {

    @Test
    public void test() throws Exception {

        HbaseSharedConnPool pool = HbaseSharedConnPool.getInstance(HbaseConfig.DEFAULT_HOST, HbaseConfig.DEFAULT_PORT, HbaseConfig.DEFAULT_MASTER, HbaseConfig.DEFAULT_ROOTDIR);

        try {
            pool = HbaseSharedConnPool.getInstance(HbaseConfig.DEFAULT_HOST, HbaseConfig.DEFAULT_PORT, "localhost:60000", "hdfs://localhost:8020/hbase");

        } catch (Exception e) {

        }

        try {
            pool = HbaseSharedConnPool.getInstance(null, HbaseConfig.DEFAULT_PORT, "localhost:60000", "hdfs://localhost:8020/hbase");

        } catch (Exception e) {

        }

        try {
            pool = HbaseSharedConnPool.getInstance(HbaseConfig.DEFAULT_HOST, null, "localhost:60000", "hdfs://localhost:8020/hbase");

        } catch (Exception e) {

        }

        Connection conn = pool.getConnection();

        conn = new Connection() {
            @Override
            public Configuration getConfiguration() {
                return null;
            }

            @Override
            public Table getTable(TableName tableName) throws IOException {
                return null;
            }

            @Override
            public Table getTable(TableName tableName, ExecutorService pool) throws IOException {
                return null;
            }

            @Override
            public BufferedMutator getBufferedMutator(TableName tableName) throws IOException {
                return null;
            }

            @Override
            public BufferedMutator getBufferedMutator(BufferedMutatorParams params) throws IOException {
                return null;
            }

            @Override
            public RegionLocator getRegionLocator(TableName tableName) throws IOException {
                return null;
            }

            @Override
            public Admin getAdmin() throws IOException {
                return null;
            }

            @Override
            public void close() throws IOException {

                throw new IOException();
            }

            @Override
            public boolean isClosed() {
                return false;
            }

            @Override
            public void abort(String why, Throwable e) {

            }

            @Override
            public boolean isAborted() {
                return false;
            }
        };

        pool.returnConnection(conn);

        pool.returnConnection(null);

        pool.invalidateConnection(conn);

        pool.invalidateConnection(new Connection() {
            @Override
            public Configuration getConfiguration() {
                return null;
            }

            @Override
            public Table getTable(TableName tableName) throws IOException {
                return null;
            }

            @Override
            public Table getTable(TableName tableName, ExecutorService pool) throws IOException {
                return null;
            }

            @Override
            public BufferedMutator getBufferedMutator(TableName tableName) throws IOException {
                return null;
            }

            @Override
            public BufferedMutator getBufferedMutator(BufferedMutatorParams params) throws IOException {
                return null;
            }

            @Override
            public RegionLocator getRegionLocator(TableName tableName) throws IOException {
                return null;
            }

            @Override
            public Admin getAdmin() throws IOException {
                return null;
            }

            @Override
            public void close() throws IOException {
            }

            @Override
            public boolean isClosed() {
                return false;
            }

            @Override
            public void abort(String why, Throwable e) {

            }

            @Override
            public boolean isAborted() {
                return false;
            }
        });

        pool.invalidateConnection(null);

        pool.close();

    }

}