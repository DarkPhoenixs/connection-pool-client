package org.darkphoenixs.pool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.junit.Test;

public class PoolBaseTest {

	@Test
	public void test() throws Exception {

		ConnPool pool = new ConnPool(new PoolConfig(), new ConnFactory());
		
		pool.initPool(new PoolConfig(), new ConnFactory());

		pool.addObjects(1);
		
		Conn conn = pool.getResource();
		
		pool.returnResource(conn);
		
		pool.invalidateResource(conn);
		
		pool.getMaxBorrowWaitTimeMillis();
		
		pool.getMeanBorrowWaitTimeMillis();
		
		pool.getNumActive();
		
		pool.getNumIdle();
		
		pool.getNumWaiters();
		
		pool.isClosed();
		
		pool.clear();
		
		pool.destroy();
		
		pool.close();

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

		public ConnPool(final PoolConfig poolConfig, ConnFactory factory) {
			
			super(poolConfig, factory);
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
