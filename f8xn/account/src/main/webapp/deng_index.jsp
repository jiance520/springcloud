<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>登陆</title>
    <meta charset="UTF-8">
    <meta name="keywords" content="帐号,账号,密码,小号,生成,批量,游戏,f8xn"><!--网站关键字-->
    <meta name="description" content="在线批量生成小号帐号密码"><!--网站内容描述-->
    <meta name="Robots" Content="all"><!--默认all，文件将被检索，且页面上的链接可以被查询；其它none|index|noindex|follow|nofollow-->
    <meta name="expires" content="Mon,14 Oct 2020 00:20:00 GMT"><!--设定网页的到期时间，一旦过期则必须到服务器上重新调用。必须使用GMT时间格式-->
    <meta name="author" content="jiance_520"><!--网站作者-->
    <meta name="generator" content="javascript,html,jsp"><!--网站使用的工具-->
    <meta name="coryright" content="未经许可，不得转载">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"><!--设置移动端自适应-->
    <meta http-equiv="content-type" content="text/html;charset=utf-8"><!--网站显示语言编码-->
    <meta http-equiv="refresh" content="3600;url=http://www.f8xn.top"><!--定时网站3600秒后，跳转到http://www.f8xn.top，达到更新-->
    <link rel="stylesheet" href="css/bootstrap.css"/>
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="js/cookietool.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <!--vue引用的css、js文件-->
    <script type="text/javascript" src="js/vue.js"></script>
    <!--去除html页面中GET http://localhost:8080/favicon.ico 404 (Not Found)-->
    <link rel="shortcut icon" href="#"/>
    <style type="text/css">
        @media screen and (max-width: 600px) {
            .form-horizontal{
                padding:8% 1% 0 0;
            }
            .control-label{
                font:normal 20px "微软雅黑 Light";
            }
            .form-control{
                font:normal 20px "微软雅黑 Light";
                height: 28px;
            }
            .log-button{
                font:normal 20px "微软雅黑 Light";
                margin-left: 20%;
            }
            .reg-button{
                font:normal 20px "微软雅黑 Light";
                margin-left: 10%;
            }
            .div-a{
                font:normal 20px "微软雅黑 Light";
            }
            .footer{
                margin-top:55px;
            }
            #veryCode{
                transform:scale(0.6);
                position: relative;
                top:-6px;
            }
            #vc_div{
                height: 31px;
            }
        }
        @media screen and (min-width: 600px) {
            .form-horizontal{
                padding:3% 1% 0 0;
            }
            .control-label{
                font:normal 30px "微软雅黑 Light";
                color: white;
            }
            .form-control{
                font:normal 30px "微软雅黑 Light";
                height: 40px;
            }
            .log-button{
                font:normal 30px "微软雅黑 Light";
                margin-left: 20%;
            }
            .reg-button{
                font:normal 30px "微软雅黑 Light";
                margin-left: 10%;
            }
            .div-a{
                font:normal 30px "微软雅黑 Light";
            }
            .footer{
                position: absolute;bottom:0;left:0;right:0;
            }
        }
        * {
            margin: 0 auto;
            padding: 0;
            border: 1px solid #444;
            /*font:normal 30px "微软雅黑 Light";*//*不要在这里设置样式，权值太低，无效*/
        }
        html,body {
            width: 100%;
            height: 100%;
            /*background-color:#202121;*/
            /*background-color:#2C143F;*/
            background-color: #2B3140;
            /*background-color: rgba(91, 255, 0, 0);*/
        }
        .footer *{
            font:normal 20px 微软雅黑;
        }
        a{
            color:chartreuse;
            text-decoration:none;
        }
        a:hover{
            color:yellow;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<header style="height:20%;"></header>
<section class="container" style="display:flex;text-align:center;">
    <!--margin-right:0;margin-left:0;padding-right:0;padding-left:0;-->
    <!--col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1-->
    <!--col-lg-pull-1 col-md-pull-1 col-sm-pull-1 col-xs-pull-1-->
    <!--col-lg-push-1 col-md-push-1 col-sm-push-1 col-xs-push-1-->
    <form id="ffindex" method="post" action="" enctype="multipart/form-data" class="form-horizontal" style="background-color: rgba(45,255,146,0.5);border: 1px solid #000000;border-radius:5px;padding-right: 0"><!--class="form-horizontal"-->
        <div id="token" hidden></div>
        <jsp:include page="token.jsp"></jsp:include>
        <div class="form-group" style="margin-right:0;margin-left:0;text-align: right;">
            <label class="col-lg-3 col-md-3 col-sm-3 col-xs-3 control-label" style="padding:0;" for="username">用户名:</label>
            <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7" style="margin-right:0;margin-left:0;">
                <input type="text" style="" class="form-control" name="username" value="" id="username" required autofocus autocomplete="on"/>
            </div>
        </div>
        <div></div>
        <div class="form-group" style="margin-right:0;margin-left:0;text-align: right">
            <label class="col-lg-3 col-md-3 col-sm-3 col-xs-3 control-label" style="padding:0;" for="password">密码:</label>
            <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7" style="margin-right:0;margin-left:0;">
                <input type="text" style="" class="form-control" name="password" id="password" value="" required/>
            </div>
        </div>
        <div class="form-group" style="margin-right:0;margin-left:0;text-align: right">
            <label class="col-lg-3 col-md-3 col-sm-3 col-xs-3 control-label" style="padding:0;" for="v-code">验证码:</label>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="margin-right:0;margin-left:0;padding-right:0;">
                <input type="text" style="padding-right:0;" class="form-control veryCode" value="123456" id="v-code" required autofocus/>
            </div>
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-4" id="vc_div" style="margin-right:0;margin-left:0;padding-left:0;text-align: center;">
                <img id="veryCode" src="ImageAction" style="padding-right:0;border-radius: 3px;-moz-border-radius:3px;" alt=""/>
            </div>
        </div>
        <div class="form-group" style="margin-right:0;margin-left:0;text-align:center">
            <input type="checkbox" id="rememberme"/><span class="div-a text-center" style="font:normal 18px/24px solid">Remember me</span>
        </div>
        <div class="form-group" style="margin-right:0;margin-left:0;text-align:center" id="hidden" hidden>
            <span class="div-a text-center" id="error" style="color: red">${fail}</span>
        </div>
        <div class="form-group" style="margin-right:0;margin-left:0;text-align:center">
            <button type="button" class="log-button btn btn-primary" style="display:block;margin:0 auto;width:25%" id="submitid">登陆</button>
        </div>
        <div class="form-group" style="margin-right:0;margin-left:0;text-align:center">
            <a href="#mymodal2" class="reg-button col-md-4 col-sm-4 col-xs-4" data-toggle="modal">注册</a>
            <a class="div-a  col-md-6 col-sm-6 col-xs-6 text-center" href="javascript:void(0)">忘记密码</a>
        </div>
    </form>
</section>
<footer class="footer" style="">
    <div class="container">
        <div class="row">
            <a class="col-md-offset-2 col-lg-4 col-md-4 col-sm-6 col-xs-12" style="display:inline-block;text-align: center" target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=43050302000163">
                <img style="vertical-align: middle;display: inline-block" src="img/beian.png" alt=""/>
                <span style="vertical-align: middle;display: inline-block;color:#939393;">湘公网安备 43050302000163号</span>
            </a>
            <a  class="col-lg-4 col-md-4 col-sm-6 col-xs-12" style="display: inline-block;text-align:center" target="_blank" href="http://www.beian.miit.gov.cn/">
                <span style="vertical-align: middle;display: inline-block;color:#939393;">湘ICP备18023926号-1</span>
            </a>
        </div>
    </div>
</footer>
<script type="text/javascript">

    //  重置
    function clearForm(){
        $('#ffindex').form('clear');
    }
    window.onload=function(){
        $("#submitid").click(function () {
            //如果有错误提示信息，不能提交表单
            var content = $("#error").html();
            if(content!=''){
                return false;
            }
            else {
                let formData=new FormData($("#ffindex")[0]);
                let frm =$("#ffindex");
                $.ajax({
                    type:"POST",
                    url: "loginAction",
                    //url: "http://localhost:8083/account/loginAction",
                    //url: ${bathPath}+"loginAction",
                    data:formData,
                    //url: frm.attr("http://localhost:8083/account/loginAction"),
                    //data:frm.serialize(),
                    async:false,
                    processData:false,
                    contentType:false,
                    success:function (result) {
                        console.log(result.fail);
                        if(result.fail!=null&&result.fail!=false){
                            $("#hidden").show();
                            $("#error").html(JSON.stringify(result.fail));
                        }
                       else {
                            let username=result.users.username;
                            let password=result.users.password;
                            let flag = $("#rememberme").prop('checked');
                            if(flag){
                                console.log("选中，设置cookie");
                                //setCookie("username",username);
                                //setCookie("password",password);
                            }else {
                                console.log("未选中");
                            }
                           window.location.href="password.jsp";
                            $("#error").html("");//进行了跳转新页面
                            $("#hidden").hide();
                        }
                    }
                });
            }
        });
//      点击更新验证码
        let veryCodeImg = $("#veryCode");
        let veryCodeInput = $(".veryCode");
        veryCodeImg.click(function(){
            var vs = "ImageAction?times="+new Date();
            $(this).attr("src",vs);
            $("#error").html("");/*清空错误提示*/
            $("#hidden").hide();
        });
        veryCodeInput.focus(function () {
            $("#error").html("");
            $("#hidden").hide();
        });

//      失去光标验证验证码
        veryCodeInput.blur(function(){
            var val = veryCodeInput.val();
            $.post("CheckCodeAction",{"value":val},function(data){
                if(data==="true"){
                    $("#error").html("");
                    $("#hidden").hide();
                }
                else{
                    veryCodeInput.val("");
                    $("#hidden").show();
                    $("#error").html("验证码错误");
                }
            },"text");
        });
        //帐号或密码获取焦点时，取消错误提示
        var failNode = $("#error");
        $("#password").focus(function () {
            failNode.html("");
            $("#hidden").hide();
        });
        $("#username").focus(function () {
            failNode.html("");
            $("#hidden").hide();
        });
    };
</script>

<ul class="nav navbar-nav">
    <li><a class="active" href="#mymodal" data-toggle="modal">登陆</a></li>
    <li><a href="#mymodal2" data-toggle="modal">注册 </a></li>
</ul>
<!--<button class="btn btn-primary" data-toggle="modal" data-target="#mymodal">点击按钮触发弹框2</button>-->
<!--弹出的模态框-->
<div id="mymodal" class="modal fade" ><!-- modal固定布局，上下左右都是0表示充满全屏，支持移动设备上使用触摸方式进行滚动。。-->
    <div class="modal-dialog"><!-- modal-dialog默认相对定位，自适应宽度，大于768px时宽度600，居中-->
        <div class="modal-content col-md-offset-2" style="width:60%;"><!-- modal-content模态框的边框、边距、背景色、阴影效果。-->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">登陆</h4>
            </div>
            <div class="media-body">


                <form action="#" class="form-horizontal">
                    <div class="form-group" style="display: flex; justify-content: center;">
                        <div class="col-sm-10">
                            <input class="form-control" type="text" placeholder="用户名"/>
                        </div>
                    </div>
                    <div class="form-group"  style="display: flex; justify-content: center;">
                        <div class="col-sm-10">
                            <input class="form-control" type="email" placeholder="请输入你的密码"/>
                        </div>
                    </div>
                    <div class="form-group"  style="display: flex; justify-content: center;">
                        <div class="col-sm-10">
                            <input class="" type="checkbox" value=""/>下次自动登陆
                        </div>
                    </div>
                    <div class="form-group"  style="display: flex; justify-content: center;">
                        <div class="col-sm-11" >
                            <input class="btn btn-primary btn-block" type="submit" value="登陆"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <!-- <button class="btn btn-default" data-dismiss="modal">关闭</button>
                 <button class="btn btn-primary">保存</button>-->
            </div>
        </div>
    </div>
</div>

<div id="mymodal2" class="modal fade" ><!-- modal固定布局，上下左右都是0表示充满全屏，支持移动设备上使用触摸方式进行滚动。。-->
    <div class="modal-dialog"><!-- modal-dialog默认相对定位，自适应宽度，大于768px时宽度600，居中-->
        <div class="modal-content col-md-offset-2" style="width:60%;"><!-- modal-content模态框的边框、边距、背景色、阴影效果。-->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">注册</h4>
            </div>
            <div class="media-body">


                <form action="#" class="form-horizontal">
                    <div class="form-group" style="display: flex; justify-content: center;">
                        <div class="col-sm-10">
                            <span id="check-id">
				                <c:if test="${param.flagid !=null && param.flagid=='1'}">
                                    <c:out value="已存在"/>
                                </c:if>
		                        <c:if test="${param.flagid !=null && param.flagid=='0'}">
                                    <c:out value="可注册。"/>
                                </c:if>
                            </span><a href="#" id="check">检查帐号</a>
                        </div>
                    </div>
                    <div class="form-group" style="display: flex; justify-content: center;">
                        <div class="col-sm-10">
                            用户名(必填):<input class="form-control" type="text" id="cardId"/>
                        </div>
                    </div>
                    <div class="form-group"  style="display: flex; justify-content: center;">
                        <div class="col-sm-10">
                            请输入你的密码(必填):<input class="form-control" type="password"/>
                        </div>
                    </div>
                    <div class="form-group"  style="display: flex; justify-content: center;">
                        <div class="col-sm-10">
                            请确认你的密码(必填)：<input class="form-control" type="email"/>
                        </div>
                    </div>
                    <div class="form-group" style="display: flex; justify-content: center;">
                        <div class="col-sm-10">
                            手机号(选填):<input class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="form-group" style="display: flex; justify-content: center;">
                        <div class="col-sm-10">
                            邮箱(选填):<input class="form-control" type="text"/>
                        </div>
                    </div>
                    <div class="form-group"  style="display: flex; justify-content: center;">
                        <div class="col-sm-5">
                            <input class="form-control" type="text"/>
                        </div>
                        <div class="col-sm-5">
                            <input type="text" style="padding-right:0;" class="form-control veryCode" value="123456" id="v-code-model"/>
                        </div>
                    </div>
                    <div class="form-group"  style="display: flex; justify-content: center;">
                        <div class="col-sm-11" >
                            <input class="btn btn-primary btn-block" type="submit" value="注册"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
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