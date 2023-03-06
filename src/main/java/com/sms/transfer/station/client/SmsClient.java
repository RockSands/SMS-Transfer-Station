package com.sms.transfer.station.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

@Service
public class SmsClient {
	public static final String SMSURL = "http://172.21.17.6:8091";

	public static final String SYSTEMNAME = "iron_soles_system";

	public static final String USERNAME = "iron_soles_user";

	public static final String PASSWD = "iron_soles!@_2023";

	/**
	 * 取token
	 *
	 * @param
	 * @return token
	 */
	private String getToken() {
		Map<String, String> header = new HashMap<>();
		header.put("Accept", "application/json");
		Map<String, String> sysParams = new HashMap<>(); // 系统参数
		sysParams.put("systemName", SYSTEMNAME);
		sysParams.put("passWord", SmsUtil.getencryptPassWord(SYSTEMNAME, PASSWD));
		sysParams.put("userName", USERNAME);
		String result = OkHttpUtil.get(SMSURL + "/token/getToken", header, sysParams);
		Map<String, Object> hashMap = JSON.parseObject(result, HashMap.class);
		if (result == null || result.trim().equals("")) {
			System.out.println("【短信】调用短信网关[{}]获得token,返回:空" + SMSURL + "/token/getToken");
			return "";
		}
		return hashMap.get("responseData").toString();

	}

	public void sendMessage(String phone, String content,String messageSign) {
		Object token = getToken();
		List<MessageItem> list = new ArrayList<>();
		MessageItem messageItem = new MessageItem();
		messageItem.setMessageContent(content);
		messageItem.setMessageTo(phone);
		messageItem.setMessageSign(messageSign);
		messageItem.setDeleted("0");
		list.add(messageItem);
		String sendUrl = SMSURL + "/send/message";
		Map<String, String> sysParams = new HashMap<>(); // 系统参数
		sysParams.put("messageListJson", JSONArray.toJSONString(list));
		sysParams.put("passWord", PASSWD);
		sysParams.put("sendMessageType", "2");
		sysParams.put("systemName", SYSTEMNAME);
		sysParams.put("token", String.valueOf(token));
		Map<String, String> header = new HashMap<>();
		header.put("Content-Type", "application/json");
		header.put("Accept", "application/json");
		String result = OkHttpUtil.post(sendUrl, header, sysParams);
		System.out.println("---> " +phone + " -发送短信结果-> " + result);
	}

}
