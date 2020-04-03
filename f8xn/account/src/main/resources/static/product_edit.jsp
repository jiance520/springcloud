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
<body style="text-align: center;">
<div  id="product_edit">
    <div id="ueditor_edit" name="productdesc"></div>
    <form name="form_product_edit" id="form_product_edit" enctype="multipart/form-data" style="border: 1px solid #666;">
        <div style="display:flex;">
            <table style="flex:1;">
                <tr style="margin:2px;display:flex">
                    <td style="background-color:#EDEDED;flex:1;">帐号类型*</td>
                    <td style="flex:2;"><input id="genreAllAction" class="easyui-combobox" name="genreid"  value=""></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">帐号品牌*</td>
                    <td style="flex:2;"><input id="brandAllAction" class="easyui-combobox" name="brandid"  value="" ></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">帐号名称*</td>
                    <td style="flex:2;"><input class="easyui-textbox" name="productname" style="width:380px;height:32px;" value="${product.PRODUCTNAME}" ></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">帐号编号</td>
                    <td style="flex:2;"><input class="easyui-textbox" name="productno" style="width:380px;height:32px;" value="${product.PRODUCTNO}"></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">价格(元)*</td>
                    <td style="flex:2;"><input class="easyui-textbox" name="price" style="width:380px;height:32px;" value="${product.PRICE}" ></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">库存数量(件)*</td>
                    <td style="flex:2;"><input class="easyui-textbox" name="productnum" style="width:380px;height:32px;" value="${product.PRODUCTNUM}" ></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">库存报警数量(件)*</td>
                    <td style="flex:2;"><input class="easyui-textbox" name="alarmnum" style="width:380px;height:32px;" value="${product.ALARMNUM}" ></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">帐号图片一*</td>
                    <td style="flex:2"><input name="files" class="easyui-filebox" data-options="buttonText:'浏览'" style="display:inline-block;width:380px;height:32px;" value="" ></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">帐号图片二</td>
                    <td style="flex:2"><input name="files" class="easyui-filebox" data-options="buttonText:'浏览'" style="display:inline-block;width:380px;height:32px;" value=""></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">帐号图片三</td>
                    <td style="flex:2"><input name="files" class="easyui-filebox" data-options="buttonText:'浏览'" style="display:inline-block;width:380px;height:32px;" value=""></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">供货商*</td>
                    <td style="flex:2"><input name="ssid" id="supplierAllAction" class="easyui-combobox" value=""  /></td>
                </tr>
            </table>
            <table style="flex:1;">
                <tr style="margin:2px;display:flex;">
                    <td style="font-weight:bold;" colspan="2">帐号硬件(参数)属性*</td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">屏幕尺寸*</td>
                    <td style="flex:2"><input id="screenAllAction"  class="easyui-combobox" name="screen_driveid" value="" ></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">机身颜色*</td>
                    <td style="flex:2"><input id="styleAllAction" class="easyui-combobox" name="styleid" value="" ></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">预装系统</td>
                    <td style="flex:2"><input id="osAllAction"  class="easyui-combobox" name="osid" value=""></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">内存*</td>
                    <td style="flex:2"><input id="romAllAction"  class="easyui-combobox" name="rom_driveid" value="" ></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">硬盘*</td>
                    <td style="flex:2"><input id="diskAllAction"  class="easyui-combobox" name="disk_driveid" value="" ></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">显卡</td>
                    <td style="flex:2"><input id="grapAllAction"  class="easyui-combobox" name="grap_driveid" value=""></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">光驱</td>
                    <td style="flex:2"><input id="dvdAllAction"  class="easyui-combobox" name="dvd_driveid" value=""></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">处理器*</td>
                    <td style="flex:2"><input id="cpuAllAction"  class="easyui-combobox" name="cpu_driveid" value="" ></td>
                </tr>
                <tr style="margin:2px;display:flex;">
                    <td style="background-color:#EDEDED;flex:1">特性</td>
                    <td style="flex:2"><input id="featAllAction"  class="easyui-combobox" name="feat_driveid" value=""></td>
                </tr>
            </table>
        </div>
        <div>帐号描述信息</div>
        <div style="text-align:left"><textarea name="ue_content" id="ue_content" style="width:1000px;height:auto;"></textarea></div>
        <div style="text-align:left;">
            <button type="submit" id="submit" class="submit" style="background-color:#DBDBDB;border: 1px solid #929292;border-radius:4px;font:normal 16px/28px 宋体;width:100px;text-align:center;box-shadow:inset -10px -10px 3px #EAE5E1;">确定</button>&nbsp;&nbsp;&nbsp;
            <button type="button" style="background-color:#DBDBDB;border: 1px solid #929292;border-radius:4px;font:normal 16px/28px 宋体;width:100px;text-align:center;box-shadow:inset -10px -10px 3px #EAE5E1;" onclick="clearProductForm()">重置</button>&nbsp;&nbsp;&nbsp;
            <%--<button type="button" style="background-color:#DBDBDB;border: 1px solid #929292;border-radius:4px;font:normal 16px/28px 宋体;width:100px;text-align:center;box-shadow:inset -10px -10px 3px #EAE5E1;" onclick="win_product_close('#win_product_edit')">取消</button>--%>
        </div>
    </form>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#genreAllAction').combobox({
                url:'genreAllAction',
                valueField: "genreid",
                textField: "name",
                width:'380',
                height:'32',
                editable: true,
                value:'${product.GENREID}',
                onLoadSuccess: function (data) {
                    if (data == 0) {
                        $.messager.alert("系统提示", "数据库异常，请联系管理员！", "warning");
                    }
                }
            });
            $('#brandAllAction').combobox({
                url:'brandAllAction',
                valueField: "brandid",
                textField: "name",
                width:'380',
                height:'32',
                editable: true,
                value:'${product.BRANDID}',
                onLoadSuccess: function (data) {
                    if (data == 0) {
                        $.messager.alert("系统提示", "数据库异常，请联系管理员！", "warning");
                    }
                }
            });
            $('#supplierAllAction').combobox({
                url:'supplierAllAction',
                valueField: "ssid",
                textField: "name",
                width:'380',
                height:'32',
                editable: true,
                value:'${product.SUPPLIERID}',
                onLoadSuccess: function (data) {
                    if (data == 0) {
                        $.messager.alert("系统提示", "数据库异常，请联系管理员！", "warning");
                    }
                }
            });
            $('#screenAllAction').combobox({
                url:'screenAllAction',
                valueField: "driveid",
                textField: "drivename",
                width:'380',
                height:'32',
                editable: true,
                value:'${product.SCREENID}',
                onLoadSuccess: function (data) {
                    if (data == 0) {
                        $.messager.alert("系统提示", "数据库异常，请联系管理员！", "warning");
                    }
                }
            });
            $('#styleAllAction').combobox({
                url:'styleAllAction',
                valueField: "styleid",
                textField: "color",
                width:'380',
                height:'32',
                editable: true,
                value:'${product.PRODUCTSTYLEID}',
                onLoadSuccess: function (data) {
                    if (data == 0) {
                        $.messager.alert("系统提示", "数据库异常，请联系管理员！", "warning");
                    }
                }
            });
            $('#osAllAction').combobox({
                url:'osAllAction',
                valueField: "osid",
                textField: "name",
                width:'380',
                height:'32',
                value:'${product.PRODUCTOSID}',
                editable: true
            });
            $('#romAllAction').combobox({
                url:'romAllAction',
                valueField: "driveid",
                textField: "drivename",
                width:'380',
                height:'32',
                editable: true,
                value:'${product.ROMID}',
                onLoadSuccess: function (data) {
                    if (data == 0) {
                        $.messager.alert("系统提示", "数据库异常，请联系管理员！", "warning");
                    }
                }
            });
            $('#diskAllAction').combobox({
                url:'diskAllAction',
                valueField: "driveid",
                textField: "drivename",
                width:'380',
                height:'32',
                editable: true,
                value:'${product.HARDDISKID}',
                onLoadSuccess: function (data) {
                    if (data == 0) {
                        $.messager.alert("系统提示", "数据库异常，请联系管理员！", "warning");
                    }
                }
            });
            $('#grapAllAction').combobox({
                url:'grapAllAction',
                valueField: "driveid",
                textField: "drivename",
                width:'380',
                height:'32',
                editable: true,
                value:'${product.GRAPHICSID}'
            });
            $('#dvdAllAction').combobox({
                url:'dvdAllAction',
                valueField: "driveid",
                textField: "drivename",
                width:'380',
                height:'32',
                editable: true,
                value:'${product.DVDROMID}'
            });
            $('#cpuAllAction').combobox({
                url:'cpuAllAction',
                valueField: "driveid",
                textField: "drivename",
                width:'380',
                height:'32',
                editable: true,
                value:'${product.CPUUNITID}',
                onLoadSuccess: function (data) {
                    if (data == 0) {
                        $.messager.alert("系统提示", "数据库异常，请联系管理员！", "warning");
                    }
                }
            });
            $('#featAllAction').combobox({
                url:'featAllAction',
                valueField: "featureid",
                textField: "name",
                width:'380',
                height:'32',
                editable: true,
                value:'${product.FEATUREID}'
            });

            var ue = UE.getEditor("ue_content", {
                zIndex: "9999"  //ueditor 插入视频弹出框被遮住,加上这句设置层级关系
            });
            ue.ready(function() {
                var info = '<img src="https://s1.ax1x.com/2018/09/14/iEbPBV.png">';
                ue.setContent(info);  //赋值给UEditor,编辑器初始化完成再赋值
                $("#submit").click(function(){
                    $("#form_product_edit").submit(function () {
                        var form = new FormData($("#form_product_edit")[0]);
                        form.append("productdesc",$('[name="productdesc"]').html());
                        $.ajax({
//                            url:"${pageContext.request.contextPath}/add_productAction",
                            url:"update_productAction?productid="+${product.PRODUCTID},
                            type:"post",
                            data:form,
                            processData:false,
                            contentType:false,
                            success:function(data){
                            }
                        });
//                        get();//此处为上传文件的进度条
//                        向前台其它页面跳转发送数据
//                        var req = new XMLHttpRequest();
//                        req.open("post", "${pageContext.request.contextPath}/add_productAction", false);
//                        req.send(form);
//                        alert($('#form_product_edit').serialize());
                        return false;
                    });
                    var html = ue.getContent();
                    $("#ueditor_edit").append($(html));//把富文本编辑器的内容展示在jsp页面上！
//                        ue.setHide();//隐藏富文本编辑器
                    alert("修改成功");
                });
            });
        });
        function clearProductForm() {
            $(document.form_product_edit).form('clear');/*清空easyui表单*/
            function clearmyedit(){
                UE.getEditor('ue_content').setContent('', false);
            }
            clearmyedit();/*清空富文本框*/
        }
        $.parser.parse("#product_edit");//刷新样式，显示图标
    </script>
</div>
</body>
</html>