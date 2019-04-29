package com.controllers.parametre;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.ILanguesRepository;
import com.entities.Langues;

@RestController
public class LanguesController {

	@Autowired
	private ILanguesRepository langueRepository;
	
	@RequestMapping(value = "/api/Langues", method = RequestMethod.GET)
	public List<Langues> getLangues( Long idModule, HttpServletRequest request) {
		return langueRepository.findLanguesId(idModule);
	}
	
}
