package com.mytable_entity;

import java.util.Calendar;

/**
 * @version ʱ�䣺2018-5-2 ����6:52:48
 *
 */
public class ModifyRecord {
	Calendar cd = Calendar.getInstance();
	private String recordId;
	private String oldPwd10;
	private String oldPwd6;
	private String oldSecondPwd;
	private String oldModifyDate;
	private String oldPwdSafe;
	private String oldStrNum;
	public ModifyRecord() {
		super();
	}
	public ModifyRecord(String oldPwd10) {
		super();
		setOldPwd10(oldPwd10);
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getOldPwd10() {
		return oldPwd10;
	}
	public void setOldPwd10(String oldPwd10) {
		this.oldPwd10 = oldPwd10;
	}
	public String getOldPwd6() {
		return oldPwd6;
	}
	public void setOldPwd6(String oldPwd6) {
		this.oldPwd6 = oldPwd6;
	}
	public String getOldSecondPwd() {
		return oldSecondPwd;
	}
	public void setOldSecondPwd(String oldSecondPwd) {
		this.oldSecondPwd = oldSecondPwd;
	}
	public String getOldModifyDate() {
		return oldModifyDate;
	}
	public void setOldModifyDate(String oldModifyDate) {
		this.oldModifyDate = oldModifyDate;
	}
	public String getOldPwdSafe() {
		return oldPwdSafe;
	}
	public void setOldPwdSafe(String oldPwdSafe) {
		this.oldPwdSafe = oldPwdSafe;
	}
	public String getOldStrNum() {
		return oldStrNum;
	}
	public void setOldStrNum(String oldStrNum) {
		this.oldStrNum = oldStrNum;
	}
	
}
