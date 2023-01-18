package com.qntcore.clancy.entity;

public interface Entity extends Permissionable{
	void sendMessage(String message);
	String getName();

	String parseFromString(String string);
	String toString();

}
