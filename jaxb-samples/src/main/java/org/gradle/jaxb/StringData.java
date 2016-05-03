package org.gradle.jaxb;

public class StringData extends BaseData {
	private String content;

	public StringData() {
		this.type = "string";
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
