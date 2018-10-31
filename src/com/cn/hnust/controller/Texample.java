package com.cn.hnust.controller;

import com.cn.hnust.util.PinYinUtils;

public class Texample {

	public static void main(String[] args) {
		String value=PinYinUtils.getFullSpell("谢䅞瀚");
		System.out.println(value);
	}
}
