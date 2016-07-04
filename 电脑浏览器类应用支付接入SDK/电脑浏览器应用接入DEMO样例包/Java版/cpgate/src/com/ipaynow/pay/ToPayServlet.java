package com.ipaynow.pay;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipaynow.utils.NowPayUtils;
import com.ipaynow.utils.PayRequest;

/**
 * 支付表单数据准备
 * @author christ
 *
 */
public class ToPayServlet extends HttpServlet{
	
	private static final long serialVersionUID = -3240794927782965682L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//商户自己的订单数据
		String funcode = req.getParameter("funcode");
		String appId = req.getParameter("appId");
		String mhtOrderNo = System.currentTimeMillis()+"";
		String mhtOrderName = req.getParameter("mhtOrderName");
		String mhtCurrencyType = req.getParameter("mhtCurrencyType");
		String mhtOrderAmt = req.getParameter("mhtOrderAmt");
		String mhtOrderDetail = req.getParameter("mhtOrderDetail");
		String mhtOrderType = req.getParameter("mhtOrderType");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String mhtOrderStartTime = dateFormat.format(new Date());
		String notifyUrl = req.getParameter("notifyUrl");
		String frontNotifyUrl = req.getParameter("frontNotifyUrl");
		String mhtCharset = req.getParameter("mhtCharset");
		String deviceType = req.getParameter("deviceType");
		String payChannelType = req.getParameter("payChannelType");
		String appKey = req.getParameter("appKey");
		String mhtReserved = req.getParameter("mhtReserved");
		String outputType = req.getParameter("outputType");
		
	
		//构建请求对象
		PayRequest payRequest = new PayRequest();
		payRequest.setAppId(appId);
		payRequest.setMhtOrderNo(mhtOrderNo);
		payRequest.setMhtOrderName(mhtOrderName);
		payRequest.setMhtCurrencyType(mhtCurrencyType);
		payRequest.setMhtOrderAmt(mhtOrderAmt);
		payRequest.setMhtOrderDetail(mhtOrderDetail);
		
		payRequest.setMhtOrderStartTime(mhtOrderStartTime);
		payRequest.setNotifyUrl(notifyUrl);
		payRequest.setFrontNotifyUrl(frontNotifyUrl);
		payRequest.setPayChannelType(payChannelType);
		payRequest.setMhtReserved(mhtReserved);
		payRequest.setOutputType(outputType);
		
		//构建请求对象，并生成签名
		String signature = "";
		boolean result = NowPayUtils.buildPayData(payRequest, appKey);
		if(true == result){
			signature = payRequest.getMhtSignature();
		}
		
		req.setAttribute("outputType", outputType);
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
		req.setAttribute("mhtSignature", payRequest.getMhtSignature());
		req.setAttribute("funcode", funcode);
		req.setAttribute("deviceType", deviceType);
		req.setAttribute("payChannelType", payChannelType);
		req.setAttribute("mhtReserved", mhtReserved);
		
		req.getRequestDispatcher("topay.jsp").forward(req, resp);				
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
