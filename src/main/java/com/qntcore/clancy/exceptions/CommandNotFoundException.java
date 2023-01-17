package com.qntcore.clancy.exceptions;

import com.qntcore.clancy.QntKey;

import java.util.Arrays;

public class CommandNotFoundException extends Exception{
	public CommandNotFoundException(QntKey key) {
		super("Could not find a command with key \"" + key.getKey() + "\" and name \"" + key.getName() + "\"!");
	}
	public CommandNotFoundException(String... categories) {
		super("Could not find a command with categories: "+ Arrays.toString(categories) +"!");
	}
	public CommandNotFoundException(String type, String... types) {
		super("Could not find a command with "+type+": "+ Arrays.toString(types) +"!");
	}
}
