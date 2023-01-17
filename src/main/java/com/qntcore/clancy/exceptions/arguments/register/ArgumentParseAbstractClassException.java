package com.qntcore.clancy.exceptions.arguments.register;

import com.qntcore.clancy.exceptions.arguments.ArgumentParseException;

public class ArgumentParseAbstractClassException extends ArgumentParseException {
	public ArgumentParseAbstractClassException(Class<?> clazz) {
		super(clazz, "could not parse class cause the class is abstract!");
	}
}
