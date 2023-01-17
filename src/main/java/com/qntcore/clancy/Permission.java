package com.qntcore.clancy;

import java.util.ArrayList;
import java.util.List;

public class Permission {
	private static List<String> permissions = new ArrayList<>();

	String permission;
	public Permission(String permission){
		StringBuilder stringBuilder = new StringBuilder("");
		for (String string : permission.split("\\.")){
			if (stringBuilder.toString().equalsIgnoreCase("")) {
				stringBuilder.append(string);
			}else{
				stringBuilder.append(".").append(string);
			}
			boolean contains = false;
			for (String perm : permissions){
				if (perm.equalsIgnoreCase(stringBuilder.toString())){
					contains = true;
					break;
				}
			}
			if (!contains){
				permissions.add(stringBuilder.toString());
			}
		}
		this.permission = permission;
		if (permissions.contains(permission)){
			return;
		}
		permissions.add(permission);
	}


	public static boolean exists(String permission){
		String[] permSplit = permission.split("\\.");
		boolean cont = true;
		for (String perms : permissions){
			cont = true;
			String[] permsSplit = perms.split("\\.");
			for (int i = 0; i < permSplit.length-1; i++){
				if (permSplit[i] == null) {
					cont = false;
					break;
				}
				if (!permSplit[i] .equalsIgnoreCase(permsSplit[i])){
					cont = false;
					break;
				}
			}
			System.out.println(perms +" | "+permission);
			if (cont) {
				if (permsSplit.length > permSplit.length){
					cont = false;
				} else if (permsSplit.length < permSplit.length) {
					cont = false;
				}else {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean exists(Permission permission){
		return exists(permission.permission);
	}
}
