package com.qntcore.clancy.tests.entities;

import com.qntcore.clancy.IPermission;
import com.qntcore.clancy.entity.Entity;

import java.util.List;

public class Console implements Entity {
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

	@Override
	public boolean hasPermission(IPermission IPermission) {
		return true;
	}

	@Override
	public List<IPermission> getPermissions() {
		return null;
	}

	@Override
	public void deletePermissions() {
	}

	@Override
	public void addPermission(IPermission IPermission) {
	}

	@Override
	public void removePermission(IPermission IPermission) {

	}

	@Override
	public void setPermissions(List<IPermission> IPermissions) {

	}
}