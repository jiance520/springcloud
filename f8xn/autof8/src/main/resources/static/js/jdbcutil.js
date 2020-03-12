//调用jquery事件或函数必须放在window.onload=或$(document).ready()方法体内！否则无效！javascript自定义方法放在ready()外，且里面不能使得jquery方法！已验证！重点
/*$(document).ready(function () {
});*/
window.onload=()=>{
    //,必须放在ready内。
//ajax不跳转提交表单，一是点击form外的按钮，二是点击form的button按钮事件onclick="submitForm()"。不刷新提交表单？
//form不再需要action，在ajax指定。能获取formdata.append后加入的值！
//$("#submitid").submit(function (e) {})是直接点击submit按钮，默认跳转到action，通过onclick事件的函数，加入ajax不跳转提交表单。
    function ajaxForm() {
        let driverNamedjj = $('#driverNamedjj').val();
        let datasourceUrldjj = $('#datasourceUrldjj').val();
        let userNamedjj = $('#userNamedjj').val();
        let passworddjj = $('#passworddjj').val();
        let pronamedjj= document.getElementById("pronamedjj").value;
        let tabnamedjj= $("input[name='tabnamedjj']").val();
        let pidnamedjj1= $("input[name='pidnamedjj1']").val();
        let pidnamedjj2= $("input[name='pidnamedjj2']").val();
        var acttypedjj= document.getElementsByName("acttypedjj").item(0).value;//外部js只能是纯js，不能使用jquery取值。
        let jdbc= document.getElementsByName("jdbc").item(0).value;
        let formData = new FormData($("#formsignin")[0]);
        //formData.append("acttypedjj",acttypedjj);
        //formData.set("excelfiledjj","excelfiledjj");//修改
        //formData.get("acttypedjj")
        //console.log("formdata:"+JSON.stringify(formData));
        if(jdbc==="a1"){
            acttypedjj="actionAll"
        }
        if(jdbc==="a2"){
            acttypedjj="actionAllTwo"
        }
        console.log("driverNamedjj:"+driverNamedjj);
        console.log("datasourceUrldjj:"+datasourceUrldjj);
        console.log("userNamedjj:"+userNamedjj);
        console.log("passworddjj:"+passworddjj);
        console.log("pronamedjj:"+pronamedjj);
        console.log("tabnamedjj:"+tabnamedjj);
        console.log("pidnamedjj1:"+pidnamedjj1);
        console.log("pidnamedjj2:"+pidnamedjj2);
        console.log("acttypedjj:"+acttypedjj);
        console.log("jdbc:"+jdbc);
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
                //后端Map+request.getParameter能取new FormData($(formid)[0])里的值;
                //提交表单上传文件，后端可以使用MultipartFile excelfiledjj接收。
            }
            //alert('end ajax');/* 异步时先弹出 */
        });
        let html = "http://www.f8xn.top:8081/"+pronamedjj+'/'+acttypedjj;
        document.getElementById(httpApi.id).innerHTML=html; //给节点赋值。
    }

    //方式二(推荐)form外的button按钮点击事件，ajax不跳转提交。
    $('.ajax2').click(()=>{
        ajaxForm();
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
};


//方式一(推荐)form内的button按钮点击事件，ajax不跳转提交。
function submitForm() {
    console.log(3);
    //submit.onSubmit=function(){};
    ajaxForm();
}

//删除和增加,必须放在ready外。
function addrow() {
    let inputname = prompt("请输入name的字段名：");
     if(inputname!==false&&inputname!=null){//判断是空值或没有值用==和||,判断非空用!和&&(重点)。
         let inputvalue = prompt("请输入value的值：");
         if(inputvalue!==false&&inputvalue!=null){//判断是空值或没有值用==和||,判断非空用!和&&(重点)。
             let childNode =`        <tr>
            <td><label for="password" class="sr-only">新增字段${inputname}</label></td>
            <td>
                <input type="text" id="${inputname}" class="form-control" name="${inputname}" value="${inputvalue}" required autofocus>
                <input type="button" value="deleterow" onclick="deleterow(this)">
            </td>
        </tr>`;
             $("#addrow").append(childNode);
         }
     }
}
function autoAdd() {
    let driverNamedjj = $('#driverNamedjj').val();
    let datasourceUrldjj = $('#datasourceUrldjj').val();
    let userNamedjj = $('#userNamedjj').val();
    let passworddjj = $('#passworddjj').val();
    let tabnamedjj= $("input[name='tabnamedjj']").val();
    $.ajax({
        type:"POST",
        // url:"http://www.f8xn.top:8081/"+pronamedjj+'/'+acttypedjj,
        url:"http://localhost:8081/autof8/selectOne",
        data:{driverNamedjj:driverNamedjj,datasourceUrldjj:datasourceUrldjj,userNamedjj:userNamedjj,passworddjj:passworddjj,tabnamedjj:tabnamedjj},
        async:false,
        processData:false,
        contentType:false,
        onSubmit:function () {},
        success:function(data){
            console.log("ajax-data:"+JSON.stringify(data));
        }
    });
}
function deleterow(e) {
    e.parentElement.parentElement.remove();
}
function clearAll() {
    deleterow($('#driverNamedjj')[0]);//转为DOM对象
    deleterow($('#datasourceUrldjj')[0]);
    deleterow($('#userNamedjj')[0]);
    deleterow($('#passworddjj')[0]);
    $("input[name='tabnamedjj']").val("");
    $("input[name='pidnamedjj1']").val("");
    $("input[name='pidnamedjj2']").val("");
    deleterow($('#id')[0]);
    deleterow($('#name')[0]);
    deleterow($('#password')[0]);
}
