package com.controllers.execution;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.IExecutionRepository;

import com.entities.Executoin;
@RestController
public class ExecutionController {

	@Autowired
	private IExecutionRepository executionrepository;
	
	
	@RequestMapping(value = "/api/executionProjetId", method = RequestMethod.GET)
	public List<Executoin> getExecutionProjet(Integer projectID,  HttpServletRequest request) {
		return executionrepository.findProjetId(Long.valueOf(projectID));

	}
	
	@RequestMapping(value = "/api/executionId", method = RequestMethod.GET)
	public List<Executoin> getExecutionId(Integer executionID,  HttpServletRequest request) {
		return executionrepository.findExecutionId(Long.valueOf(executionID));
	}
	
	
	
	
}
