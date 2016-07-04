<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
<form action="http://posp.ipaynow.cn:10808/mobilewaptest/topay" METHOD=POST>
<input type=hidden name="funcode" value="WP001"/>
应用ID：<input type=text name="appId" value="1409833031429403"/><br>
应用密钥：<input type=text name="appKey" value="BT9wS4OQI6LKMJJdrFmXumnoR0fsFBgr"/><br>
<input type=hidden name="mhtOrderNo" value=""/>
订单名称：<input type=text name="mhtOrderName" value="merchantTest"/><br>
<input type=hidden name="mhtCurrencyType" value="156"/>
订单金额：<input type=text name="mhtOrderAmt" value="1"/><br>
订单详情：<input type=text name="mhtOrderDetail" value="mhtOrderDetail"/><br>
<input type=hidden name="mhtOrderType" value="01"/>
<input type=hidden name="mhtOrderStartTime" value="20140911201030"/><br>
<input type=hidden name="notifyUrl" value="http://localhost:10802/"/>
<input type=hidden name="frontNotifyUrl" value="http://posp.ipaynow.cn:10808/cpgatetest/frontnotify"/>
<input type=hidden name="mhtCharset" value="UTF-8">
设备类型：<input type=text name="deviceType" value="06">

<button type=submit>submit</button>
</form>
</body>
</html> 