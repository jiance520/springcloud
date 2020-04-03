<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <%--<base href="<%=basePath%>">--%>
    <title>My JSP 'product_edit.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="css/themes/icon.css">
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/datagrid-detailview.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
    <link rel="stylesheet" href="ueditor/themes/default/css/ueditor.css"><!--非必须 导入js或者css的顺序一定不要互换，-->
    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
    <style type="text/css">
        *{
            /*font:normal 14px/24px 宋体;*/
            /*border:1px solid #444;*/
        }
        td,input{
            line-height: 32px;
        }
    </style>
</head>
<body style="text-align:center;">
<div  id="product_show">
    <h3>帐号描述信息</h3>
    <script type="text/javascript">
        $(document).ready(function () {

        });
        //返回?后的json对象
        function getRequest() {
            //获取url中"?"符后的字串
            var url = location.search;
            var theRequest = new Object();
            if (url.indexOf("?") != -1) {
                var str = url.substr(1);
                var strs = str.split("&");
                for (var i = 0; i < strs.length; i++) {
                    //theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
                    //theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
                    theRequest[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
                }
            }
            return theRequest;
        }
        //    产品id
        var productid = getRequest()["productid"];
            if(productid!=false&&productid!=null){//判断是空值或没有值用==和||,判断非空用!和&&(重点)。
                $.post("productMapAjax",{"productid":productid},function (data) {
                    var desc = data.PRODUCTDESC;
                    $("#product_show").append($(desc));
                },'json');
            }
        $.parser.parse("#product_edit");//刷新样式，显示图标
    </script>
</div>
</body>
</html>