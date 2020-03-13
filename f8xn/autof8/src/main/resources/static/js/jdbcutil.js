//使用jquery方法就必须放在ready内。但是ready()外的事件无法调用。因为后声明。
//ajax不跳转提交表单，一是点击form外的按钮，二是点击form的button按钮事件onclick="submitForm()"。不刷新提交表单？
//form不再需要action，在ajax指定。能获取formdata.append后加入的值！
//$("#submitid").submit(function (e) {})是直接点击submit按钮，默认跳转到action，通过onclick事件的函数，加入ajax不跳转提交表单。

function ajaxForm() {
    // let driverNamedjj = $('#driverNamedjj').val();//ready()外不能使用jquery访求，否则事件无法调用。因为后声明
    let driverNamedjj =document.getElementById("driverNamedjj").value;
    let datasourceUrldjj = document.getElementById("datasourceUrldjj").value;
    let userNamedjj =document.getElementById("userNamedjj").value;
    let passworddjj =document.getElementById("passworddjj").value;
    let pronamedjj= document.getElementById("pronamedjj").value;
    let tabnamedjj= document.getElementById("tabnamedjj").value;
    let pidnamedjj1= document.getElementById("pidnamedjj1").value;
    let pidnamedjj2= document.getElementById("pidnamedjj2").value;
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
    console.log("datasourceUrldjj:"+datasourceUrldjj);
    console.log("passworddjj:"+passworddjj);
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

//调用jquery事件或函数必须放在window.onload=或$(document).ready()方法体内！否则无效！javascript自定义方法放在ready()外，且里面不能使得jquery方法！已验证！重点
/*$(document).ready(function () {
});*/
window.onload=()=>{
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

    //删除不要的行
    function deleterowAll(){
        let array = [];
        array.push("driverNamedjj","datasourceUrldjj","userNamedjj","passworddjj","pronamedjj","tabnamedjj","pidnamedjj1","pidnamedjj2","acttypedjj","jdbc");
        $("#addrow").find(".form-control").each(function (index,element) {
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
                $.each(data,function(k,v){//data默认是对象，k是键或下标，v是值json字符串数组data中的成员,如果是对象，可以调用其属性。
                    console.log("k:"+k+",v:"+v);
                    let childNode =`        <tr>
            <td><label for="password" class="sr-only">新增字段${v}</label></td>
            <td>
                <input type="text" id="${k}" class="form-control" placeholder="${k}" name="${k}" value="" required autofocus>
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
        let driverNamedjj =document.getElementById("driverNamedjj").value;
        let datasourceUrldjj = document.getElementById("datasourceUrldjj").value;
        let userNamedjj =document.getElementById("userNamedjj").value;
        let passworddjj =document.getElementById("passworddjj").value;
        let pronamedjj= document.getElementById("pronamedjj").value;
        let tabnamedjj= document.getElementById("tabnamedjj").value;
        let pidnamedjj1= document.getElementById("pidnamedjj1").value;
        let pidnamedjj2= document.getElementById("pidnamedjj2").value;
        let exectueQuerySql= document.getElementById("exectueQuerySql").value;
        let formData = new FormData($("#formsignin")[0]);
        formData.append("exectueQuerySql",exectueQuerySql);
        console.log("exectueQuerySql:"+exectueQuerySql);
        $.ajax({
            type:"POST",
            url:"/"+pronamedjj+"/"+"exectueQueryAction",
            data:formData,
            async:false,
            processData:false,
            contentType:false,
            success:function(data){
                console.log("ajax-data:"+JSON.stringify(data));
                let html = JSON.stringify(data);
                document.getElementById(httpApi.id).innerHTML=html;
            }
        });
    })

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
                <input type="text" id="${inputname}" class="form-control" placeholder="${inputname}" name="${inputname}" value="${inputvalue}" required autofocus>
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

