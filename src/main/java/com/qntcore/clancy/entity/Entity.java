package com.qntcore.clancy.entity;

public interface Entity{
	/**
	 * Sends message to entity.
	 * @param message the message to be sent
	 */
	void sendMessage(String message);

	/**
	 * Gets the name of the entity given
	 * @return name of entity
	 */
	String getName();
}
