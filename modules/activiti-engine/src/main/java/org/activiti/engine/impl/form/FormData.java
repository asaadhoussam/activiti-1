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
package org.activiti.engine.impl.form;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.impl.persistence.task.TaskImpl;


/**
 * @author Tom Baeyens
 */
public class FormData implements Map<String, Object> {
  
  TaskImpl task;

  public FormData(TaskImpl task) {
    this.task = task;
  }

  public void clear() {
  }

  public boolean containsKey(Object key) {
    return false;
  }

  public boolean containsValue(Object value) {
    return false;
  }

  public Set<java.util.Map.Entry<String, Object>> entrySet() {
    return null;
  }

  public Object get(Object key) {
    return null;
  }

  public boolean isEmpty() {
    return false;
  }

  public Set<String> keySet() {
    return null;
  }

  public Object put(String key, Object value) {
    return null;
  }

  public void putAll(Map< ? extends String, ? extends Object> m) {
  }

  public Object remove(Object key) {
    return null;
  }

  public int size() {
    return 0;
  }

  public Collection<Object> values() {
    return null;
  }

}
