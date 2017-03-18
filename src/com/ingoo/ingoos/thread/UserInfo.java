package com.ingoo.ingoos.thread;

public class UserInfo {

	public UserInfo() {
	}

	private int mUserId;
	private String mUserName;
	private int age;
	private String pwd;

	@Override
	public String toString() {
		return "UserInfo:" +
				"mUserId: " + mUserId +
				"mUserName: " + mUserName +
				", pwd=" + pwd +
				", age=" + age + "]";
	}

	public int getId() {
		return mUserId;
	}

	public void setId(int id) {
		this.mUserId = id;
	}

	public String getName() {
		return mUserName;
	}

	public void setName(String name) {
		this.mUserName = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
