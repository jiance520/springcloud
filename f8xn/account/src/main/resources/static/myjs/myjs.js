//input块元素自动适应父盒子宽高，必须放在ready内，否则无法在加载完成前获取宽高,即没有设置宽高，也能获取到！
$(document).ready(function () {
    // $("input").css("height",$("input").parent().height());
    // $("input").css("width",$("input").parent().width());
    // $("input").css("line-height",$("input").parent().height());
});
//左右等高
if($("#left").height() > $("#right").height()){
    $("#right").css("height",$("#left").height())
}else{
    $("#left").css("height",$("#right").height())
}
//时间
setInterval("fun()",1);
function fun(){
    var date = new Date();  //创建对象
    var y = date.getFullYear();     //获取年份
    var m =date.getMonth()+1;   //获取月份  返回0-11
    var d = date.getDate(); // 获取日
    var w = date.getDay();   //获取星期几  返回0-6   (0=星期天)
    var ww = ' 星期'+'日一二三四五六'.charAt(new Date().getDay()) ;//星期几
    var h = date.getHours();  //时
    var minute = date.getMinutes()  //分
    var s = date.getSeconds(); //秒
    var sss = date.getMilliseconds() ; //毫秒
    if(m<10){
        m = "0"+m;
    }
    if(d<10){
        d = "0"+d;
    }
    if(h<10){
        h = "0"+h;
    }

    if(minute<10){
        minute = "0"+minute;
    }

    if(s<10){
        s = "0"+s;
    }

    if(sss<10){
        sss = "00"+sss;
    }else if(sss<100){
        sss = "0"+sss;
    }
    // document.getElementById(timeID.id).innerHTML =  y+"-"+m+"-"+d+"   "+h+":"+minute+":"+s+"."+sss+"  "+ww;
    document.getElementById(show_time.id).innerHTML =  y+"年"+m+"月"+d+"日   "+h+":"+minute+":"+s+"  "+ww;
}