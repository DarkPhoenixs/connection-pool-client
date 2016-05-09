package org.darkphoenixs.pool;

import org.junit.Test;

public class ConnectionPoolTest {


	@Test
	public void test() throws Exception {
		
		ConnPool pool = new ConnPool();
		
		Conn conn = pool.getConnection();
		
		pool.returnConnection(conn);
		
		pool.invalidateConnection(conn);
	}
	
	private static class ConnPool implements ConnectionPool<Conn> {

		/** serialVersionUID */
		private static final long serialVersionUID = 1L;

		@Override
		public Conn getConnection() {
			
			Conn conn = new Conn();
			
			conn.setId(123);
			
			return conn;
		}
		@Override
		public void returnConnection(Conn conn) {
			
			System.out.println("returnConnection " + conn.getId());
		}

		@Override
		public void invalidateConnection(Conn conn) {
			
			System.out.println("invalidateConnection " + conn.getId());
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
