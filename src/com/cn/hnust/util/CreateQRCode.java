package com.cn.hnust.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;


/**
 * 生成二维码信息数据
 * @author zcg
 *
 */
public class CreateQRCode {
	/**
	 * 根据传递过来的用户名称和号码数据，加密形成对应的二维码数据信息
	 * 			返回二维码的实际的服务的路径地址，提醒用户进行截图保存
	 * 			传入的qRcodeUrl具体的数据中需要指定二维码中的具体的数据，并将指定的路径的信息显示出来，回调信息
	 * @param UserName
	 * @param phone:一人一码信息
	 * @param sendPath
	 * @return 返回的数据信息为二维码的实际的路径地址信息
	 */
	public static String creatQRCode(String message,String sendPath,String phone,String userName){
		String truthPath="";
		try { 
           Qrcode qrcodeHandler = new Qrcode(); 
           //设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小 
           qrcodeHandler.setQrcodeErrorCorrect('H'); 
           qrcodeHandler.setQrcodeEncodeMode('B'); 
           qrcodeHandler.setQrcodeVersion(5); 
           System.out.println(message); 
           //int imgSize = 67 + 12 * (size - 1);
           byte[] contentBytes = message.getBytes("gb2312"); 
           BufferedImage bufImg = new BufferedImage(300, 300, 
                   BufferedImage.TYPE_INT_RGB); 
           Graphics2D gs = bufImg.createGraphics(); 
           gs.setBackground(Color.WHITE); 
           gs.clearRect(0, 0, 115, 115); 
           // 设定图像颜色> BLACK 
           gs.setColor(Color.BLACK); 
           // 设置偏移量 不设置可能导致解析出错 
           int pixoff = 2; 
           // 输出内容> 二维码 
           if (contentBytes.length > 0 && contentBytes.length < 800) { 
               boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes); 
               for (int i = 0; i < codeOut.length; i++) { 
                   for (int j = 0; j < codeOut.length; j++) { 
                       if (codeOut[j][i]) { 
                           gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3); 
                       } 
                   } 
               } 
           } else { 
               System.err.println("QRCode content bytes length = " 
                       + contentBytes.length + " not in [ 0,120 ]. "); 
           } 
           gs.dispose(); 
           bufImg.flush(); 
           truthPath=sendPath+"\\"+PinYinUtils.getFullSpell(userName)+phone+".png";
           File imgFile = new File(truthPath); 
           truthPath="\\uploadFile\\"+PinYinUtils.getFullSpell(userName)+phone+".png";
           // 生成二维码QRCode图片 
           ImageIO.write(bufImg, "png", imgFile); 
		} catch (Exception e) { 
           e.printStackTrace(); 
       	} 
		System.out.println("truthPath=="+truthPath);
		return truthPath;
	}
	
}
