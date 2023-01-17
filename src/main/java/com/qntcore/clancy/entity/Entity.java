package com.qntcore.clancy.entity;

import java.util.ArrayList;
import java.util.List;

public interface Entity {
	void sendMessage(String message);
	String getName();

	String parseFromString(String string);
	String toString();
}
