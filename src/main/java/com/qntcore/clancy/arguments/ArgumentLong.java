package com.qntcore.clancy.arguments;

@CommandArgument
public class ArgumentLong extends Argument<Long> {
	public ArgumentLong(String argument) {
		super(argument, Long.class);
	}

	@Override
	public boolean parse() {
		try{
			type = Long.parseLong(argument);
			return true;
		}catch (NumberFormatException e){
			return false;
		}
	}

	@Override
	public Long getParsed() {
		if (type == null){
			if (!parse()) {
				return null;
			}
		}
		return type;
	}
}
