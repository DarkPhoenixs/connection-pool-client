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

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.darkphoenixs.pool.PoolConfig;
import org.junit.Test;

import java.sql.Connection;
import java.util.Properties;

public class JdbcConnectionPoolTest {

    @Test
    public void test() throws Exception {

        try {
            JdbcConnectionPool pool = new JdbcConnectionPool(
                    JdbcConfig.DEFAULT_DRIVER_CLASS,
                    JdbcConfig.DEFAULT_JDBC_URL,
                    JdbcConfig.DEFAULT_JDBC_USERNAME,
                    JdbcConfig.DEFAULT_JDBC_PASSWORD);

            pool.close();
        } catch (Exception e) {
        }

        try {
            JdbcConnectionPool pool = new JdbcConnectionPool(new PoolConfig(),
                    JdbcConfig.DEFAULT_DRIVER_CLASS,
                    JdbcConfig.DEFAULT_JDBC_URL,
                    JdbcConfig.DEFAULT_JDBC_USERNAME,
                    JdbcConfig.DEFAULT_JDBC_PASSWORD);

            pool.close();
        } catch (Exception e) {
        }
        Properties prop = new Properties();
        prop.setProperty(JdbcConfig.DRIVER_CLASS_PROPERTY,
                JdbcConfig.DEFAULT_DRIVER_CLASS);
        prop.setProperty(JdbcConfig.JDBC_URL_PROPERTY,
                JdbcConfig.DEFAULT_JDBC_URL);
        prop.setProperty(JdbcConfig.JDBC_USERNAME_PROPERTY,
                JdbcConfig.DEFAULT_JDBC_USERNAME);
        prop.setProperty(JdbcConfig.JDBC_PASSWORD_PROPERTY,
                JdbcConfig.DEFAULT_JDBC_PASSWORD);
        try {
            JdbcConnectionPool pool = new JdbcConnectionPool(prop);
            pool.close();
        } catch (Exception e) {
        }

        try {
            JdbcConnectionPool pool = new JdbcConnectionPool(new PoolConfig(),
                    prop);
            pool.close();
        } catch (Exception e) {
        }

        JdbcConnectionPool pool = new JdbcConnectionPool();

        try {
            pool.getConnection();
        } catch (Exception e) {
        }

        try {
            pool.returnConnection(null);
        } catch (Exception e) {
        }

        try {
            pool.invalidateConnection(null);
        } catch (Exception e) {
        }

        pool.close();

        JdbcConnectionPoolDemo demo = new JdbcConnectionPoolDemo();

        try {
            demo.getConnection();

        } catch (Exception e) {
        }

        demo.close();
    }

    class JdbcConnectionPoolDemo extends JdbcConnectionPool {

        public JdbcConnectionPoolDemo() {

            initPool(new PoolConfig(), new BasePooledObjectFactory<Connection>() {

                @Override
                public Connection create() throws Exception {

                    return null;
                }

                @Override
                public PooledObject<Connection> wrap(Connection obj) {

                    return null;
                }

            });

        }
    }
}
