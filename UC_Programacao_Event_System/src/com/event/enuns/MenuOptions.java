package com.event.enuns;

public enum MenuOptions {

	esporte(1), festa(2), curso(3), congresso(4);

	private final int type;

	MenuOptions(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

}
