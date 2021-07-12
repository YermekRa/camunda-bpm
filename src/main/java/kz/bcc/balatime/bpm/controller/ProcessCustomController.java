package kz.bcc.balatime.bpm.controller;

import io.swagger.annotations.ApiOperation;
import kz.bcc.balatime.bpm.service.IProcessCustomService;
import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/private/v1/process")
public class ProcessCustomController {

    @Autowired
    IProcessCustomService processCustomService;

    @ApiOperation(value = "Get All Process List")
    @GetMapping("/all")
    public ResponseEntity<List<ProcessDefinitionDto>> getAllActiveTasks() {
//        return ResponseEntity.ok(taskCustomService.getAllActiveTasks());
        return ResponseEntity.ok(processCustomService.getAllProcess());
    }

    @ApiOperation(value = "Get All Process List")
    @PostMapping("/start/procDefKey/{procDefKey}")
    public ResponseEntity<String> startProcess(@PathVariable String procDefKey, @RequestBody HashMap variables) {
//        return ResponseEntity.ok(taskCustomService.getAllActiveTasks());
        return ResponseEntity.ok(processCustomService.startProcess(procDefKey, variables));
    }

}
