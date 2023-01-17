package com.qntcore.clancy.exceptions.arguments;

public class ArgumentParseException extends Exception{
	public ArgumentParseException(Class<?> clazz, String error){
		super("Error while parsing argument " + clazz + " " + error);
	}
}
