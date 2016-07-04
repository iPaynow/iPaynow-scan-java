package com.ipaynow.notify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipaynow.utils.BackEndRequest;
import com.ipaynow.utils.FormDateReportConvertor;
import com.ipaynow.utils.MD5Facade;
import com.ipaynow.utils.NowPayUtils;

/**
 * 服务器端异步通知商户Servlet
 * @author christ
 *
 */
public class MchBackNotifyServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2358940163943334363L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
        //拿到自己应用的md5Key
        InputStream propertiesInput = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        properties.load(propertiesInput);
        String md5Key = (String) properties.get("md5Key");
        //String md5Key = "79Y5Nv14E2FC1pvb938fOgk6OkV8UY2t";
		
        
        
		
		//String reportContent = "appId=1445486158399220&channelOrderNo=1000360223201510261342761681&deviceType=06&funcode=N001&mhtCharset=UTF-8&mhtCurrencyType=156&mhtOrderAmt=1&mhtOrderName=1863%E6%B8%B8%E6%88%8F%E4%BA%A4%E6%98%93%E7%BD%91&mhtOrderNo=5727838078061970104&mhtOrderStartTime=20151026181911&mhtOrderType=01&nowPayOrderNo=2004115215000380&payChannelType=13&payConsumerId=orUBxuETzrpajO7Rd7dHQldUfZm8&signType=MD5&signature=241519892b83b3e16b2e86a29e40a73d&tradeStatus=A001";		
		//Map<String,String> dataMap = FormDateReportConvertor.parseFormDataPatternReportWithDecode(reportContent, "UTF-8", "UTF-8");
		
		//去除签名类型和签名值
        //dataMap.remove("signType");
        //String signature = dataMap.remove("signature");


        //验证签名
        //boolean isValidSignature = MD5Facade.validateFormDataParamMD5(dataMap,md5Key,signature);
        
		//验证签名
		boolean isValidSignature = false;
		try{
		isValidSignature =  NowPayUtils.verifySignature(req, md5Key);
		}catch(Exception e){
			e.printStackTrace();
		}
        System.out.println("验签结果："+isValidSignature);
        
        //获取请求体
        //BackEndRequest backEndRequest = NowPayUtils.buildBackEndRequest(req);
        //System.out.println(backEndRequest.getAppId());
        
        //一定要返回
        if(isValidSignature)
            resp.getOutputStream().write("success=Y".getBytes());
        else
        	resp.getOutputStream().write("success=N".getBytes());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}