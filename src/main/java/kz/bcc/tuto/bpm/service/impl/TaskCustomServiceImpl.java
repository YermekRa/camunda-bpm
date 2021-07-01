package kz.bcc.tuto.bpm.service.impl;

import kz.bcc.tuto.bpm.service.TaskCustomService;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TaskCustomServiceImpl implements TaskCustomService {

    @Autowired
    TaskService taskService;

    @Autowired
    IdentityService identityService;

    @Override
    public List<TaskDto> getAllActiveTasks() {
        List<Task> taskList = taskService.createTaskQuery().active().list();
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task t : taskList) {
            taskDtos.add(TaskDto.fromEntity(t));
        }
        return taskDtos;
    }

    @Override
    public TaskDto claimTask(String taskId, String userId) {
        TaskQuery taskQuery = taskService.createTaskQuery().taskId(taskId);
        if (taskQuery.list().isEmpty()) {
            return null;
        }
        Task task = taskQuery.singleResult();
        task.setAssignee(userId);
        taskService.saveTask(task);

        return TaskDto.fromEntity(taskService.createTaskQuery().taskId(taskId).singleResult());
    }

    @Override
    public TaskDto getTaskById(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        return TaskDto.fromEntity(task);
    }

    @Override
    public String submitTask(String taskId, String userId, HashMap variables) {
        identityService.setAuthenticatedUserId(userId);
        TaskDto task = getTaskById(taskId);
        try {
            taskService.complete(task.getId(), variables);

        } catch (Exception e) {
            System.out.println("Message: " + e.getCause());
        }
        return "DONE:" + taskId;
    }
}
