package com.qntcore.clancy.arguments;

@CommandArgument
public class ArgumentInteger extends Argument<Integer> {
	public ArgumentInteger(String argument) {
		super(argument, Integer.class);
	}

	@Override
	public boolean parse() {
		try {
			type = Integer.parseInt(argument);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}

	@Override
	public Integer getParsed() {
		if (type == null){
			if (!parse()){
				return null;
			}
		}
		return type;
	}
}
