package com.cn.hnust.pojo;

import java.io.Serializable;

/**
 * 数据库表中的个人信息
 * @author Administrator
 *
 */
public class Information implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String exhName;
	
	private String exhPhone;
	
	private String exhEmail;
	
	private String exhInviter;
	
	private String exhQrcode;
	
	private int exhQrcodeScann;
	
	private int exhQrcodeIsInter;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExhName() {
		return exhName;
	}

	public void setExhName(String exhName) {
		this.exhName = exhName;
	}

	public String getExhPhone() {
		return exhPhone;
	}

	public void setExhPhone(String exhPhone) {
		this.exhPhone = exhPhone;
	}

	public String getExhEmail() {
		return exhEmail;
	}

	public void setExhEmail(String exhEmail) {
		this.exhEmail = exhEmail;
	}

	public String getExhInviter() {
		return exhInviter;
	}

	public void setExhInviter(String exhInviter) {
		this.exhInviter = exhInviter;
	}

	public String getExhQrcode() {
		return exhQrcode;
	}

	public void setExhQrcode(String exhQrcode) {
		this.exhQrcode = exhQrcode;
	}

	public int getExhQrcodeScann() {
		return exhQrcodeScann;
	}

	public void setExhQrcodeScann(int exhQrcodeScann) {
		this.exhQrcodeScann = exhQrcodeScann;
	}

	public int getExhQrcodeIsInter() {
		return exhQrcodeIsInter;
	}

	public void setExhQrcodeIsInter(int exhQrcodeIsInter) {
		this.exhQrcodeIsInter = exhQrcodeIsInter;
	}
	
	
	
}
