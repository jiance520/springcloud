package com.mytable_entity;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @version ʱ�䣺2018-5-2 ����6:51:59
 *
 */
public class UserData {
	Calendar cd = Calendar.getInstance();
	private String userName;
	private String userId;
	private String name;
	private String pwd10;
	private String pwd6;
	private String secondPwd;
	private String tel;
	private String tel1;
	private String email;
	private String email1;
	private String registerDate;
	private String pwdSafe;
	private String note;
	private String strNum;
	private ArrayList<ModifyRecord> recordList;
	public UserData() {
		super();
	}
	public UserData(String userName, String pwd10) {
		super();
		setUserName(userName);
		setPwd10(pwd10);
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd10() {
		return pwd10;
	}
	public void setPwd10(String pwd10) {
		this.pwd10 = pwd10;
	}
	public String getPwd6() {
		return pwd6;
	}
	public void setPwd6(String pwd6) {
		this.pwd6 = pwd6;
	}
	public String getSecondPwd() {
		return secondPwd;
	}
	public void setSecondPwd(String secondPwd) {
		this.secondPwd = secondPwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTel1() {
		return tel1;
	}
	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getPwdSafe() {
		return pwdSafe;
	}
	public void setPwdSafe(String pwdSafe) {
		this.pwdSafe = pwdSafe;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getStrNum() {
		return strNum;
	}
	public void setStrNum(String strNum) {
		this.strNum = strNum;
	}
	public ArrayList<ModifyRecord> getRecordList() {
		return recordList;
	}
	public void setRecordList(ArrayList<ModifyRecord> recordList) {
		this.recordList = recordList;
	}	
}
