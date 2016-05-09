package org.darkphoenixs.pool.redis;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

public class JedisConn2 extends Jedis {

	public JedisConn2(String arg0, int arg1) {
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

		return "PONG";
	}

	@Override
	public String quit() {

		return "quit";
	}

	@Override
	public void disconnect() {

	}

	@Override
	public Client getClient() {

		return new Client() {

			@Override
			public boolean isBroken() {
				return true;
			}
		};
	}
}
