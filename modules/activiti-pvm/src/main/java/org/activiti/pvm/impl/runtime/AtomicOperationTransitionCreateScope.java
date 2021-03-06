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
package org.activiti.pvm.impl.runtime;

import org.activiti.pvm.impl.process.ActivityImpl;
import org.activiti.pvm.impl.process.ScopeImpl;
import org.activiti.pvm.process.PvmActivity;


/**
 * @author Tom Baeyens
 */
public class AtomicOperationTransitionCreateScope implements AtomicOperation {

  public void execute(ExecutionImpl execution) {
    ExecutionImpl propagatingExecution = null;
    ActivityImpl activity = execution.getActivity();
    if (activity.isScope()) {
      propagatingExecution = (ExecutionImpl) execution.createScope();

      // set the pointer of the scope-execution to its scope: the activity 
      // if it is an activity or null if it is the process definition
      ScopeImpl parentScope = activity.getParent();
      if (parentScope instanceof PvmActivity) {
        execution.setActivity((ActivityImpl) parentScope);
      } else {
        execution.setActivity(null);
      }

    } else {
      propagatingExecution = execution;
    }
    propagatingExecution.performOperation(AtomicOperation.TRANSITION_NOTIFY_LISTENER_START);
  }

  public boolean isAsync() {
    return false;
  }
  
  public String toString() {
    return "TransitionCreateScope";
  }
}
