package com.qntcore.clancy.entity;

import com.qntcore.clancy.IPermission;

import java.util.List;

@SuppressWarnings("unused")
public interface Permissionable extends Entity {
	/**
	 * @param permission the permission to check for
	 * @see IPermission
	 * @see Entity
	 * @return has permission
	 */
	boolean hasPermission(IPermission permission);
	/**
	 * @param permission the permission to check for
	 * @see IPermission
	 * @see Entity
	 * @return has permission
	 */
	boolean hasPermission(String permission);

	/**
	 * Gets the permissions from entity
	 * @see IPermission
	 * @see Entity
	 * @return permissions
	 */
	List<IPermission> getPermissions();

	/**
	 * Deletes all the permissions from entity
	 * @see IPermission
	 * @see Entity
	 */
	void deletePermissions();

	/**
	 * Adds permission to entity's permissions
	 * @param permission permission to give
	 * @see IPermission
	 * @see Entity
	 */
	void addPermission(IPermission permission);
	/**
	 * Adds permission to entity's permissions
	 * @param permission permission to give
	 * @see IPermission
	 * @see Entity
	 */
	void addPermission(String permission);
	/**
	 * Removes permission to entity's permissions
	 * @param permission permission to give
	 * @see IPermission
	 * @see Entity
	 */
	void removePermission(IPermission permission);
	/**
	 * Removes permission to entity's permissions
	 * @param permission permission to give
	 * @see IPermission
	 * @see Entity
	 */
	void removePermission(String permission);
	/**
	 * Sets the permissions of entity to given new state
	 * @param permissions the permissions to set the entity's own permissions to new state
	 * @see IPermission
	 * @see Entity
	 */
	void setPermissions(List<IPermission> permissions);

	/**
	 * Deletes the permissions from entity
	 * @param permissions the permissions to set the entity's permissions to.
	 * @see IPermission
	 * @see Entity
	 */
	void deletePermissions(List<IPermission> permissions);
}
