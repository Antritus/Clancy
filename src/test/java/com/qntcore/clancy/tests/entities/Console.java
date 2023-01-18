package com.qntcore.clancy.tests.entities;

import com.qntcore.clancy.IPermission;
import com.qntcore.clancy.entity.Permissionable;

import java.util.List;

public class Console implements Permissionable {
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
	public String toString(){
		return "{\"super\":\"console\"\"name\":\""+name+"\"}";
	}

	@Override
	public boolean hasPermission(IPermission IPermission) {
		return true;
	}

	@Override
	public boolean hasPermission(String permission) {
		return false;
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
	public void addPermission(String permission) {

	}

	@Override
	public void removePermission(IPermission IPermission) {

	}

	@Override
	public void removePermission(String permission) {

	}

	@Override
	public void setPermissions(List<IPermission> IPermissions) {

	}

	@Override
	public void deletePermissions(List<IPermission> permissions) {

	}
}