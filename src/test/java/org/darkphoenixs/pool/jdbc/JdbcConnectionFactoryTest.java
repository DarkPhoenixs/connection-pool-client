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

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.junit.Test;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class JdbcConnectionFactoryTest {

    @Test
    public void test() throws Exception {

        try {
            JdbcConnectionFactory factory = new JdbcConnectionFactory(
                    "com.mysql.jdbc.Driver1", null, null, null);
            factory.toString();
        } catch (Exception e) {
        }

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
            factory.validateObject(new DefaultPooledObject<Connection>(
                    new Conn()));
        } catch (Exception e) {
        }

        try {
            factory.destroyObject(new DefaultPooledObject<Connection>(
                    new Conn()));
        } catch (Exception e) {
        }

        try {
            factory.validateObject(new DefaultPooledObject<Connection>(
                    new Conn()));
        } catch (Exception e) {
        }

        try {
            factory.validateObject(new DefaultPooledObject<Connection>(
                    new Conn2()));
        } catch (Exception e) {
        }

        try {

            JdbcConnectionFactoryDemo demo = new JdbcConnectionFactoryDemo(prop);

            demo.makeObject();

        } catch (Exception e) {

        }
    }

    private class Conn implements Connection {

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
        public CallableStatement prepareCall(String sql) throws SQLException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {
            // TODO Auto-generated method stub

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
            return true;
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public boolean isReadOnly() throws SQLException {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {
            // TODO Auto-generated method stub

        }

        @Override
        public String getCatalog() throws SQLException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {
            // TODO Auto-generated method stub

        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {
            // TODO Auto-generated method stub

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
        public CallableStatement prepareCall(String sql, int resultSetType,
                                             int resultSetConcurrency) throws SQLException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            // TODO Auto-generated method stub

        }

        @Override
        public int getHoldability() throws SQLException {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public void setHoldability(int holdability) throws SQLException {
            // TODO Auto-generated method stub

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
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
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
        public CallableStatement prepareCall(String sql, int resultSetType,
                                             int resultSetConcurrency, int resultSetHoldability)
                throws SQLException {
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

            throw new SQLException("timeout");
        }

        @Override
        public void setClientInfo(String name, String value)
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
        public void setClientInfo(Properties properties)
                throws SQLClientInfoException {
            // TODO Auto-generated method stub

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
        public String getSchema() throws SQLException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void setSchema(String schema) throws SQLException {
            // TODO Auto-generated method stub

        }

        @Override
        public void abort(Executor executor) throws SQLException {
            // TODO Auto-generated method stub

        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds)
                throws SQLException {
            // TODO Auto-generated method stub

        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            // TODO Auto-generated method stub
            return 0;
        }

    }

    private class Conn2 implements Connection {

        @Override
        public Statement createStatement() throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return null;
        }

        @Override
        public CallableStatement prepareCall(String sql) throws SQLException {
            return null;
        }

        @Override
        public String nativeSQL(String sql) throws SQLException {
            return null;
        }

        @Override
        public void setAutoCommit(boolean autoCommit) throws SQLException {

        }

        @Override
        public boolean getAutoCommit() throws SQLException {
            return false;
        }

        @Override
        public void commit() throws SQLException {

        }

        @Override
        public void rollback() throws SQLException {

        }

        @Override
        public void close() throws SQLException {

        }

        @Override
        public boolean isClosed() throws SQLException {
            return false;
        }

        @Override
        public DatabaseMetaData getMetaData() throws SQLException {
            return null;
        }

        @Override
        public void setReadOnly(boolean readOnly) throws SQLException {

        }

        @Override
        public boolean isReadOnly() throws SQLException {
            return false;
        }

        @Override
        public void setCatalog(String catalog) throws SQLException {

        }

        @Override
        public String getCatalog() throws SQLException {
            return null;
        }

        @Override
        public void setTransactionIsolation(int level) throws SQLException {

        }

        @Override
        public int getTransactionIsolation() throws SQLException {
            return 0;
        }

        @Override
        public SQLWarning getWarnings() throws SQLException {
            return null;
        }

        @Override
        public void clearWarnings() throws SQLException {

        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return null;
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return null;
        }

        @Override
        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return null;
        }

        @Override
        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

        }

        @Override
        public void setHoldability(int holdability) throws SQLException {

        }

        @Override
        public int getHoldability() throws SQLException {
            return 0;
        }

        @Override
        public Savepoint setSavepoint() throws SQLException {
            return null;
        }

        @Override
        public Savepoint setSavepoint(String name) throws SQLException {
            return null;
        }

        @Override
        public void rollback(Savepoint savepoint) throws SQLException {

        }

        @Override
        public void releaseSavepoint(Savepoint savepoint) throws SQLException {

        }

        @Override
        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        @Override
        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return null;
        }

        @Override
        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return null;
        }

        @Override
        public Clob createClob() throws SQLException {
            return null;
        }

        @Override
        public Blob createBlob() throws SQLException {
            return null;
        }

        @Override
        public NClob createNClob() throws SQLException {
            return null;
        }

        @Override
        public SQLXML createSQLXML() throws SQLException {
            return null;
        }

        @Override
        public boolean isValid(int timeout) throws SQLException {
            return true;
        }

        @Override
        public void setClientInfo(String name, String value) throws SQLClientInfoException {

        }

        @Override
        public void setClientInfo(Properties properties) throws SQLClientInfoException {

        }

        @Override
        public String getClientInfo(String name) throws SQLException {
            return null;
        }

        @Override
        public Properties getClientInfo() throws SQLException {
            return null;
        }

        @Override
        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return null;
        }

        @Override
        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return null;
        }

        @Override
        public void setSchema(String schema) throws SQLException {

        }

        @Override
        public String getSchema() throws SQLException {
            return null;
        }

        @Override
        public void abort(Executor executor) throws SQLException {

        }

        @Override
        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

        }

        @Override
        public int getNetworkTimeout() throws SQLException {
            return 0;
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            return null;
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }
    }

    private class JdbcConnectionFactoryDemo extends JdbcConnectionFactory {

        public JdbcConnectionFactoryDemo(Properties properties) {

            super(properties);
        }

        @Override
        public Connection createConnection() throws Exception {

            return new Conn2();
        }
    }
}
