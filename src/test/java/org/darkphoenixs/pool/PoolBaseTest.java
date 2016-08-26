package org.darkphoenixs.pool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.junit.Test;

public class PoolBaseTest {

    @Test
    public void test_0() throws Exception {

        ConnPool pool = new ConnPool(new PoolConfig(), new ConnFactory());

        pool.initPool(new PoolConfig(), new ConnFactory());

        pool.addObjects(1);

        Conn conn = pool.getResource();

        pool.returnResource(conn);

        pool.invalidateResource(conn);

        System.out.println("MaxBorrowWaitTimeMillis " + pool.getMaxBorrowWaitTimeMillis());

        System.out.println("MeanBorrowWaitTimeMillis " + pool.getMeanBorrowWaitTimeMillis());

        System.out.println("NumActive " + pool.getNumActive());

        System.out.println("NumIdle " + pool.getNumIdle());

        System.out.println("NumWaiters " + pool.getNumWaiters());

        pool.isClosed();

        pool.clear();

        pool.destroy();

        pool.close();


        ConnPool2 pool2 = new ConnPool2(new PoolConfig(), new ConnFactory());

        try {
            pool.getMaxBorrowWaitTimeMillis();
        } catch (Exception e) {

        }
    }

    @Test
    public void test_1() throws Exception {

        ConnPool pool = new ConnPool();

        try {
            pool.addObjects(1);
        } catch (Exception e) {
        }

        try {
            pool.getResource();
        } catch (Exception e) {
        }

        try {
            pool.returnResource(new Conn());
        } catch (Exception e) {
        }

        try {
            pool.invalidateResource(new Conn());
        } catch (Exception e) {
        }

        try {
            pool.returnResource(null);
        } catch (Exception e) {
        }

        try {
            pool.invalidateResource(null);
        } catch (Exception e) {
        }

        try {
            System.out.println("MaxBorrowWaitTimeMillis " + pool.getMaxBorrowWaitTimeMillis());
        } catch (Exception e) {
        }

        try {
            System.out.println("MeanBorrowWaitTimeMillis " + pool.getMeanBorrowWaitTimeMillis());
        } catch (Exception e) {
        }

        try {
            System.out.println("NumActive " + pool.getNumActive());
        } catch (Exception e) {
        }

        try {
            System.out.println("NumIdle " + pool.getNumIdle());
        } catch (Exception e) {
        }

        try {
            System.out.println("NumWaiters " + pool.getNumWaiters());
        } catch (Exception e) {
        }

        try {
            pool.isClosed();
        } catch (Exception e) {
        }

        try {
            pool.clear();
        } catch (Exception e) {
        }

        try {
            pool.destroy();
        } catch (Exception e) {
        }

        try {
            pool.close();
        } catch (Exception e) {
        }

    }

    private static class ConnFactory implements ConnectionFactory<Conn> {

        private static final long serialVersionUID = 1L;

        @Override
        public PooledObject<Conn> makeObject() throws Exception {

            return new DefaultPooledObject<Conn>(createConnection());
        }

        @Override
        public void destroyObject(PooledObject<Conn> p) throws Exception {

            System.out.println("destroyObject " + p.getObject().getId());
        }

        @Override
        public boolean validateObject(PooledObject<Conn> p) {

            System.out.println("validateObject " + p.getObject().getId());

            return true;
        }

        @Override
        public void activateObject(PooledObject<Conn> p) throws Exception {

            System.out.println("activateObject " + p.getObject().getId());
        }

        @Override
        public void passivateObject(PooledObject<Conn> p) throws Exception {

            System.out.println("passivateObject " + p.getObject().getId());
        }

        @Override
        public Conn createConnection() throws Exception {

            Conn conn = new Conn();

            conn.setId(123);

            return conn;
        }
    }

    private static class ConnPool extends PoolBase<Conn> {

        private static final long serialVersionUID = 1L;

        public ConnPool() {
        }

        public ConnPool(final PoolConfig poolConfig, ConnFactory factory) {

            super(poolConfig, factory);
        }
    }

    private static class ConnPool2 extends PoolBase<Conn> {

        private static final long serialVersionUID = 1L;

        public ConnPool2(final PoolConfig poolConfig, ConnFactory factory) {

            super(poolConfig, factory);
        }

        @Override
        public boolean isClosed() {
            throw new RuntimeException();
        }
    }

    private static class Conn {

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
