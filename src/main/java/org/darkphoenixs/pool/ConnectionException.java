/**
 * <p>Title: ConnectionException.java</p>
 * <p>Description: ConnectionException</p>
 * <p>Package: org.darkphoenixs.pool</p>
 * <p>Company: www.github.com/DarkPhoenixs</p>
 * <p>Copyright: Dark Phoenixs (Open-Source Organization) 2015</p>
 */
package org.darkphoenixs.pool;

/**
 * <p>Title: ConnectionException</p>
 * <p>Description: 连接异常</p>
 *
 * @since 2015年9月19日
 * @author Victor
 * @see RuntimeException
 * @version 1.0
 */
public class ConnectionException extends RuntimeException {

	private static final long serialVersionUID = -6503525110247209484L;

	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(Throwable e) {
		super(e);
	}

	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
