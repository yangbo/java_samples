package org.gradle.jaxb;

public class BinaryData extends BaseData {
	private int size;

	public BinaryData() {
		this.type = "binary";
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
