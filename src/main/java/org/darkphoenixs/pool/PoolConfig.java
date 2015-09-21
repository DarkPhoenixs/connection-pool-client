/**
 * <p>Title: PoolConfig.java</p>
 * <p>Description: PoolConfig</p>
 * <p>Package: org.darkphoenixs.pool</p>
 * <p>Company: www.github.com/DarkPhoenixs</p>
 * <p>Copyright: Dark Phoenixs (Open-Source Organization) 2015</p>
 */
package org.darkphoenixs.pool;

import java.io.Serializable;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * <p>Title: PoolConfig</p>
 * <p>Description: 默认池配置</p>
 *
 * @since 2015年9月19日
 * @author Victor
 * @see GenericObjectPoolConfig
 * @see Serializable
 * @version 1.0
 */
public class PoolConfig extends GenericObjectPoolConfig implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -2414567557372345057L;

	/**
	 * <p>Title: PoolConfig</p>
	 * <p>Description: 默认构造方法</p>
	 */
	public PoolConfig() {

	    // defaults to make your life with connection pool easier :)
	    setTestWhileIdle(true);
	    setMinEvictableIdleTimeMillis(60000);
	    setTimeBetweenEvictionRunsMillis(30000);
	    setNumTestsPerEvictionRun(-1);
	}
}
