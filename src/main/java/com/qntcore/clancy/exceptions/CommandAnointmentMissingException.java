package com.qntcore.clancy.exceptions;

public class CommandAnointmentMissingException extends Exception {
	public CommandAnointmentMissingException(Class<?> clazz){
		super("Could not find command anointment in class: "+ clazz);
	}
}

