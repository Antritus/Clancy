package com.qntcore.clancy.arguments;

@CommandArgument
public class ArgumentFloat extends Argument<Float> {
	public ArgumentFloat(String argument) {
		super(argument, Float.class);
	}

	@Override
	public boolean parse() {
		try {
			type = Float.valueOf(argument);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}

	@Override
	public Float getParsed() {
		if (type == null){
			if (!parse()){
				return null;
			}
		}
		return type;
	}
}