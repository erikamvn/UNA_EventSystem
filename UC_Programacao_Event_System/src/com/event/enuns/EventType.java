package com.event.enuns;

public enum EventType {

	esporte(1), festa(2), curso(3), congresso(4), show(5);

	private final int type;

	EventType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

}
