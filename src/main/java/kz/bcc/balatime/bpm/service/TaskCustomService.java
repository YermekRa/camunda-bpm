package kz.bcc.balatime.bpm.service;

import org.camunda.bpm.engine.rest.dto.task.TaskDto;

import java.util.HashMap;
import java.util.List;

public interface TaskCustomService {


    List<TaskDto> getAllActiveTasks();

    TaskDto claimTask(String taskId, String userId);

    TaskDto getTaskById(String taskId);

    String submitTask(String taskId,String userId, HashMap variables);
}
