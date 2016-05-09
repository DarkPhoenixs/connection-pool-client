package org.darkphoenixs.pool.redis;

import redis.clients.jedis.Jedis;

public class JedisConn4 extends Jedis {

	public JedisConn4(String arg0, int arg1) {
		super(arg0, arg1);
	}

	@Override
	public String select(int index) {

		return String.valueOf(index);
	}

	@Override
	public boolean isConnected() {

		return true;
	}

	@Override
	public String ping() {

		return "PONG1";
	}

	@Override
	public String quit() {

		return "quit";
	}

	@Override
	public void disconnect() {

	}
}
