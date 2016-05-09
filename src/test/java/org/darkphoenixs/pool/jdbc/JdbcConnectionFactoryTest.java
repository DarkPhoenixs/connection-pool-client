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

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.junit.Test;

public class JdbcConnectionFactoryTest {

	@Test
	public void test() throws Exception {

		try {
			JdbcConnectionFactory factory = new JdbcConnectionFactory(null,
					null, null, null);
			factory.toString();
		} catch (Exception e) {
		}

		try {
			JdbcConnectionFactory factory = new JdbcConnectionFactory(
					new Properties());
			factory.toString();
		} catch (Exception e) {
		}
		
		Properties prop = new Properties();
		try {
			prop.setProperty(JdbcConfig.DRIVER_CLASS_PROPERTY,
					JdbcConfig.DEFAULT_DRIVER_CLASS);

			JdbcConnectionFactory factory = new JdbcConnectionFactory(prop);
			factory.toString();
		} catch (Exception e) {
		}

		try {
			prop.setProperty(JdbcConfig.JDBC_URL_PROPERTY,
					JdbcConfig.DEFAULT_JDBC_URL);
			JdbcConnectionFactory factory = new JdbcConnectionFactory(prop);
			factory.toString();
		} catch (Exception e) {
		}

		try {
			prop.setProperty(JdbcConfig.JDBC_USERNAME_PROPERTY,
					JdbcConfig.DEFAULT_JDBC_USERNAME);
			JdbcConnectionFactory factory = new JdbcConnectionFactory(prop);
			factory.toString();
		} catch (Exception e) {
		}

		try {
			prop.setProperty(JdbcConfig.JDBC_PASSWORD_PROPERTY,
					JdbcConfig.DEFAULT_JDBC_PASSWORD);
			JdbcConnectionFactory factory = new JdbcConnectionFactory(prop);
			factory.toString();
		} catch (Exception e) {
		}

		JdbcConnectionFactory factory = new JdbcConnectionFactory(
				JdbcConfig.DEFAULT_DRIVER_CLASS, JdbcConfig.DEFAULT_JDBC_URL,
				JdbcConfig.DEFAULT_JDBC_USERNAME,
				JdbcConfig.DEFAULT_JDBC_PASSWORD);

		try {
			factory.makeObject();

		} catch (Exception e) {
		}
		
		try {
			factory.activateObject(new DefaultPooledObject<Connection>(null));
		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<Connection>(null));
		} catch (Exception e) {
		}

		try {
			factory.passivateObject(new DefaultPooledObject<Connection>(null));
		} catch (Exception e) {
		}

		try {
			factory.destroyObject(new DefaultPooledObject<Connection>(null));
		} catch (Exception e) {
		}

		try {
			factory.destroyObject(new DefaultPooledObject<Connection>(new Connection(){

				@Override
				public <T> T unwrap(Class<T> iface) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public boolean isWrapperFor(Class<?> iface) throws SQLException {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public Statement createStatement() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public PreparedStatement prepareStatement(String sql)
						throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public CallableStatement prepareCall(String sql)
						throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String nativeSQL(String sql) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void setAutoCommit(boolean autoCommit)
						throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public boolean getAutoCommit() throws SQLException {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void commit() throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void rollback() throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void close() throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public boolean isClosed() throws SQLException {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public DatabaseMetaData getMetaData() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void setReadOnly(boolean readOnly) throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public boolean isReadOnly() throws SQLException {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void setCatalog(String catalog) throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public String getCatalog() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void setTransactionIsolation(int level)
						throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public int getTransactionIsolation() throws SQLException {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public SQLWarning getWarnings() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void clearWarnings() throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public Statement createStatement(int resultSetType,
						int resultSetConcurrency) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public PreparedStatement prepareStatement(String sql,
						int resultSetType, int resultSetConcurrency)
						throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public CallableStatement prepareCall(String sql,
						int resultSetType, int resultSetConcurrency)
						throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Map<String, Class<?>> getTypeMap() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void setTypeMap(Map<String, Class<?>> map)
						throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void setHoldability(int holdability) throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public int getHoldability() throws SQLException {
					// TODO Auto-generated method stub
					return 0;
				}

				@Override
				public Savepoint setSavepoint() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Savepoint setSavepoint(String name) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void rollback(Savepoint savepoint) throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void releaseSavepoint(Savepoint savepoint)
						throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public Statement createStatement(int resultSetType,
						int resultSetConcurrency, int resultSetHoldability)
						throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public PreparedStatement prepareStatement(String sql,
						int resultSetType, int resultSetConcurrency,
						int resultSetHoldability) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public CallableStatement prepareCall(String sql,
						int resultSetType, int resultSetConcurrency,
						int resultSetHoldability) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public PreparedStatement prepareStatement(String sql,
						int autoGeneratedKeys) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public PreparedStatement prepareStatement(String sql,
						int[] columnIndexes) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public PreparedStatement prepareStatement(String sql,
						String[] columnNames) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Clob createClob() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Blob createBlob() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public NClob createNClob() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public SQLXML createSQLXML() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public boolean isValid(int timeout) throws SQLException {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void setClientInfo(String name, String value)
						throws SQLClientInfoException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void setClientInfo(Properties properties)
						throws SQLClientInfoException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public String getClientInfo(String name) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Properties getClientInfo() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Array createArrayOf(String typeName, Object[] elements)
						throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Struct createStruct(String typeName, Object[] attributes)
						throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void setSchema(String schema) throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public String getSchema() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void abort(Executor executor) throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void setNetworkTimeout(Executor executor,
						int milliseconds) throws SQLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public int getNetworkTimeout() throws SQLException {
					// TODO Auto-generated method stub
					return 0;
				}
				
			}));
		} catch (Exception e) {
		}
		
	}
}
