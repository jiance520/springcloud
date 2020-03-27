//读取cookie
//driverNamedjjcookie
//datasourceUrldjjcookie
//userNamedjjcookie
//passworddjjcookie
//tabnamedjjcookie
//pidnamedjj1cookie
//pidnamedjj2cookie
//acttypedjjcookie

//设置cookie：https://www.cnblogs.com/llljpf/p/10485464.html
//document.cookie="name=123" //将cookie写入客户端
//获取cookie，能够获取，有效
function getCookie(name)
{
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}
//删除cookie需要将失效日期设置为过去的时间点,有效。
function delCookie(name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); //将时间转换成GMT格式的字符串
}
//设置cookie属性值name=value时，给cookie设置失效日期,
function setCookie(name, value)
{
    var date = new Date(); //获取当前时间
    var exp = 3; //expiresDays缩写exp(有效时间)
    date.setTime(date.getTime() + exp * 24 * 3600 * 1000); //格式化为cookie识别的时间
    document.cookie=escape(name) + "=" + escape(value) + ";expires="+date.toGMTString(); //将name设置为3天后过期,超过这个时间name这条cookie会消失
}