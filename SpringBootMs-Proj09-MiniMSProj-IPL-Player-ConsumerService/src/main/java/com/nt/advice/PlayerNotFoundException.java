package com.nt.advice;

public class PlayerNotFoundException extends RuntimeException {
	
	public PlayerNotFoundException() {
		super();
		}
	public   PlayerNotFoundException(String msg) {
		super(msg);
	}
	

}
