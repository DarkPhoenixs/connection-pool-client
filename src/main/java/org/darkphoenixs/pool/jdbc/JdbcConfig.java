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
package org.darkphoenixs.pool.jdbc;

/**
 * <p>JdbcConfig</p>
 * <p>JDBC配置</p>
 *
 * @since 1.2.1
 * @author Victor
 */
public interface JdbcConfig {

	/** MYSQL DEFAULT_DRIVER_CLASS */
	public static final String DEFAULT_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	/** MYSQL DEFAULT_JDBC_URL */
	public static final String DEFAULT_JDBC_URL = "jdbc:mysql://localhost:3306/test";
	/** MYSQL DEFAULT_JDBC_USERNAME */
	public static final String DEFAULT_JDBC_USERNAME = "root";
	/** MYSQL DEFAULT_JDBC_PASSWORD */
	public static final String DEFAULT_JDBC_PASSWORD = "root";
	
	/** DRIVER_CLASS_PROPERTY */
	public static final String DRIVER_CLASS_PROPERTY = "driverClass";
	/** JDBC_URL_PROPERTY */
	public static final String JDBC_URL_PROPERTY = "jdbcUrl";
	/** JDBC_USERNAME_PROPERTY*/
	public static final String JDBC_USERNAME_PROPERTY = "username";
	/** JDBC_PASSWORD_PROPERTY */
	public static final String JDBC_PASSWORD_PROPERTY = "password";
}
