/**
 * <p>Title: JdbcConnectionPool.java</p>
 * <p>Description: JdbcConnectionPool</p>
 * <p>Package: org.darkphoenixs.pool.jdbc</p>
 * <p>Company: www.github.com/DarkPhoenixs</p>
 * <p>Copyright: Dark Phoenixs (Open-Source Organization) 2015</p>
 */
package org.darkphoenixs.pool.jdbc;

import java.sql.Connection;
import java.util.Properties;

import org.darkphoenixs.pool.ConnectionPool;
import org.darkphoenixs.pool.PoolBase;
import org.darkphoenixs.pool.PoolConfig;

/**
 * <p>Title: JdbcConnectionPool</p>
 * <p>Description: Jdbc连接池</p>
 *
 * @since 2015年11月17日
 * @author Victor.Zxy
 * @see PoolBase
 * @see ConnectionPool
 * @version 1.0
 */
public class JdbcConnectionPool extends PoolBase<Connection> implements ConnectionPool<Connection> {

	/** serialVersionUID */
	private static final long serialVersionUID = 2743612676107943708L;

	
	/**
	 * <p>Title: JdbcConnectionPool</p>
	 * <p>Description: 默认构造方法</p>
	 */
	public JdbcConnectionPool() {

		this("oracle.jdbc.OracleDriver", "jdbc:oracle:thin:@localhost:1521:orcl", "root", "root");
	}
	
	/**
	 * <p>Title: JdbcConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param properties JDBC参数
	 */
	public JdbcConnectionPool(final Properties properties) {
	
		this(new PoolConfig(), properties);
	}
	
	/**
	 * <p>Title: JdbcConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param driverClass 驱动类
	 * @param jdbcUrl 数据库URL
	 * @param username 数据库用户名
	 * @param password 数据密码
	 */
	public JdbcConnectionPool(final String driverClass, final String jdbcUrl, final String username, final String password) {

		this(new PoolConfig(), driverClass, jdbcUrl, username, password);
	}
	
	/**
	 * <p>Title: JdbcConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param properties JDBC参数
	 */
	public JdbcConnectionPool(final PoolConfig poolConfig, final Properties properties) {
		
		super(poolConfig, new JdbcConnectionFactory(properties));
	}
	
	/**
	 * <p>Title: JdbcConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param driverClass 驱动类
	 * @param jdbcUrl 数据库URL
	 * @param username 数据库用户名
	 * @param password 数据密码
	 */
	public JdbcConnectionPool(final PoolConfig poolConfig, final String driverClass, final String jdbcUrl, final String username, final String password) {

		super(poolConfig, new JdbcConnectionFactory(driverClass, jdbcUrl, username, password));
	}
	
	@Override
	public Connection getConnection() {
		
		return super.getResource();
	}

	@Override
	public void returnConnection(Connection conn) {
		
		super.returnResource(conn);
	}
	
	@Override
	public void invalidateConnection(Connection conn) {

		super.invalidateResource(conn);
	}

}
