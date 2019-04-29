package com.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "idRelease", "dateRelease" })
@Entity
public class Releases {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idRelease;
	private Long idProjet;
	private Date dateRelease;
	private String nameRelease;
	private Integer etatRelease;
	private String description;
	private String sonar1Release;
	private String sonar2Release;
	private String sonar3Release;
	private String sonar4Release;
	private Integer passed;
	private Integer failed;
	private Integer failedNiv1;
	private Integer failedNiv2;
	private Integer failedNiv3;
	private Integer failedNiv4;
	private Integer blocked;
	private Integer notRun;

	public Long getIdRelease() {
		return idRelease;
	}

	public void setIdRelease(Long idRelease) {
		this.idRelease = idRelease;
	}

	public Long getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}

	public Date getDateRelease() {
		return dateRelease;
	}

	public void setDateRelease(Date dateRelease) {
		this.dateRelease = dateRelease;
	}

	public String getNameRelease() {
		return nameRelease;
	}

	public void setNameRelease(String nameRelease) {
		this.nameRelease = nameRelease;
	}

	public Integer getEtatRelease() {
		return etatRelease;
	}

	public void setEtatRelease(Integer etatRelease) {
		this.etatRelease = etatRelease;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSonar1Release() {
		return sonar1Release;
	}

	public void setSonar1Release(String sonar1Release) {
		this.sonar1Release = sonar1Release;
	}

	public String getSonar2Release() {
		return sonar2Release;
	}

	public void setSonar2Release(String sonar2Release) {
		this.sonar2Release = sonar2Release;
	}

	public String getSonar3Release() {
		return sonar3Release;
	}

	public void setSonar3Release(String sonar3Release) {
		this.sonar3Release = sonar3Release;
	}

	public String getSonar4Release() {
		return sonar4Release;
	}

	public void setSonar4Release(String sonar4Release) {
		this.sonar4Release = sonar4Release;
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

	public Releases(Long idRelease, Long idProjet, Date dateRelease, String nameRelease, Integer etatRelease,
			String description, String sonar1Release, String sonar2Release, String sonar3Release, String sonar4Release,
			Integer passed, Integer failed, Integer failedNiv1, Integer failedNiv2, Integer failedNiv3,
			Integer failedNiv4, Integer blocked, Integer notRun) {
		super();
		this.idRelease = idRelease;
		this.idProjet = idProjet;
		this.dateRelease = dateRelease;
		this.nameRelease = nameRelease;
		this.etatRelease = etatRelease;
		this.description = description;
		this.sonar1Release = sonar1Release;
		this.sonar2Release = sonar2Release;
		this.sonar3Release = sonar3Release;
		this.sonar4Release = sonar4Release;
		this.passed = passed;
		this.failed = failed;
		this.failedNiv1 = failedNiv1;
		this.failedNiv2 = failedNiv2;
		this.failedNiv3 = failedNiv3;
		this.failedNiv4 = failedNiv4;
		this.blocked = blocked;
		this.notRun = notRun;
	}

	public Releases() {
		super();
		// TODO Auto-generated constructor stub
	}

}
