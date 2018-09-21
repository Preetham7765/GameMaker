package com.components;

import java.io.Serializable;

public class Clock implements Serializable {

	private static final long serialVersionUID = 8L;
	
	public long milisecondsElapsed;
	public final int DELTA;

	public Clock(int delta) {
		this.DELTA = delta;
		milisecondsElapsed = 0;
	}

	public void tick(int milisPassed) {
		milisecondsElapsed += milisPassed;
	}

	public String getTime() {
		if (getSeconds() >= 10) {
			return Integer.toString(getMinutes()) + ":" + Integer.toString(getSeconds());
		} else {
			return Integer.toString(getMinutes()) + ":0" + Integer.toString(getSeconds());
		}
	}

	public void reset() {
		milisecondsElapsed = 0;
	}
	
	public void setTime(long ms) {
		this.milisecondsElapsed = ms;
	}

	private int getMinutes() {
		return (int) (milisecondsElapsed / 60000);
	}

	private int getSeconds() {
		return (int) ((milisecondsElapsed / 1000) % 60);
	}
}
