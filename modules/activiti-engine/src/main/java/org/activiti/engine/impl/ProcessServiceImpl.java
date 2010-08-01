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
package org.activiti.engine.impl;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ActivityInstance;
import org.activiti.engine.ProcessInstance;
import org.activiti.engine.ProcessInstanceQuery;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cmd.DeleteProcessInstanceCmd;
import org.activiti.engine.impl.cmd.FindActivitiyInstanceCmd;
import org.activiti.engine.impl.cmd.FindActivityInstanceInActivityCmd;
import org.activiti.engine.impl.cmd.FindProcessInstanceCmd;
import org.activiti.engine.impl.cmd.GetVariableCmd;
import org.activiti.engine.impl.cmd.GetVariablesCmd;
import org.activiti.engine.impl.cmd.SetVariablesCmd;
import org.activiti.engine.impl.cmd.SignalCmd;
import org.activiti.engine.impl.cmd.StartProcessInstanceCmd;

/**
 * @author Tom Baeyens
 */
public class ProcessServiceImpl extends ServiceImpl implements RuntimeService {

  public ActivityInstance findActivityInstanceById(String activityInstanceId) {
    return commandExecutor.execute(new FindActivitiyInstanceCmd(activityInstanceId));
  }

  public void deleteProcessInstance(String processInstanceId) {
    commandExecutor.execute(new DeleteProcessInstanceCmd(processInstanceId));
  }

  public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) {
    return commandExecutor.execute(new StartProcessInstanceCmd<ProcessInstance>(processDefinitionKey, null, null));
  }

  public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, Map<String, Object> variables) {
    return commandExecutor.execute(new StartProcessInstanceCmd<ProcessInstance>(processDefinitionKey, null, variables));
  }

  public ProcessInstance startProcessInstanceById(String processDefinitionId) {
    return commandExecutor.execute(new StartProcessInstanceCmd<ProcessInstance>(null, processDefinitionId, null));
  }

  public ProcessInstance startProcessInstanceById(String processDefinitionId, Map<String, Object> variables) {
    return commandExecutor.execute(new StartProcessInstanceCmd<ProcessInstance>(null, processDefinitionId, variables));
  }

  public ProcessInstance findProcessInstanceById(String processInstanceId) {
    return commandExecutor.execute(new FindProcessInstanceCmd(processInstanceId));
  }

  public ProcessInstanceQuery createProcessInstanceQuery() {
    return new ProcessInstanceQueryImpl(commandExecutor);
  }

  public Map<String, Object> getVariables(String executionId) {
    return commandExecutor.execute(new GetVariablesCmd(executionId));
  }

  public Object getVariable(String executionId, String variableName) {
    return commandExecutor.execute(new GetVariableCmd(executionId, variableName));
  }

  public void setVariable(String executionId, String variableName, Object value) {
    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put(variableName, value);
    commandExecutor.execute(new SetVariablesCmd(executionId, variables));
  }

  public void setVariables(String executionId, Map<String, Object> variables) {
    commandExecutor.execute(new SetVariablesCmd(executionId, variables));
  }

  public void signal(String activityInstanceId) {
    commandExecutor.execute(new SignalCmd(activityInstanceId, null, null));
  }

  public void signal(String activityInstanceId, String sigalName, Object signalData) {
    commandExecutor.execute(new SignalCmd(activityInstanceId, sigalName, signalData));
  }

  public ActivityInstance findActivityInstanceByProcessInstanceIdAndActivityId(String processInstanceId, String activityId) {
    return commandExecutor.execute(new FindActivityInstanceInActivityCmd(processInstanceId, activityId));
  }
}
