package com.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idExecution", "nameCompagne" })
@Entity
public class Executoin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idExecution;
	private Long idProjet;
	private Date dateExecution;
	private Date dateImport;
	private String refDBImport;
	private String nameCompagne;
	private String description;
	private Integer passed;
	private Integer failed;
	private Integer failedNiv1;
	private Integer failedNiv2;
	private Integer failedNiv3;
	private Integer failedNiv4;
	private Integer blocked;
	private Integer notRun;
	private Integer toatl;

	public Long getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}

	public Date getDateExecution() {
		return dateExecution;
	}

	public void setDateExecution(Date dateExecution) {
		this.dateExecution = dateExecution;
	}

	public Date getDateImport() {
		return dateImport;
	}

	public void setDateImport(Date dateImport) {
		this.dateImport = dateImport;
	}

	public String getRefDBImport() {
		return refDBImport;
	}

	public void setRefDBImport(String refDBImport) {
		this.refDBImport = refDBImport;
	}

	public Long getIdExecution() {
		return idExecution;
	}

	public void setIdExecution(Long idExecution) {
		this.idExecution = idExecution;
	}

	public String getNameCompagne() {
		return nameCompagne;
	}

	public void setNameCompagne(String nameCompagne) {
		this.nameCompagne = nameCompagne;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPassed() {
		return passed;
	}

	public void setPassed(Integer passed) {
		this.passed = passed;
	}

	public Integer getFailed() {
		return failed;
	}

	public void setFailed(Integer failed) {
		this.failed = failed;
	}

	public Integer getFailedNiv1() {
		return failedNiv1;
	}

	public void setFailedNiv1(Integer failedNiv1) {
		this.failedNiv1 = failedNiv1;
	}

	public Integer getFailedNiv2() {
		return failedNiv2;
	}

	public void setFailedNiv2(Integer failedNiv2) {
		this.failedNiv2 = failedNiv2;
	}

	public Integer getFailedNiv3() {
		return failedNiv3;
	}

	public void setFailedNiv3(Integer failedNiv3) {
		this.failedNiv3 = failedNiv3;
	}

	public Integer getFailedNiv4() {
		return failedNiv4;
	}

	public void setFailedNiv4(Integer failedNiv4) {
		this.failedNiv4 = failedNiv4;
	}

	public Integer getBlocked() {
		return blocked;
	}

	public void setBlocked(Integer blocked) {
		this.blocked = blocked;
	}

	public Integer getNotRun() {
		return notRun;
	}

	public void setNotRun(Integer notRun) {
		this.notRun = notRun;
	}

	public Integer getToatl() {
		return toatl;
	}

	public void setToatl(Integer toatl) {
		this.toatl = toatl;
	}

	public Executoin(Long idExecution, Long idProjet, Date dateExecution, Date dateImport, String refDBImport,
			String nameCompagne, String description, Integer passed, Integer failed, Integer failedNiv1,
			Integer failedNiv2, Integer failedNiv3, Integer failedNiv4, Integer blocked, Integer notRun,
			Integer toatl) {
		super();
		this.idExecution = idExecution;
		this.idProjet = idProjet;
		this.dateExecution = dateExecution;
		this.dateImport = dateImport;
		this.refDBImport = refDBImport;
		this.nameCompagne = nameCompagne;
		this.description = description;
		this.passed = passed;
		this.failed = failed;
		this.failedNiv1 = failedNiv1;
		this.failedNiv2 = failedNiv2;
		this.failedNiv3 = failedNiv3;
		this.failedNiv4 = failedNiv4;
		this.blocked = blocked;
		this.notRun = notRun;
		this.toatl = toatl;
	}

	public Executoin() {
		super();
		// TODO Auto-generated constructor stub
	}

}
