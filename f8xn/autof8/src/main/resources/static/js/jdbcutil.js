$(document).ready(function () {
    //,必须放在ready内。
//ajax不跳转提交表单，一是点击form外的按钮，二是点击form的button按钮事件onclick="submitForm()"。不刷新提交表单？
//form不再需要action，在ajax指定。能获取formdata.append后加入的值！
//$("#submit").submit(function (e) {})是直接点击submit按钮，会跳转到action。
    function ajaxForm() {
        let proname= document.getElementById("proname").value;
        /*let tabname= $("input[name='tabname']").val();
        let id= $("input[name='id']").val();
        let id2= $("input[name='id2']").val();
        let pidname1= $("input[name='pidname1']").val();
        let pidname2= $("input[name='pidname2']").val();*/
        var acttype= document.getElementsByName("acttype").item(0).value;//外部js只能是纯js，不能使用jquery取值。
        let jdbc= document.getElementsByName("jdbc").item(0).value;
        let formData = new FormData($("#formsignin")[0]);
        //formData.append("acttype",acttype);
        //formData.set("excelfile","excelfile");//修改
        //console.log("formdata:"+JSON.stringify(formData));
        if(jdbc==="a1"){
            acttype="actionAll"
        }
        if(jdbc==="a2"){
            acttype="actionAllTwo"
        }
        console.log("formdata.acttype:"+formData.get("acttype"));
        console.log("proname:"+proname);
        console.log("acttype:"+acttype);
        console.log("jdbc:"+jdbc);
        $.ajax({
            type:"POST",
            url:"/"+proname+"/"+acttype, //properties不加#server.servlet.context-path=/autof8，使用http://localhost:8081/autof8/t_userUpdate
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
                alert("ajax-data:"+JSON.stringify(data));
                //后端Map+request.getParameter能取new FormData($(formid)[0])里的值;
                //提交表单上传文件，后端可以使用MultipartFile excelfile接收。
            }
            //alert('end ajax');/* 异步时先弹出 */
        });
    }
//方式一(推荐)form内的button按钮点击事件，ajax不跳转提交。
    function submitForm() {
        console.log(3);
        //submit.onSubmit=function(){};
        ajaxForm();
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
    /*$("#submit").submit((e)=>{
        e.preventDefault();//阻止默认事件，一样会提交和跳转到form元素的action上。
        console.log(4);
        //ajaxForm();
        return false;//不刷新提交.无效？
    });*/
//使用万能接口，需要类和dao+mybatis，统一控制层
//jsonMap:{tabname=t_user, pidname1=id, acttype=selectOne, name=admin, password=123}
    $('.post2').click(()=>{
        let action = '/autof8/actionAll';
        $.post(action,{tabname:"t_user",pidname1:"id",pidname2:"id2",id:1,name:'admin',password:'123',acttype:"selectOne"},function(data){/* function(data){}相当于success部分,data=msg */
            alert("服务器信息"+data);
        },'text');
    });
//使用纯Jdbcutil，无需类和接口
    /*$('.post2').click(()=>{
        let action = '/autof8/updateOne';
        $.post(action,{tabname:"t_user",pidname1:"id",id:1,name:'tom',password:'123'},function(data){/!* function(data){}相当于success部分,data=msg *!/
            alert("服务器信息"+data);
        },'text');
    });*/
});

//删除和增加,必须放在ready外。
function addrow() {
    let inputname = prompt("请输入name的字段名：");
    let inputvalue = prompt("请输入value的值：");
    //"+inputname+"
    let childNode =`        <tr>
            <td><label for="password" class="sr-only">新增字段${inputname}</label></td>
            <td>
                <input type="text" id="${inputname}" class="form-control" name="${inputname}" value="${inputvalue}" required autofocus>
                <input type="button" value="deleterow" onclick="deleterow(this)">
            </td>
        </tr>`;
    $("#addrow").append(childNode);
}
function deleterow(e) {
    e.parentElement.parentElement.remove();
}