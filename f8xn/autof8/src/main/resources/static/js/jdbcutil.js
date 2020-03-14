// console.log("cookie:"+document.cookie);
//取cookie值
let driverNamedjjcookie = getCookie("driverNamedjjcookie");
let datasourceUrldjjcookie = getCookie("datasourceUrldjjcookie");
let userNamedjjcookie = getCookie("userNamedjjcookie");
let passworddjjcookie = getCookie("passworddjjcookie");
let tabnamedjjcookie = getCookie("tabnamedjjcookie");
let pidnamedjj1cookie = getCookie("pidnamedjj1cookie");
let pidnamedjj2cookie = getCookie("pidnamedjj2cookie");
let acttypedjjcookie = getCookie("acttypedjjcookie");

// delCookie("driverNamedjjcookie");
// delCookie("datasourceUrldjjcookie");
// delCookie("userNamedjjcookie");
// delCookie("passworddjjcookie");
// delCookie("tabnamedjjcookie");
// delCookie("pidnamedjj1cookie");
// delCookie("pidnamedjj2cookie");
// delCookie("acttypedjjcookie");

$(document).ready(function () {
    // alert(1)
    //初始化，赋值,必须放在ready()内，否则报错属性为null。
    if(driverNamedjjcookie!=null&&driverNamedjjcookie!=false&&driverNamedjjcookie!="undefined"){
        document.getElementById("driverNamedjj").value=driverNamedjjcookie;
        document.getElementById("datasourceUrldjj").value=datasourceUrldjjcookie;
        document.getElementById("userNamedjj").value=userNamedjjcookie;
        document.getElementById("passworddjj").value=passworddjjcookie;
        document.getElementById("tabnamedjj").value=tabnamedjjcookie;
        document.getElementById("pidnamedjj1").value=pidnamedjj1cookie;
        document.getElementById("pidnamedjj2").value=pidnamedjj2cookie;
        document.getElementById("acttypedjj").value=acttypedjjcookie;
    }else {
        document.getElementById("driverNamedjj").value="com.mysql.jdbc.Driver";
        document.getElementById("datasourceUrldjj").value="jdbc:mysql://47.107.171.60:3306/shiro?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8";
        document.getElementById("userNamedjj").value="root";
        document.getElementById("passworddjj").value="root";
        document.getElementById("pronamedjj").value="autof8";
        document.getElementById("tabnamedjj").value="t_user";
        document.getElementById("pidnamedjj1").value="id";
        document.getElementById("pidnamedjj2").value="id2";
        document.getElementById("acttypedjj").value="selectOne";
    }
});

//使用jquery方法就必须放在ready内。但是ready()外的事件无法调用。因为后声明。
//ajax不跳转提交表单，一是点击form外的按钮，二是点击form的button按钮事件onclick="submitForm()"。不刷新提交表单？
//form不再需要action，在ajax指定。能获取formdata.append后加入的值！
//$("#submitid").submit(function (e) {})是直接点击submit按钮，默认跳转到action，通过onclick事件的函数，加入ajax不跳转提交表单。
function ajaxForm() {
    // let driverNamedjj = $('#driverNamedjj').val();//ready()外不能使用jquery访求，否则事件无法调用。因为后声明
    let driverNamedjj;
    let datasourceUrldjj;
    let userNamedjj;
    let passworddjj;
    if(document.getElementById("driverNamedjj")!=null&&document.getElementById("driverNamedjj")!="undefined"){
        driverNamedjj =document.getElementById("driverNamedjj").value;
    }
    if(document.getElementById("datasourceUrldjj")!=null&&document.getElementById("datasourceUrldjj")!="undefined"){
        datasourceUrldjj = document.getElementById("datasourceUrldjj").value;
    }
    if(document.getElementById("userNamedjj")!=null&&document.getElementById("userNamedjj")!="undefined"){
        userNamedjj=document.getElementById("userNamedjj").value;
    }
    if(document.getElementById("passworddjj")!=null&&document.getElementById("passworddjj")!="undefined"){
        passworddjj=document.getElementById("passworddjj").value;
    }
    let tabnamedjj= document.getElementById("tabnamedjj").value;
    let pidnamedjj1= document.getElementById("pidnamedjj1").value;
    let pidnamedjj2= document.getElementById("pidnamedjj2").value;
    let acttypedjj= document.getElementsByName("acttypedjj").item(0).value;//外部js只能是纯js，不能使用jquery取值。
    console.log("datasourceUrldjj:"+datasourceUrldjj);
    console.log("passworddjj:"+passworddjj);
    console.log("pidnamedjj1:"+pidnamedjj1);
    console.log("pidnamedjj2:"+pidnamedjj2);
    //设置cookie 3天后失效：每次操作更新cookie。
    setCookie("driverNamedjjcookie",driverNamedjj);
    setCookie("datasourceUrldjjcookie",datasourceUrldjj);
    setCookie("userNamedjjcookie",userNamedjj);
    setCookie("passworddjjcookie",passworddjj);
    setCookie("tabnamedjjcookie",tabnamedjj);
    setCookie("pidnamedjj1cookie",pidnamedjj1);
    setCookie("pidnamedjj2cookie",pidnamedjj2);
    setCookie("acttypedjjcookie",acttypedjj);
    console.log("userNamedjjcookie:"+userNamedjj);
    console.log("getuserNamedjjcookie:"+getCookie("userNamedjjcookie"));
    if(driverNamedjj==null||driverNamedjj==false||driverNamedjj=="undefined"){
        let connflag=confirm("没有配置数据库连接，是否要继续？");
        if(!connflag){
            return;
        }
    }
}

//调用jquery事件或函数必须放在window.onload=或$(document).ready()方法体内！否则无效！javascript自定义方法放在ready()外，且里面不能使得jquery方法！已验证！重点
/*$(document).ready(function () {
});*/
//只能有一个window.onload，多个没反应,但跟ready()不冲突。
window.onload=()=>{

    //方式二(推荐)form外的button按钮点击事件，ajax不跳转提交。
    $('.ajax2').click(()=>{
        ajaxForm();
        let pronamedjj= document.getElementById("pronamedjj").value;
        let acttypedjj= document.getElementsByName("acttypedjj").item(0).value;//外部js只能是纯js，不能使用jquery取值。
        let jdbc= document.getElementsByName("jdbc").item(0).value;
        let formData = new FormData($("#formsignin")[0]);
        //formData.append("acttypedjj",acttypedjj);
        //formData.set("excelfiledjj","excelfiledjj");//修改
        //formData.get("acttypedjj")
        //console.log("formdata:"+JSON.stringify(formData));
        console.log("acttypedjj:"+acttypedjj);
        console.log("jdbc:"+jdbc);
        if(jdbc==="a1"){
            acttypedjj="actionAll"
        }
        if(jdbc==="a2"){
            acttypedjj="actionAllTwo"
        }
        $.ajax({
            type:"POST",
            url:"/"+pronamedjj+"/"+acttypedjj, //properties不加#server.servlet.context-path=/autof8，使用http://localhost:8081/autof8/t_userUpdate
            // url:"http://www.f8xn.top:8081/autof8/selectOne", //properties不加#server.servlet.context-path=/autof8，使用http://localhost:8081/autof8/t_userUpdate
            data:formData,//"name=John&location=Boston",
            async:false,/* 默认异步，是true,false是先执行ajax,再执行后面的内容 */
            //dataType:"json",//text,json.html,后端收到的数据的格式，一般会有 json 、text……等
            //默认processData 是 true，提交的时候data不会序列化为二进制 data，上传的数据都会被转换为字符串的形式上传。
            //而当上传文件的时候，则不需要把其转换为字符串，因此要改成false
            processData:false,
            //前端发送数据的格式，一般会有 json 、text……等,把contentType 改成 false 就会改掉之前默认的数据格式，在上传文件时就不会报错了。
            contentType:false,
            onSubmit:function () {
                /*if(confirm("是否提交")){
                    return true;
                }
                else {
                    return false;
                }*/
            },
            success:function(data){
                console.log("ajax-data:"+JSON.stringify(data));
                document.getElementById(httpApi.id).innerHTML=`接口地址:http://www.f8xn.top:8081/${pronamedjj}/${acttypedjj}`; //给节点赋值。
                document.getElementById(datavalue.id).innerHTML="返回数据:"+JSON.stringify(data); //给节点赋值。
                //后端Map+request.getParameter能取new FormData($(formid)[0])里的值;
                //提交表单上传文件，后端可以使用MultipartFile excelfiledjj接收。
            }
            //alert('end ajax');/* 异步时先弹出 */
        });
    });
//方式三(不推荐)，ajax+jquery+eayui+form事件，不跳转提交。
    /*$('.ajax2').click(function(){
        //无法在后端获取formdata.append后加入的值。
        $('#formsignin').form('submit', {
            type:"POST",
            url:"/autof8/t_userUpdate",
            data:formData,
            async:false,
            processData:false,
            contentType:false,
            success:function(data){
                alert("ajax-data:"+JSON.stringify(data));
            }
        })
    });*/
//方式四提交时跳转页面。
    /*$("#submitid").submit((e)=>{
        e.preventDefault();//阻止默认事件，默认跳转到form元素的action上。
        console.log(4);
        //ajaxForm();
        return false;//不刷新提交.无效？
    });*/
//使用万能接口，需要类和dao+mybatis，统一控制层
//jsonMap:{tabnamedjj=t_user, pidnamedjj1=id, acttypedjj=selectOne, name=admin, password=123}
    $('.post2').click(()=>{
        let action = '/autof8/actionAll';
        let driverNamedjj="com.mysql.jdbc.Driver";
        let datasourceUrldjj="jdbc:mysql://localhost:3306/shiro?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8";
        let userNamedjj="root";
        let passworddjj="root";
        $.post(action,{driverNamedjj:driverNamedjj,datasourceUrldjj:datasourceUrldjj,userNamedjj:userNamedjj,passworddjj:passworddjj,tabnamedjj:"t_user",pidnamedjj1:"id",pidnamedjj2:"id2",id:1,name:'admin',password:'123',acttypedjj:"selectOne"},function(data){/* function(data){}相当于success部分,data=msg */
            console.log("服务器信息"+data);
        },'text');
    });
//使用纯Jdbcutil，无需类和接口
    /*$('.post2').click(()=>{
        let action = '/autof8/updateOne';
        let driverNamedjj="com.mysql.jdbc.Driver";
        let datasourceUrldjj="jdbc:mysql://localhost:3306/shiro?useSSL=false&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8";
        let userNamedjj="root";
        let passworddjj="root";
        $.post(action,{driverNamedjj:driverNamedjj,datasourceUrldjj:datasourceUrldjj,userNamedjj:userNamedjj,passworddjj:passworddjj,tabnamedjj:"t_user",pidnamedjj1:"id",id:1,name:'tom',password:'123'},function(data){/!* function(data){}相当于success部分,data=msg *!/
            alert("服务器信息"+data);
        },'text');
    });*/

    //删除不要的行
    function deleterowAll(){
        let array = [];
        array.push("driverNamedjj","datasourceUrldjj","userNamedjj","passworddjj","pronamedjj","tabnamedjj","pidnamedjj1","pidnamedjj2","acttypedjj","jdbc");
        $("#addrow").find(".my-form-control").each(function (index,element) {
            let elementid = $(element).attr("id");
            let flag = array.includes(elementid);
            if(!flag){
                console.log("element.id:"+$(element).attr("id"));
                console.log(flag);
                deleterow(element);
            }
        })
    }
    //删除不要的行
    $("#clearAll").click(()=>{
        deleterowAll();
    });
    //先删除不要的行，再通过数据库连接自动生成空行。
    $("#autoAdd").click(()=>{
        deleterowAll();
        let driverNamedjj = $('#driverNamedjj').val();
        let datasourceUrldjj = $('#datasourceUrldjj').val();
        let userNamedjj = $('#userNamedjj').val();
        let passworddjj = $('#passworddjj').val();
        let tabnamedjj= $("input[name='tabnamedjj']").val();
        console.log("tabnamedjj:"+tabnamedjj);
        if(driverNamedjj==null||driverNamedjj==false){
            let connflag=confirm("没有配置数据库连接，是否要继续？");
            if(!connflag){
                return;
            }
        }
        $.ajax({
            type:"POST",
            // url:"http://www.f8xn.top:8081/"+pronamedjj+'/'+acttypedjj,
            url:"/autof8/actionAutoAdd",
            data:{driverNamedjj:driverNamedjj,"datasourceUrldjj":datasourceUrldjj,"userNamedjj":userNamedjj,"passworddjj":passworddjj,"tabnamedjj":tabnamedjj},
            async:false,
            dataType:'json',
            success:function(data){
                //console.log("ajax-data:"+data);//[object Object]
                console.log("JSON.stringify-data:"+JSON.stringify(data));//对象转字符串{}，obj = JSON.parse(string));把字符串转对象。
                $.each(data,function(i,v){//data默认是对象，k是键或下标，v是值json字符串数组data中的成员,如果是对象，可以调用其属性。
                    console.log("i:"+i+",v:"+v);
                    let childNode =`        <tr>
            <td><label for="password" class="my-sr-only">新增字段${i}</label></td>
            <td>
                <input type="text" id="${v}" class="my-form-control" placeholder="${v}" name="${v}" value="" required autofocus>
                <input type="button" value="deleterow" onclick="deleterow(this)">
            </td>
        </tr>`;
                    $("#addrow").append(childNode);

                });
            }
        });
    });

    //显示或隐藏说明文档
    $("#showdetail").mouseover(function () {
        $("#contextdetail").show();
    }).mouseout(function () {
        $("#contextdetail").hide();
    });

    //查询
    $("#exectueQueryAction").click(function () {
        ajaxForm();
        let pronamedjj= document.getElementById("pronamedjj").value;
        let exectueSql= document.getElementById("exectueSql").value;
        let formData = new FormData($("#formsignin")[0]);
        formData.append("exectueSql",exectueSql);
        console.log("exectueSql:"+exectueSql);
        if(exectueSql==null||exectueSql==false||exectueSql=="undefined"){
            alert("没有写查询语句，退出执行。");
            return;
        }
        $.ajax({
            type:"POST",
            url:"/"+pronamedjj+"/exectueQueryAction",
            data:formData,
            async:false,
            processData:false,
            contentType:false,
            success:function(data){
                console.log("ajax-data:"+JSON.stringify(data));
                let html = JSON.stringify(data);
                document.getElementById(datavalue.id).value="返回数据："+html;
                document.getElementById(httpApi.id).value="接口地址：http://www.f8xn.top:8081/"+pronamedjj+"/exectueQueryAction";
                //document.getElementById(datavalue.id).innerHTML=html;
                //$("#t1").text("AAA");
                //$("#t2").val("BBB");
            }
        });
    }).mouseover(function () {
        document.getElementById(httpApi.id).value="查询接口地址：http://www.f8xn.top:8081/autof8/exectueQueryAction";
    });
    //更改数据(增删改)
    $("#executeUpdateAction").click(function () {
        ajaxForm();
        let pronamedjj= document.getElementById("pronamedjj").value;
        let exectueSql= document.getElementById("exectueSql").value;
        let formData = new FormData($("#formsignin")[0]);
        formData.append("exectueSql",exectueSql);
        console.log("exectueSql:"+exectueSql);
        if(exectueSql==null||exectueSql==false||exectueSql=="undefined"){
            alert("没有写sql语句，退出执行。");
            return;
        }
        $.ajax({
            type:"POST",
            url:"/"+pronamedjj+"/executeUpdateAction",
            data:formData,
            async:false,
            processData:false,
            contentType:false,
            success:function(data){
                console.log("ajax-data:"+JSON.stringify(data));
                let html = JSON.stringify(data);
                document.getElementById(datavalue.id).value="返回数据："+html;
                document.getElementById(httpApi.id).value="接口地址：http://www.f8xn.top:8081/"+pronamedjj+"/executeUpdateAction";
                //document.getElementById(datavalue.id).innerHTML=html;
                //$("#t1").text("AAA");
                //$("#t2").val("BBB");
            }
        });
    }).mouseover(function () {
        document.getElementById(httpApi.id).value="增删改接口地址：http://www.f8xn.top:8081/autof8/executeUpdateAction";
    });

};

//方式一(推荐)form内的button按钮点击事件，ajax不跳转提交。
function submitForm() {
    console.log(3);
    //submit.onSubmit=function(){};
    ajaxForm();
}

//删除和增加,必须放在ready外。
function addrow() {
    let inputname = prompt("请输入字段名：");
     if(inputname!==false&&inputname!=null){//判断是空值或没有值用==和||,判断非空用!和&&(重点)。
         let inputvalue = prompt("请输入字段的值：");
         if(inputvalue!==false&&inputvalue!=null){//判断是空值或没有值用==和||,判断非空用!和&&(重点)。
             let childNode =`        <tr>
            <td><label for="password" class="my-sr-only">新增字段${inputname}</label></td>
            <td>
                <input type="text" id="${inputname}" class="my-form-control" placeholder="${inputname}" name="${inputname}" value="${inputvalue}" required autofocus>
                <input type="button" value="deleterow" onclick="deleterow(this)">
            </td>
        </tr>`;
             $("#addrow").append(childNode);
         }
     }
}
//手动删除不要的行。
function deleterow(e) {
    e.parentElement.parentElement.remove();
}

//根据textarea 标签内容自适应高度，只回车有效
// $(function(){
//     $.fn.autoHeight = function(){
//         function autoHeight(elem){
//             elem.style.height = 'auto';
//             elem.scrollTop = 0; //防抖动
//             elem.style.height = elem.scrollHeight + 'px';
//         }
//         this.each(function(){
//             autoHeight(this);
//             $(this).on('keyup', function(){
//                 autoHeight(this);
//             });
//         });
//     };
//     $('textarea').autoHeight();
// });
