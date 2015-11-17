package org.darkphoenixs.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.darkphoenixs.pool.jdbc.JdbcConnectionPool;
import org.junit.Test;

public class JdbcTest {

	@Test
	public void test() throws Exception {
		
		PoolConfig config = new PoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);
		
		Properties props = new Properties();
		props.setProperty("driverClass", "oracle.jdbc.OracleDriver");
		props.setProperty("jdbcUrl", "jdbc:oracle:thin:@localhost:1521:orcl");
		props.setProperty("username", "root");
		props.setProperty("password", "root");
		
		JdbcConnectionPool pool = new JdbcConnectionPool(config, props);
		
		Connection conn = pool.getConnection();
		
		Statement stat = conn.createStatement();
		
		ResultSet rs = stat.executeQuery("select ID from T_TEST");
		
		while(rs.next()) {
			
			rs.getInt("ID");
		}
		
		pool.returnConnection(conn);
		
		pool.close();
	}
}
