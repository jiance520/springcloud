<%@ page language="java" import="com.oracle_jdbc.*,java.util.*,java.sql.*,org.slf4j.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <title>注册</title>
    <style type="text/css">
        #main {
            margin: 0 auto;
            width: 1000px;
            border: 1px solid #c0c0c0;
     	}
    </style>
  </head>
<body id="main">
<form action="Register" method="post">
    <table>
        <tr>
            <td>帐号:</td>
            <td><input id="cardId" name="cardId" type="text" value="430523198311070056" required placeholder="身份证"><span></span>
            <span id="check-id">
				<c:if test="${param.flagid !=null && param.flagid=='1'}">
					<c:out value="已存在"/>
		        </c:if>
		        <c:if test="${param.flagid !=null && param.flagid=='0'}">
	            	<c:out value="可注册。"/>
	            </c:if>
            </span><a href="#" id="check">检查帐号</a>
            </td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password" id="password" value="" required placeholder="12位，含大小写"><span></span></td>
        </tr>
        <tr>
            <td>密码确认:</td>
            <td><input type="password" name="password2" id="password2" value=""><span></span></td>
        </tr>
        <tr>
        	<td>验证码</td>
        	<td><input type="text" name="code" id="code" value="1"/><img src="ImageAction" alt="点击更新" title="点击更新"></td>
        </tr>
        <tr>
            <td><input type="submit" value="注册"></td>
            <td>
            	<c:if test="${param.flagid !=null && param.flagid=='2'}">
	            	<c:out value="注册失败。"/>
	        	</c:if>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
    var flag = true;/*判断空内容*/
    var flag1 = true;/*判断格式错误*/
    // var cardIdReg = /^[a-zA-Z]\w{5,9}$/;/*用户名6-10位的字母，数字或下划线，首字字母。*/
    // var passwordReg = /(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*_).{6,12}/;/*密码6-12位，包含大小写，数字和下划线*/
    var cardIdReg = /.*/;///^\d{15}$|^\d{18}$/身份证
    var passwordReg = /.*/;
    $(document).ready(function () {
        $('#cardId').blur(function () {
            $('#check-id').html('');
            if($(this).val()==''){
                $(this).next().html('身份证不能为空');
                $(this).next().css('color','red');
                return false;
            }
            else {
                if(cardIdReg.test($(this).val())==true){
                    $(this).next().html('');
                    // $(this).next().html('身份证正确');
                    // $(this).next().css('color','green');
                    return true;
                }
                else {
                    $(this).next().html('身份证15-18位的字母，数字。');
                    $(this).next().css('color','red');
                    flag1 = false;
                    return false;
                }
            }
        })
        //通过点击检查用户名格式及是否存在
        $('#check').click(function () {
            if($('#cardId').val()==''){
                $('#cardId').next().html('身份证不能为空');
                $('#cardId').next().css('color','red');
                return false;
            }
            else {
                if(cardIdReg.test($('#cardId').val())==true){
                	//判断注册的身份证帐号是否已存在，如果存在，则必须重新注册
 //http://reg.email.163.com/unireg/call.do?cmd=register.entrance&from=email163&regPage=163
		 			var id = $('#cardId').val();
 					$('#check').attr('href','CheckUser?cardId='+id);
                    $('#cardId').next().html('');
                    // $('#cardId').next().html('身份证正确');
                    // $('#cardId').next().css('color','green');
                    return true;
                }
                else {
                    $('#cardId').next().html('身份证15-18位的字母，数字。');
                    $('#cardId').next().css('color','red');
                    flag1 = false;
                    return false;
                }
            }
        })
        $('#password').blur(function () {
            if($(this).val()==''){
                $(this).next().html('密码不能为空');
                $(this).next().css('color','red');
                return false;
            }
            else {
                if(passwordReg .test($(this).val())==true){
                    $(this).next().html('');
                    // $(this).next().html('密码格式正确');
                    // $(this).next().css('color','green');
                    return true;
                }
                else {
                    $(this).next().html('密码6-12位，包含大小写，数字和下划线');
                    $(this).next().css('color','red');
                    flag1 = false;
                    return false;
                }
            }
        })
        $('#password2').blur(function () {
            if($(this).val()==''){
                $(this).next().html('密码不能为空');
                $(this).next().css('color','red');
                return false;
            }
            else {
                if($(this).val()==$('#password').val()){
                    $(this).next().html('');
                    // $(this).next().html('密码正确');
                    // $(this).next().css('color','green');
                    return true;
                }
                else {
                    $(this).next().html('两次输入的密码不一致');
                    $(this).next().css('color','red');
                    flag1 = false;
                    return false;
                }
            }
        })
        $("form:eq(0)").submit(function () {/*如果是非form不能使用submit()方法*/
            $('input').each(function (index,element) {/*==$.each($('.inputs'), function() {}*/
                element.value.trim();/*去空格*/
                if(element.value==''){/*element非jquery对象，不能用val()*/
                    flag = false;
                    return false;/*当前方法的return只能中断当前方法的执行*/
                }
            });
            if(flag==false){
                alert('内容不能为空');
                return false;/*中断click方法，后面的不再执行*/
            }
            if(flag1 == false){
                alert('格式错误');
                return false;
            }
            alert('确认提交');
            return true;
        })
    });
</script>

</body>
</html>