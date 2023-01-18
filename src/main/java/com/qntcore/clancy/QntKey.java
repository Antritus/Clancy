package com.qntcore.clancy;

public class QntKey {
	private final String key;
	private final String name;

	public QntKey(String key, String name) {
		this.key = key;
		this.name = name;
	}

	/**
	 * @return key+":"+name
	 */
	@Override
	public String toString(){
		return key+":"+name;
	}

	/**
	 * Returns the name of key part
	 * @return key part
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Returns the name part
	 * @return name part
	 */
	public String getName() {
		return name;
	}
}
