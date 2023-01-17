package com.qntcore.clancy.arguments;

@CommandArgument
public class ArgumentString extends Argument<String> {
	public ArgumentString(String argument) {
		super(argument, String.class);
	}

	@Override
	public boolean parse() {
		return true;
	}

	@Override
	public String getParsed() {
		if (type == null){
			type = argument;
		}
		return type;
	}
}
