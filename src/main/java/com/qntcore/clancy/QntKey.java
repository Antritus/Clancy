package com.qntcore.clancy;

public class QntKey {
	private final String key;
	private final String name;

	public QntKey(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}
}
