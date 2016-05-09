package org.darkphoenixs.pool.socket;

import java.io.PrintWriter;
import java.net.Socket;

import org.darkphoenixs.pool.PoolConfig;
import org.darkphoenixs.pool.socket.SocketConnectionPool;
import org.junit.Test;

public class SocketTest {

	@Test
	public void test() throws Exception {

//		PoolConfig config = new PoolConfig();
//		config.setMaxTotal(20);
//		config.setMaxIdle(5);
//		config.setMaxWaitMillis(1000);
//		config.setTestOnBorrow(true);
//
//		SocketConnectionPool pool = new SocketConnectionPool(config,
//				"localhost", 1234);
//
//		Socket conn = pool.getConnection();
//
//		PrintWriter out = new PrintWriter(conn.getOutputStream());
//
//		out.write("哈哈");
//
//		out.flush();
//
//		out.close();
//
//		pool.returnConnection(conn);
//
//		pool.close();
	}
}
