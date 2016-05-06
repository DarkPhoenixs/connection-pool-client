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
