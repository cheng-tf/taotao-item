package com.taotao.springboot.item.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 短链接工具类
 */
public class ShortUrlUtil {
	
	public static String urlToRequest = "https://polr.52itstyle.com/api/v2/action/shorten";

	/**
	 * 生成短链接
	 * @param url	长链接
	 */
	public static String createShortUrl(String url){
		 Map<String, String> parameters = new HashMap<String, String>();
		 parameters.put("key", "0");
		 parameters.put("url", url);
		 return HttpClientHelper.requestBodyString(urlToRequest, parameters);
	}

    public static void main(String[] args) {
    	String url = "https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-live-player.html";
    	url = createShortUrl(url);
    	System.out.println(url);
    	System.out.println("生成二维码地址:F:\\itstyle.png");
    	QrcodeUtil.createQrcode(200, 200, url, "png", "F:\\itstyles.png");
	}

}
