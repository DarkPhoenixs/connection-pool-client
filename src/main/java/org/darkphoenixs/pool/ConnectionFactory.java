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
package org.darkphoenixs.pool;

import org.apache.commons.pool2.PooledObjectFactory;

import java.io.Serializable;

/**
 * <p>Title: ConnectionFactory</p>
 * <p>Description: 连接工厂接口</p>
 *
 * @author Victor
 * @version 1.0
 * @see PooledObjectFactory
 * @see Serializable
 * @since 2015年9月19日
 */
public interface ConnectionFactory<T> extends PooledObjectFactory<T>, Serializable {

    /**
     * <p>Title: createConnection</p>
     * <p>Description: 创建连接</p>
     *
     * @return 连接
     * @throws Exception
     */
    public abstract T createConnection() throws Exception;
}
