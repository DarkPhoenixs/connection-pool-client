/**
 * <p>Title: ConnectionPool.java</p>
 * <p>Description: ConnectionPool</p>
 * <p>Package: org.darkphoenixs.pool</p>
 * <p>Company: www.github.com/DarkPhoenixs</p>
 * <p>Copyright: Dark Phoenixs (Open-Source Organization) 2015</p>
 */
package org.darkphoenixs.pool;

import java.io.Serializable;

/**
 * <p>Title: ConnectionPool</p>
 * <p>Description: 连接池接口</p>
 *
 * @since 2015年9月19日
 * @author Victor
 * @see Serializable
 * @version 1.0
 */
public interface ConnectionPool<T> extends Serializable {

	/**
	 * <p>Title: getConnection</p>
	 * <p>Description: 获取连接</p>
	 *
	 * @return 连接
	 */
	public abstract T getConnection();
	
	/**
	 * <p>Title: returnConnection</p>
	 * <p>Description: 返回连接</p>
	 *
	 * @param conn 连接
	 */
	public void returnConnection(T conn);
	
	/**
	 * <p>Title: invalidateConnection</p>
	 * <p>Description: 废弃连接</p>
	 *
	 * @param conn 连接
	 */
	public void invalidateConnection(T conn);
}
