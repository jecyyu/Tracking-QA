<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Readme Page</title>
</head>
<body>
	计数接口：
	<table border=1 width="100%" style="table-layout:fixed;">
		<tr>
			<td width="15%">Protobuf Madhouse</td>
			<td  width="80%" style="WORD-WRAP: break-word">
			<a href="<%=basePath %>/Tracking-QA/madhouse-protobuf?url=114.80.90.115:9002/adcall_p/bidrequest&adspaceid=BC00298CF8C79D9E&adtype=2&width=160&height=130&debug=1&ip=37.70.188.68&pid=c2f394befcff3f03&media=0&pcat=12&bid=0000000000001&carrier=0&apitype=2&pkgname=com.autotest.pkgname&appname=QA-Auto&density=&lon=&lat=&cell=&mcell=&conn=0&idfa=WNlJWMwM2YhVDOiZ&oid=WNlJWMwM2YhVDOiZ&wma=020000000000&imei=&aid=&aaid=&uid=&ts=0&os=1&osv=7.0.4&ua=Mozilla/5.0%20(iPhone;%20CPU%20iPhone%20OS%207_0_4%20like%20Mac%20OS%20X)%20AppleWebKit/537.51.1%20(KHTML,%20like%20Gecko)%20Version/7.0%20Mobile/%20Safari/9537.53&device=Apple%20iPhone5s" target="_blank"><%=basePath %>/Tracking-QA/madhouse-protobuf?url=114.80.90.115:9002/adcall_p/bidrequest&adspaceid=BC00298CF8C79D9E&adtype=2&width=160&height=130&debug=1&ip=37.70.188.68&pid=c2f394befcff3f03&media=0&pcat=12&bid=0000000000001&carrier=0&apitype=2&pkgname=com.autotest.pkgname&appname=QA-Auto&density=&lon=&lat=&cell=&mcell=&conn=0&idfa=WNlJWMwM2YhVDOiZ&oid=WNlJWMwM2YhVDOiZ&wma=020000000000&imei=&aid=&aaid=&uid=&ts=0&os=1&osv=7.0.4&ua=Mozilla/5.0%20(iPhone;%20CPU%20iPhone%20OS%207_0_4%20like%20Mac%20OS%20X)%20AppleWebKit/537.51.1%20(KHTML,%20like%20Gecko)%20Version/7.0%20Mobile/%20Safari/9537.53&device=Apple%20iPhone5s</a>
			</td>
		</tr>
		<tr>
			<td >Protobuf Iqiyi</br>(需提供正确的conf文件)</td>
			<td style="WORD-WRAP: break-word">
			<a href="<%=basePath %>/Tracking-QA/iqiyi-protobuf" target="_blank"><%=basePath %>/Tracking-QA/iqiyi-protobuf</a>
			</td>
		</tr>
		<tr>
			<td>Protobuf Sohu</br>(需提供正确的conf文件)</td>
			<td style="WORD-WRAP: break-word">
			<a href="<%=basePath %>/Tracking-QA/sohu-protobuf" target="_blank"><%=basePath %>/Tracking-QA/sohu-protobuf</a>
			</td>
		</tr>
		<tr>
			<td>Protobuf Tencent</br>(需提供正确的conf文件)</td>
			<td style="WORD-WRAP: break-word">
			<a href="<%=basePath %>/Tracking-QA/tencent-protobuf" target="_blank"><%=basePath %>/Tracking-QA/tencent-protobuf</a>
			</td>
		</tr>
		<tr>
			<td>Protobuf Youku</br>(需提供正确的conf文件)</td>
			<td style="WORD-WRAP: break-word">
			<a href="<%=basePath %>/Tracking-QA/youku-protobuf" target="_blank"><%=basePath %>/Tracking-QA/youku-protobuf</a>
			</td>
		</tr>
		<tr>
			<td>Protobuf 支持的平台</td>
			<td>madhouse、iqiyi、tencent、letv、sohu、youku</td>
		</tr>
		<tr>
			<td rowspan="5">监播计数接口：</td>
			<td>
			<a href="<%=basePath %>/Tracking-QA/trackingCount?remark=remark1" target="_blank"><%=basePath %>/Tracking-QA/trackingCount?remark=remark1</a>
			</td>
		</tr>
		<tr>
			
			<td><a href="<%=basePath %>/Tracking-QA/impCount?remark=remark2" target="_blank"><%=basePath %>/Tracking-QA/impCount?remark=remark2</a></td>
		</tr>
		<tr>
			
			<td><a href="<%=basePath %>/Tracking-QA/clkCount?remark=remark3" target="_blank"><%=basePath %>/Tracking-QA/clkCount?remark=remark3</a></td>
		</tr>
		<tr>
			
			<td><a href="<%=basePath %>/Tracking-QA/imptrackingCount?remark=remark4" target="_blank"><%=basePath %>/Tracking-QA/imptrackingCount?remark=remark4</a></td>
		</tr>
		<tr>
			
			<td><a href="<%=basePath %>/Tracking-QA/clktrackingCount?remark=remark5" target="_blank"><%=basePath %>/Tracking-QA/clktrackingCount?remark=remark5</a></td>
		</tr>
	
	</table>

</body>
</html>