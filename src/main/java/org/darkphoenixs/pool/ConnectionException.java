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

/**
 * <p>Title: ConnectionException</p>
 * <p>Description: 连接异常</p>
 *
 * @author Victor
 * @version 1.0
 * @see RuntimeException
 * @since 2015年9月19日
 */
public class ConnectionException extends RuntimeException {

    private static final long serialVersionUID = -6503525110247209484L;

    public ConnectionException() {
        super();
    }

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
