package com.qntcore.clancy.entity;

import com.qntcore.clancy.IPermission;

import java.util.List;

public interface Permissionable {
	boolean hasPermission(IPermission IPermission);
	List<IPermission> getPermissions();
	void deletePermissions();
	void addPermission(IPermission IPermission);
	void removePermission(IPermission IPermission);
	void setPermissions(List<IPermission> IPermissions);
}
