package com.ipaynow.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipaynow.utils.MD5Facade;

public class ToPay extends HttpServlet{
	
	private static final long serialVersionUID = -3240794927782965682L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String funcode = req.getParameter("funcode");
		String appId = req.getParameter("appId");
		String mhtOrderNo = System.currentTimeMillis()+"";
		String mhtOrderName = req.getParameter("mhtOrderName");
		String mhtCurrencyType = req.getParameter("mhtCurrencyType");
		String mhtOrderAmt = req.getParameter("mhtOrderAmt");
		String mhtOrderDetail = req.getParameter("mhtOrderDetail");
		String mhtOrderType = req.getParameter("mhtOrderType");
		
		Date nowTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String mhtOrderStartTime = dateFormat.format(nowTime);
		String notifyUrl = req.getParameter("notifyUrl");
		String frontNotifyUrl = req.getParameter("frontNotifyUrl");
		String mhtCharset = req.getParameter("mhtCharset");
		String deviceType = req.getParameter("deviceType");
		String payChannelType = req.getParameter("payChannelType");
		String appKey = req.getParameter("appKey");
		
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("appId", appId);
		dataMap.put("mhtOrderNo", mhtOrderNo);
		dataMap.put("mhtOrderName", mhtOrderName);
		dataMap.put("mhtCurrencyType", mhtCurrencyType);
		dataMap.put("mhtOrderAmt", mhtOrderAmt);
		dataMap.put("mhtOrderDetail", mhtOrderDetail);
		dataMap.put("mhtOrderType", mhtOrderType);
		dataMap.put("mhtOrderStartTime", mhtOrderStartTime);
		dataMap.put("notifyUrl", notifyUrl);
		dataMap.put("frontNotifyUrl", frontNotifyUrl);
		dataMap.put("mhtCharset", mhtCharset);
		dataMap.put("payChannelType", payChannelType);
		
		String mhtSignature = MD5Facade.getFormDataParamMD5(dataMap, appKey, "UTF-8");
		
		req.setAttribute("appId", appId);
		req.setAttribute("mhtOrderNo", mhtOrderNo);
		req.setAttribute("mhtOrderName", mhtOrderName);
		req.setAttribute("mhtCurrencyType", mhtCurrencyType);
		req.setAttribute("mhtOrderAmt", mhtOrderAmt);
		req.setAttribute("mhtOrderDetail", mhtOrderDetail);
		req.setAttribute("mhtOrderType", mhtOrderType);
		req.setAttribute("mhtOrderStartTime", mhtOrderStartTime);
		req.setAttribute("notifyUrl", notifyUrl);
		req.setAttribute("frontNotifyUrl", frontNotifyUrl);
		req.setAttribute("mhtCharset", mhtCharset);
		
		req.setAttribute("mhtSignType", "MD5");
		req.setAttribute("mhtSignature", mhtSignature);
		req.setAttribute("funcode", funcode);
		req.setAttribute("deviceType", deviceType);
		req.setAttribute("payChannelType", payChannelType);
		
		req.getRequestDispatcher("topay.jsp").forward(req, resp);				
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
