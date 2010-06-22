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
package org.activiti.test.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.activiti.ProcessInstance;
import org.activiti.Task;
import org.activiti.test.ActivitiTestCase;
import org.activiti.test.ProcessDeclared;
import org.junit.Test;

/**
 * @author Joram Barrez
 */
public class TaskServiceTest extends ActivitiTestCase {
  
  @Test
  @ProcessDeclared(resources={"twoTasksProcess.bpmn20.xml"})
  public void testCompleteWithParametersTask() {
    ProcessInstance processInstance = processEngineBuilder.getProcessService().startProcessInstanceByKey("twoTasksProcess");
    
    // Fetch first task
    Task task = processEngineBuilder.getTaskService().createTaskQuery().singleResult();
    assertEquals("First task", task.getName());
    
    // Complete first task
    Map<String, Object> taskParams = new HashMap<String, Object>();
    taskParams.put("myParam", "myValue");
    processEngineBuilder.getTaskService().complete(task.getId(), taskParams);
    
    // Fetch second task
    task = processEngineBuilder.getTaskService().createTaskQuery().singleResult();
    assertEquals("Second task", task.getName());
    
    // Verify task parameters set on execution
    Map<String, Object> variables = processEngineBuilder.getProcessService().getVariables(processInstance.getId());
    assertEquals(1, variables.size());
    assertEquals("myValue", variables.get("myParam"));
  }

}
