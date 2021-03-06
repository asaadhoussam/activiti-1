/* Licensed under the Apache License, Version 2.0 (the "License");
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

package org.activiti.engine.impl.persistence.db;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.persistence.PersistentObject;


/**
 * @author Tom Baeyens
 */
public abstract class AbstractDbSession implements Session {

  protected DbSqlSession dbSqlSession;

  public AbstractDbSession() {
    this.dbSqlSession = CommandContext.getCurrentSession(DbSqlSession.class);
  }
  
  public void insert(PersistentObject persistentObject) {
    dbSqlSession.insert(persistentObject);
  }

  public void delete(PersistentObject persistentObject) {
    dbSqlSession.delete(persistentObject);
  }
}
