package com.qntcore.clancy.entity;

public class Console implements ConsoleEntity{
	private String name = "console";
	@Override
	public void sendMessage(String message) {
		System.out.println(name + " | " + message);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String parseFromString(String string){
		if (!name.equalsIgnoreCase("console")){
			return null;
		}
		name = string;
		return name;
	}
	@Override
	public String toString(){
		return "{\"super\":\"console\"\"name\":\""+name+"\"}";
	}
}