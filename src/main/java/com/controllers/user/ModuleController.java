package com.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.ImoduleRepository;
 
import com.entities.Module;
@RestController
public class ModuleController {

	@Autowired
	private ImoduleRepository modulerepository;
	
	
	@RequestMapping(value = "/api/module", method = RequestMethod.GET)
	public List<Module> getModule() {

		return modulerepository.findAll();

	}
	
}
