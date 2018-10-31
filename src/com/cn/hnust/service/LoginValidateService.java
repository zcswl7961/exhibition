package com.cn.hnust.service;


import java.util.Random;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cn.hnust.dao.LoginValidateDao;
import com.cn.hnust.pojo.Information;
import com.cn.hnust.util.MessagePropertiesUtil;
import com.cn.hnust.util.PinYinUtils;
import com.cn.hnust.util.QRCodeUtil;
/**
 * 验证，解析和调用接口生成对应的二维码数据信息
 * @author zcg
 * @time 2017-9-18
 *
 */
@Service("loginValidateSerice")
public class LoginValidateService {
	
	private static Logger logger = LoggerFactory.getLogger(LoginValidateService.class);
	@Resource
	private LoginValidateDao loginValidateDao;
	
	private String logUrl=MessagePropertiesUtil.getProperties("logUrl");
	/**
	 * 进行验证操作，验证的是用户名和电话号码信息数据，
	 * @param information 传递的参数信息
	 * @param sendPath
	 * @return
	 */
	public String validate(Information information,String sendPath,String bathPath){
		//二维码信息中传递的url数据信息(二维码中的具体的信息)
		//前端可能会传入空格，和数据其他信息,首先是去除前后中间的空格信息，然后是将字符转成小写类型
		String userName=information.getExhName();
		String phone=information.getExhPhone();
		String email=information.getExhEmail();
		String inviter=information.getExhInviter();
		if(inviter==null){
			inviter="";
		}
		String qrCodeUrlMessage=bathPath+"confirm.html?userName="+userName+"&phone="+phone+"&email="+email+"&inviter="+inviter;
		userName=userName.replace(" ","").toLowerCase();
		phone=phone.replace(" ", "");
		String filePath="";
		//将该md5的数据信息插入到指定的数据表中
//		String userNameStr="%"+userName+"%";
		String userNameStr=userName;
		Information find = loginValidateDao.findAndValidateScan(userNameStr,phone);
		if(find==null)
			return setJson("0","");
		//生成对应的二维码，并将二维码数据封装到指定的文件路径上,并返回改文件路径的地址信息
		String _qrcode=find.getExhQrcode();
		if(!"".equals(_qrcode)&&_qrcode!=null)
			//表示这个人已经生成了二维码数据信息，将二维码url回传过来
			return setJson("1",_qrcode);
		try {
			logger.info("接口自动生成了相应邀请人的二维码信息，该邀请人的用户名和号码为:{}",userName+":"+phone);
			int randomInt=new Random().nextInt(100) +1;
			String fullSpell="";
			try{
				fullSpell=PinYinUtils.getFullSpell(userName);
			}catch (Exception e) {
				int random=new Random().nextInt(1000)+1;
				fullSpell=String.valueOf(random);
			}
			String sendFileName=fullSpell+phone+randomInt;
			filePath = QRCodeUtil.encode(qrCodeUrlMessage, logUrl, sendPath, true,sendFileName);
			//如果生成了二维码数据，将二维码数据存储到数据库中的位置
			loginValidateDao.update(userName, phone, bathPath+filePath,inviter);	
		} catch (Exception e) {
			//如果系统出现错误，系统返回扫码失败
			e.printStackTrace();
			return setJson("0","");
		}
		return setJson("1",bathPath+filePath);
	}
	/**
	 * 		验证用户信息，如果数据库字段中exh_qrcode_scan中的次数为2，表示该二维码数据失效
	 * @param userName
	 * @param phone
	 * @return
	 */
	public String identify(Information _information){
		String userName=_information.getExhName();
		String phone=_information.getExhPhone();
		userName=userName.replace(" ","").toLowerCase();
		phone=phone.replace(" ", "");
//		String userNameStr="%"+userName+"%";
		String userNameStr=userName;
		Information information=loginValidateDao.findAndValidateScan(userNameStr,phone);
		if(information.getExhQrcodeScann()==2)
			return setJson("0","该二维码已失效");
		//插入并返回使用的json信息
		loginValidateDao.addScanAndInter(userNameStr,phone);
		return setJson("1","验证成功");
	} 
	/**
	 * 生成json信息
	 * 
	 */
	private String setJson(String flag,String message){
		JSONObject object=new JSONObject();
		object.put("flag", flag);
		object.put("qrcodeUrl", message);
		return object.toString();
	}
}
