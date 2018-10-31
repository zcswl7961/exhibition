package com.cn.hnust.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.cn.hnust.pojo.Information;
@MapperScan
public interface LoginValidateDao {

	void update(@Param("userName")String userName,@Param("phone")String phone,@Param("qrcode")String qrcode,@Param("inviter")String inviter);
	//@Deprecated
	//Information find(@Param("userName")String userName,@Param("phone")String phone);
	
	Information findAndValidateScan(@Param("userName")String userName,@Param("phone")String phone);
	
	void addScanAndInter(@Param("userName")String userName,@Param("phone")String phone);
}
