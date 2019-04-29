package com.controllers.execution;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dao.IReleaseRepository;

import com.entities.Releases;

@RestController
public class ReleaseController {

	@Autowired
	private IReleaseRepository releaserepository;

	@RequestMapping(value = "/api/releaseProjetId", method = RequestMethod.GET)
	public List<Releases> getReleaseProjet(Integer projectID, HttpServletRequest request) {
		return releaserepository.findProjetId(Long.valueOf(projectID));

	}

	@RequestMapping(value = "/api/releaseId", method = RequestMethod.GET)
	public List<Releases> getReleaseId(Integer releaseID, HttpServletRequest request) {
		return releaserepository.findReleaseId(Long.valueOf(releaseID));
	}

}
