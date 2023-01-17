package com.qntcore.clancy.exceptions;

import com.qntcore.clancy.Command;

public class CommandAlreadyRegisteredException extends Exception{
	public CommandAlreadyRegisteredException(Command command){
		super("Already found command with type \""+command.key()+"\" and name \""+command.name()+"\"!");
	}
}
