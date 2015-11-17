/**
 * <p>Title: JdbcConnectionFactory.java</p>
 * <p>Description: JdbcConnectionFactory</p>
 * <p>Package: org.darkphoenixs.pool.jdbc</p>
 * <p>Company: www.github.com/DarkPhoenixs</p>
 * <p>Copyright: Dark Phoenixs (Open-Source Organization) 2015</p>
 */
package org.darkphoenixs.pool.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.darkphoenixs.pool.ConnectionFactory;

/**
 * <p>Title: JdbcConnectionFactory</p>
 * <p>Description: JDBC连接工厂</p>
 *
 * @since 2015年11月17日
 * @author Victor.Zxy
 * @see ConnectionFactory
 * @version 1.0
 */
class JdbcConnectionFactory implements ConnectionFactory<Connection> {

	/** serialVersionUID */
	private static final long serialVersionUID = 4941500146671191616L;

	/** driverClass */
	private final String driverClass;
	
	/** jdbcUrl */
	private final String jdbcUrl;
	
	/** username */
	private final String username;
	
	/** password */
	private final String password;

	/**
	 * <p>Title: loadDriver</p>
	 * <p>Description: 加载驱动</p>
	 */
	private void loadDriver() {

		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * <p>Title: JdbcConnectionFactory</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param properties JDBC参数
	 */
	public JdbcConnectionFactory(final Properties properties) {

		this.driverClass = properties.getProperty("driverClass");
		this.jdbcUrl = properties.getProperty("jdbcUrl");
		this.username = properties.getProperty("username");
		this.password = properties.getProperty("password");
		this.loadDriver();
	}
	
	/**
	 * <p>Title: JdbcConnectionFactory</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param driverClass 驱动类
	 * @param jdbcUrl 数据库URL
	 * @param username 数据库用户名
	 * @param password 数据密码
	 */
	public JdbcConnectionFactory(final String driverClass, final String jdbcUrl, final String username, final String password) {

		this.driverClass = driverClass;
		this.jdbcUrl = jdbcUrl;
		this.username = username;
		this.password = password;
		this.loadDriver();
	}
	
	@Override
	public PooledObject<Connection> makeObject() throws Exception {

		Connection connection = this.createConnection();
		
		return new DefaultPooledObject<>(connection);	
	}

	@Override
	public void destroyObject(PooledObject<Connection> p) throws Exception {
		
		Connection connection = p.getObject();

		if (connection != null)

			connection.close();
	}

	@Override
	public boolean validateObject(PooledObject<Connection> p) {

		Connection connection = p.getObject();
		
		if (connection != null)
			try {
				return ((!connection.isClosed()) && (connection.isValid(1)));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return false;
	}

	@Override
	public void activateObject(PooledObject<Connection> p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passivateObject(PooledObject<Connection> p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Connection createConnection() throws Exception {

		Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
		
		return connection;
	}

}
