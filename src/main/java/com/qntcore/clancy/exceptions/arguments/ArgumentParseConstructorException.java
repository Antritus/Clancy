package com.qntcore.clancy.exceptions.arguments;


public class ArgumentParseConstructorException extends ArgumentParseException {
	public ArgumentParseConstructorException(Class<?> clazz) {
		super(clazz, "the constructor is not the right format of constructor(String.class)");
	}
}
