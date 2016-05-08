/*
 * Copyright 2015-2016 Dark Phoenixs (Open-Source Organization).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.darkphoenixs.pool.redis;

/**
 * <p>RedisConfig</p>
 * <p>Redis配置</p>
 *
 * @since 1.2.1
 * @author Victor
 *
 */
public interface RedisConfig {

	/** DEFAULT_HOST */
	public static final String DEFAULT_HOST = "localhost";
	/** DEFAULT_PORT */
	public static final int DEFAULT_PORT = 6379;
	/** DEFAULT_TIMEOUT */
	public static final int DEFAULT_TIMEOUT = 2000;
	/** DEFAULT_DATABASE */
	public static final int DEFAULT_DATABASE = 0;
	/** DEFAULT_PASSWORD */
	public static final String DEFAULT_PASSWORD = null;
	/** DEFAULT_CLIENTNAME */
	public static final String DEFAULT_CLIENTNAME = null;

	/** ADDRESS_PROPERTY */
	public static final String ADDRESS_PROPERTY = "address";
	/** CONN_TIMEOUT_PROPERTY */
	public static final String CONN_TIMEOUT_PROPERTY = "connectionTimeout";
	/** SO_TIMEOUT_PROPERTY */
	public static final String SO_TIMEOUT_PROPERTY = "soTimeout";
	/** DATABASE_PROPERTY */
	public static final String DATABASE_PROPERTY = "database";
	/** PASSWORD_PROPERTY */
	public static final String PASSWORD_PROPERTY = "password";
	/** CLIENTNAME_PROPERTY */
	public static final String CLIENTNAME_PROPERTY = "clientName";
	/** MASTERNAME_PROPERTY */
	public static final String MASTERNAME_PROPERTY = "masterName";
	/** SENTINELS_PROPERTY */
	public static final String SENTINELS_PROPERTY = "sentinels";

}
