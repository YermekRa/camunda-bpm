package kz.bcc.balatime.bpm.controller;

import io.swagger.annotations.ApiOperation;
import kz.bcc.balatime.bpm.service.TaskCustomService;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/private/v1/task", produces = APPLICATION_JSON_VALUE)
public class TaskCustomController {

    @Autowired
    TaskCustomService taskCustomService;

    @ApiOperation(value = "Create or update object")
    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAllActiveTasks() {
        return ResponseEntity.ok(taskCustomService.getAllActiveTasks());
    }

    @ApiOperation(value = "Create or update object")
    @GetMapping("/claim/taskId/{taskId}/userId/{userId}")
    public ResponseEntity<TaskDto> claimTask(@PathVariable String taskId, @PathVariable String userId) {
        return ResponseEntity.ok(taskCustomService.claimTask(taskId, userId));
    }


    @ApiOperation(value = "Create or update object")
    @PostMapping("/submit/taskId/{taskId}/userId/{userId}")
    public ResponseEntity<String> submitTask(@PathVariable String taskId, @PathVariable String userId, @Valid
                                             @RequestBody HashMap variables) {
        return ResponseEntity.ok(taskCustomService.submitTask(taskId, userId, variables));
    }

}
