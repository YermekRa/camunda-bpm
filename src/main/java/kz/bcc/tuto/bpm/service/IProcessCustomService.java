package kz.bcc.tuto.bpm.service;

import org.camunda.bpm.engine.rest.dto.ProcessEngineDto;
import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;

import java.util.HashMap;
import java.util.List;

public interface IProcessCustomService {

    List<ProcessDefinitionDto> getAllProcess();

    String startProcess(String processDefKey, HashMap variables);

}
