package com.gamble.group_languages.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Languages {

	@Size(min = 2, max = 20)
	private String name;
	@Size(min = 2, max = 20)
	private String creator;
	@NotNull
	private String currentVersion;
	
	public Languages() {
	}
	
	public Languages(String name, String creator, String currentVersion) {
		super();
		this.name = name;
		this.creator = creator;
		this.currentVersion = currentVersion;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	
	
	
}
