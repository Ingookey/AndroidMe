package com.ingoo.ingoos.data;

public class UserInfo {
	private static String TAG = "ingoo/UserInfo";
	
	private int mUserId;
	private String mUserName;
	private int mAge;
	private String mPwd;
	
	public UserInfo(int userId, String userName) {
		this(userId, userName, 0, null);
	}

	public UserInfo(int userId, String userName, int userAge, String userPwd) {
		this.mUserId = userId;
		this.mUserName = userName;
		this.mAge = userAge;
		this.mPwd = userPwd;
	}

	@Override
	public String toString() {
		return "UserInfo:" +
				"mUserId: " + mUserId +
				"mUserName: " + mUserName +
				", pwd=" + mPwd +
				", age=" + mAge + "]";
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
		return mPwd;
	}

	public void setPwd(String pwd) {
		this.mPwd = pwd;
	}

	public int getAge() {
		return mAge;
	}

	public void setAge(int age) {
		this.mAge = age;
	}

}
