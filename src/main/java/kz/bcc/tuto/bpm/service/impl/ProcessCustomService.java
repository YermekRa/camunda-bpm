package kz.bcc.tuto.bpm.service.impl;

import kz.bcc.tuto.bpm.service.IProcessCustomService;
import org.camunda.bpm.ProcessEngineService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.rest.dto.ProcessEngineDto;
import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ProcessCustomService implements IProcessCustomService {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;


    @Override
    public List<ProcessDefinitionDto> getAllProcess() {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().active().latestVersion().list();

        List<ProcessDefinitionDto> processDefinitionDtos = new ArrayList<>();

        for (ProcessDefinition processDefinition: processDefinitions) {
            processDefinitionDtos.add(ProcessDefinitionDto.fromProcessDefinition(processDefinition));
        }

        return processDefinitionDtos;
    }

    @Override
    public String startProcess(String processDefKey, HashMap variables) {

        runtimeService.startProcessInstanceByKey(processDefKey, variables);

        return "Process is started";
    }


}
