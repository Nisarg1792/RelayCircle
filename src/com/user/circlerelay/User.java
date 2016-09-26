package com.user.circlerelay;

import java.io.Serializable;

public class User implements Serializable {

	private String userName = null;
	private String color = null;
	private String xCoordinate = "";
	private String yCoordinate = "";

	public String getUserName() {
		return userName;
	}

	public void setUserName(String user) {
		this.userName = user;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(String xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public String getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(String yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public String toString(){
		return "userName="+userName+",color="+color;
	}
}
