package com.qntcore.clancy.entity;

public class Player implements Entity{
	private String name;
	public Player(String name){
		this.name = name;
	}
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
		if (name.equalsIgnoreCase("console")){
			return null;
		}
		name = string;
		return name;
	}

	@Override
	public String toString(){
		return "{\"name\":\""+name+"\"}";
	}
}
