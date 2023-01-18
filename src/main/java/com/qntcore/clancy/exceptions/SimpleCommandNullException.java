package com.qntcore.clancy.exceptions;

public class SimpleCommandNullException extends Exception{
	public SimpleCommandNullException(){
		super("Simple command found null!");
	}
	@SuppressWarnings("unused")
	public SimpleCommandNullException(boolean x){
		super("Simple command found null! Children of simple commands must have their own @Command!");
	}
}
