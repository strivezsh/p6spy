/*
 * #%L
 * P6Spy
 * %%
 * Copyright (C) 2002 - 2016 P6Spy
 * %%
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
 * #L%
 */
package com.p6spy.engine.logging;

import java.sql.PreparedStatement;

import com.p6spy.engine.common.ConnectionInformation;
import com.p6spy.engine.common.PreparedStatementInformation;
import com.p6spy.engine.proxy.MethodNameMatcher;

public abstract class AbstractP6LogPreparedStatementInvocationHandler<S extends PreparedStatement, I extends PreparedStatementInformation>
  extends AbstractP6LogStatementInvocationHandler<S, I> {

  protected final String query;

  public AbstractP6LogPreparedStatementInvocationHandler(S underlying,
                                                         ConnectionInformation connectionInformation,
                                                         String query) {
    super(underlying, connectionInformation, query);
    this.query = query;
    addDelegate(new MethodNameMatcher("set*"), getSetParameterValueDelegate());
  }

  protected abstract P6LogPreparedStatementSetParameterValueDelegate getSetParameterValueDelegate();

}