<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.oracle_jdbc.*,java.sql.*" %>
<%-- <%@ include file="loginControl.jsp" %> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--<link rel="stylesheet" type="text/css" href="styles.css">-->
    <title>生成密码</title>
    <style type="text/css">
        form{
            border: 1px solid #c0c0c0;
        }
        #main {
            margin: 0 auto;
            width: 1000px;
            border: 1px solid red;
            height: 800px;
        }
        #left {
            float:left;
            width: 350px;
            height: 750px;
            margin: 5px;
            padding: 5px;
            border: 1px solid #c0c0c0;
            font: normal 16px 宋体;
            line-height: 24px;
        }
        #right {
            float:left;
            width: 600px;
            height: 750px;
            margin: 5px;
            padding: 5px;
            border: 1px solid #c0c0c0;
            font: normal 16px 宋体;
            line-height: 24px;
        }
        input {
            line-height: 24px;
            font: normal 16px 宋体;
        }
        #get10,#get6 {
            display: inline-block;
            font: normal 16px 宋体;
        }
        .submit {
            font: bold 16px 宋体;
        }
        #show{
            width: 580px;
            height: 450px;
            padding: 5px;
            border: 1px solid #c0c0c0;
            font: normal 14px 宋体;
            line-height: 18px;
        }
        #right1,#right2,#right3,#right5{
            width: 200px;
            float: left;
        }
        #right4{
            clear: both;
        }
    </style>
</head>
<body id="main">
    <div id="left">
        <form action="SaveAction" id="info">
            <div>固定特征信息</div>
            <div>本人身份证(数字)</div>
            <input name="cardId" id="cardId" type="text" required placeholder="如纯数字身份证" value="430523198311070056"><!--value不包括字母，如尾号x=10-->
            <div>使用的网站(唯一性)</div>
            <span>网址只能是小写字母.-和数字，其它的:\///\\@汉字必须去掉！</span>
            <br/>
            <input type="text" name="www" id="www" value="www.hao123.com" required placeholder="网址,不包括开头http://或https://">
            <!--域名由各国文字的特定字符集、英文字母、数字及“-”任意组合而成,不区分大小写。-->
            <!--但开头及结尾均不能含有“-”。 域名最长可达67个字节.-->
            <!--abcdefghijklmnopqrstuvwxyz.-1234567890，把域名看成一个38进制-->
            <div>帐号(只能是大小写或数字和_，不能超过12位,邮箱只取头部)</div>
            <input type="text" name="userName" id="userName" value="jiance520" required placeholder="用户名6-20位，首字字母，必须包含大小写，数字，可能包含下划线_，点.，@。">
            <div>
                <span>请勾选或输入密码的组成符号</span>
                <input type="text" name="strzdy" id="strzdy" value='' placeholder="">
            </div>
            <div id="choose">
                <input type="checkbox" name="boxpwd10" id="boxNum" value='' checked/>数字
                <input type="checkbox" name="boxpwd10" id="boxLower" value='' checked/>小写字母
                <input type="checkbox" name="boxpwd10" id="boxUp" value='' checked/>大写字母
                <input type="checkbox" name="boxpwd10" id="boxXian" value='' checked/>下划线
                <input type="checkbox" name="boxpwd10" id="boxFu" value=''/>特殊符号
            </div>
            <input type="button" id="get10" value="生成固定密码"/>
            <br>
            <input type="text" name="pwd10" id="pwd10" value='' required placeholder="10位特殊字符密码">
            <input type="button" name='copy10' style="display: inline-block;"value="点击复制">
            <br>
            <input type="button" id="get6" value="生成6位固定数字交易密码"/>
            <br>
            <input type="text" name="pwd6" id="pwd6" value='' required placeholder="6位数字密码">
            <input type="button" name='copy6' style="display:inline-block;" value="点击复制">
            <br/>
           	 图片/卡片链接：<input type="text" name="cardImage" id="cardImage2" value='images/20180525012410682.jpg'>
           	<div>注册用默认备注信息，此处不能更改保存</div>
            <textarea name='note' rows="12" cols="45" wrap="hard">手机:13973909476、邮箱:jiance520@163.com</textarea>
            <br/>
            <input type='submit' name='submit' class='submit' value='保存/修改信息'/>
        </form>
    </div>
    <div id="right">
        <div id="right1">
            <form action='QueryRecord' method='post'>
                <div>请输入要查询的网站</div>
                <input type='text' name='www' id='qwww2' value='www.hao123.com' required/>
                <div>请输入要查询的用户名</div>
                <input type='text' name='userName' id='quserName' value='jiance520' required/>
                <br/>
                <input type='submit' name='submit' class='submit' value='查询帐号修改记录'/>
            </form>
        </div>
        <%--<div id="right2">
            <form action='QueryNote' method='post'>
                <div>请输入要查询的网站</div>
                <input type='text' name='www' id='qwww2' value='www.hao123.com' required/>
                <div>请输入要查询的用户名</div>
                <input type='text' name='userName' id='quserName' value='jiance520' required/>
                <br/>
                <input type='submit' name='submit' class='submit' value='查询帐号备注和图片'/>
            </form>
        </div>--%>
        <div id="right3">
            <form action='QueryUser' method='post'>
                <div>请输入要查询的网站</div>
                <input type='text' name='www' id='qwww1' value='www.hao123.com' required/>
                <br/>
                <input type='submit' name='submit' class='submit' value='用网站查帐号'/>
            </form>
        </div>
        <div id="right5">
            <form action='QueryNoteUser' method='post'>
                <div>输入查备注的正则表达式</div>
                <input type='text' name='regExp' id='regExp' value='手机%' required/>
                <br/>
                <input type='submit' name='submit' class='submit' value='用备注查询帐号'/>
            </form>
        </div>
        <div  id="right4">显示查询结果</div>
        <div id="show">
        <!--删除修改记录  -->
        <c:if test="${recordList!=null }">
        	<table align="center" border="1">
        	<tr>
        		<td>网站</td>
        		<td>用户名</td>
        		<td>旧密码</td>
        		<td>旧密码6</td>
        		<td>修改日期</td>
        		<td>删除</td>
        	</tr>
        	<c:forEach var="map" items="${requestScope.recordList }">
				<tr>
	        		<td>${map.get("WWW")}</td>
	        		<td>${map.get("USERNAME")}</td>
	        		<td>${map.get("OLDPWD10")}</td>
	        		<td>${map.get("OLDPWD6")}</td>
	        		<td>${map.get("OLDMODIFYDATE")}</td>
	        		<td><a href="DeleteRecord?recordId=${map.get('RECORDID')}" id="deleteRecord">删除</a></td>
        		</tr>
        	</c:forEach>
        	</table>
        </c:if>
        <!--通过网站和用户名的查询结果，来修改用户基本信息，一般不允许修改  -->
        <c:if test="${requestScope.userList !=null }">
	        <table align="center" border="1">
	        	<tr>
	        		<td>网站</td>
	        		<td>用户名</td>
	        		<td>密码</td>
	        		<td>密码6</td>
	        		<td>注册日期</td>
	        		<td>修改</td>
	        	</tr>
	        	<c:forEach var="map" items="${requestScope.userList}">
	        		<tr>
	        		<td>${map.get("WWW")}</td>
	        		<td>${map.get("USERNAME")}</td>
	        		<td>${map.get("PWD10")}</td>
	        		<td>${map.get("PWD6")}</td>
	        		<td>${map.get("REGISTERDATE")}</td>
	        		<td><a href="UpdatePwd6?www=${map.get('WWW')}&userName=${map.get('USERNAME')}" id="modifyPwd6">修改</a></td>
	        		</tr>
	        	</c:forEach>
	        </table>
        </c:if>
        <!--只修改备注 -->
        <c:if test="${requestScope.noteMap!=null }">
            <form  action='ModifyNote?www=${requestScope.noteMap.get("WWW")}&userName=${requestScope.noteMap.get("USERNAME")}' method="post" onsubmit="return confirm('请确认信息，无法返回')">
				<table align="center" border="1">
		        	<tr>
		        		<td>备注</td>
		        	</tr>
		        	<tr>
		        		<td><textarea name='note' rows="15" cols="45" wrap="hard">${requestScope.noteMap.get('NOTE')}</textarea></td>
		        	</tr>
		        	<tr>
		        		<td>图片链接<input type="text" name="cardImage" id="cardImage" value='${requestScope.noteMap.get("CARDIMAGE")}'></td>
		        	</tr>
		        	<tr>
		        		<td><img alt="图片" src='${requestScope.noteMap.get("CARDIMAGE")}'></td>
		        	</tr>
		        	<tr>
						<td><input type="submit" id="modifyNote" value="修改保存备注"/></td>
		        	</tr>
	        	</table>
			</form>
        </c:if>
        <!--通过备注的查询结果，来修改用户基本信息，一般不允许修改  -->
        <c:if test="${requestScope.userNoteList !=null }">
	        <table align="center" border="1" width="550">
	        	<tr>
	        		<td>网站</td>
	        		<td>用户名</td>
	        		<td>密码</td>
	        		<td>密码6</td>
	        		<td>注册日期</td>
	        		<td>备注</td>
	        		<td>修改</td>
	        	</tr>
	        	<c:forEach var="map" items="${requestScope.userNoteList}">
	        		<tr>
		        		<td>${map.get("WWW")}</td>
		        		<td>${map.get("USERNAME")}</td>
		        		<td>${map.get("PWD10")}</td>
		        		<td>${map.get("PWD6")}</td>
		        		<td>${map.get("REGISTERDATE")}</td>
		        		<td>${map.get("NOTE")}</td>
		        		<td><a href="UpdatePwd6?www=${map.get('WWW')}&userName=${map.get('USERNAME')}" id="modifyPwd62">修改</a></td>
	        		</tr>
	        	</c:forEach>
	        </table>
        </c:if>
        </div>
    </div>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script type="text/javascript">
        /*使用对象，成员无序，
        使用字符串不方便增删,
        使用数组不方便删除指定成员*/
        /*全角半角在线切换http://tool.chinaz.com/fullhalf/*/
        /*js对全角与半角的相互转化https://www.cnblogs.com/moqiutao/p/6869794.html?utm_source=itdadao&utm_medium=referral*/
        function AsciiWords() {
            /*特殊符号大全http://xh.5156edu.com/page/18466.html*/
            /*不同的编码，显式不一样*/
            var strA24 = /*希腊字母大写*/'ΑΒΓΔΕΖΗΘΙΚ∧ΜΝΞΟ∏Ρ∑ΤΥΦΧΨΩ';
            var strB24 = /*希腊字母小写*/'αβγδεζηθικλμνξοπρστυφχψω';
            var strC33 = /*俄文字母大写*/ 'АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ';
            var strD33 = /*俄文字母小写*/ 'абвгдеёжзийклмнопрстуфхцчшщъыьэюя';
            var strE37 = /*注音符号*/ 'ㄅㄆㄇㄈㄉㄊㄋㄌㄍㄎㄏㄐㄑㄒㄓㄔㄕㄖㄗㄘㄙㄚㄛㄜㄝㄞㄟㄠㄡㄢㄣㄤㄥㄦㄧㄨㄩ';
            var strF32 = /*汉语拼音*/ 'āáǎàōóǒòēéěèīíǐìūúǔùǖǘǚǜüêńňǹɑɡ';
            var strG83 = /*日文平假名*/ 'ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをん';
            var strH86 = /*日文片假名*/ 'ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵヶ';
            var strI56 = /*标点符号*/ '．。，、；：？！ˉˇ¨`~々～‖∶＂＇｀｜·…—-〃‘’“”〝〞〔〕〈〉《》「」『』〖〗【】（）［］｛｝︻︼﹄﹃';
            var strJ83 = /*数字序号*/ '①②③④⑤⑥⑦⑧⑨⑩㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩№⑴⑵⑶⑷⑸⑹⑺⑻⑼⑽⑾⑿⒀⒁⒂⒃⒄⒅⒆⒇⒈⒉⒊⒋⒌⒍⒎⒏⒐⒑⒒⒓⒔⒕⒖⒗⒘⒙⒚⒛ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫⅰⅱⅲⅳⅴⅵⅶⅷⅸⅹ';
            var strK53 = /*数学符号*/ '：＋－×÷﹢﹣±／＝∥∠≌∽≦≧≒﹤﹥≈≡≠≤≥＜＞≮≯∷∶∫∮∝∞∧∨∑∏∪∩∈∵∴⊥⌒⊙√∟⊿㏒㏑％‰';
            var strL16 = /*单位符号*/ '℡％‰℃℉°′″＄￡￥¤￠♂♀℅';
            var strM113 = /*制表符*/
                '─━│┃┄┅┆┇┈┉┊┋┌┍┎┏┐┑┒┓└┕┖┗┘┙┚┛├┝┞┟┠┡┢┣┤┥┦┧┨┩┪┫┬┭┮┯┰┱┲┳┴┵┶┷┸┹┺┻┼┽┾┿╀╁╂╃╄╅╆╇╈╉╊╋║╒╕╖╘╙╛╜╞╟╠╡╢╣╤╥╦╧╨╪╫╳╔╗╝╚╬═╓╩⊥﹃﹄╭╮╯╰';
            var strN40 = /*特殊符号*/ '§☆★○●◎◇◆□■△▲※→←↑↓〓＃＆＠＼＾＿⊕㊣▂▃▄▅▆▇█▓回◣◢◥▼▽';
        }

        function BoardWords() {
            /*js中没有List，需要自己构造，所以使用Array()或对象*/
        }

        BoardWords.prototype = {
            str_10: '0123456789', /*键盘半角数字*/
            strab_26: 'abcdefghijklmnopqrstuvwxyz', /*键盘半角小写字母*/
            strAB_26: 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', /*键盘半角大写字母*/
            str_xian: '_', /*下划线*/
            str_30: '`~!@#$%^&*()-_=+[]{}|;:",.<>/?', /*键盘半角特殊字符，\'转义，代表'=String.fromCharCode(39),不包括'，会出现undefined*/
            strq10: '０１２３４５６７８９', /*键盘全角数字*/
            strabq26: 'ａｂｃｄｅｆｇｈｉｊｋｌｍｎｏｐｑｒｓｔｕｖｗｘｙｚ', /*键盘全角小写字母*/
            strABq26: 'ＡＢＣＤＥＦＧＨＩＪＫＬＭＮＯＰＱＲＳＴＵＶＷＸＹＺ', /*键盘全角大写字母*/
            str_q68: '｀～！＠＃＄％＾＆＊（）－＿＝＋［］｛｝｜；：＂＇，．＜＞／？·￥……——【】、“”‘’。《》、·￥……×——“”《》【】＼‘’。、￣＼', /*键盘全角特殊字符*/
        }
        boardWords = new BoardWords();
        var strNum = '';
        var str38 = 'abcdefghijklmnopqrstuvwxyz.-1234567890';
        /*用于38进制网址*/
        var str63 = 'abcdefghijklmnopqrstuvwxyz1234567890_ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        /*用于用户名,进制越大，数值越大，密码越长*/
        //去重复
        var str843 = '';
        var strNo_Repeat = '';
        /*存放非重复字符*/
        var strRepeat = '';

        /*存放重复字符*/
        function Repeat(strNum) {
            var strNo_Repeat = '';
            var strRepeat = '';
            for (var i = 0; i < strNum.length; i++) {
                if (strNo_Repeat.indexOf(strNum.charAt(i)) === -1) {/*如果不在里面，则添加*/
                    strNo_Repeat = strNo_Repeat + strNum.charAt(i);
                }
                else {//找到重复：∶∧∑∏％‰⊥﹃﹄
                    strRepeat = strRepeat + strNum.charAt(i);
                }
            }
            return strRepeat;
        }

        function NoRepeat(strNum) {
            var strNo_Repeat = '';
            var strRepeat = '';
            for (var i = 0; i < strNum.length; i++) {
                if (strNo_Repeat.indexOf(strNum.charAt(i)) === -1) {/*如果不在里面，则添加*/
                    strNo_Repeat = strNo_Repeat + strNum.charAt(i);
                }
                else {//找到重复：∶∧∑∏％‰⊥﹃﹄
                    strRepeat = strRepeat + strNum.charAt(i);
                }
            }
            return strNo_Repeat;
        }

        // strRepeat = repeat(strNum);/*返回重复内容*/
        // strNo_Repeat = noRepeat(strNum);/*去重复内容，返回新字符串*/

        //全角转化为半角// a.全角空格为12288，半角空格为32 b.其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248

        var date = new Date();
        var month = date.getMonth();
        var monthCh = String.fromCharCode(64 + month);
        /*A-L版本号跟修改的月份有关*/
        // 获取38进制网址
        var wwwValue38 = document.getElementById('www').getAttribute('value');
        /*获取网站地址，不包括http://或https://*/
        var GetstrWww38 = function (wwwValue38) {
            var strWww38 = '';
            var index = 0;
            if (wwwValue38.substring(0, 6) == 'http:/' || wwwValue38.substring(0, 6) == 'https:') {
                index = wwwValue38.indexOf('w');
                strWww38 = wwwValue38.substring(index + 4);
            }
            else if (wwwValue38.substring(0, 3) == 'www') {/*去掉前面有www.的。www只是主机服务器名，不是域名必备的一部分*/
                strWww38 = wwwValue38.substring(4);
            }
            else {
                strWww38 = wwwValue38;
            }
            return strWww38;
        }
        var strWww38 = GetstrWww38(wwwValue38);
        var strUser63 = document.getElementById('userName').value;
        /*用户名*/
        //将10进制转26进制
        var Convert10_26 = function (num10) {
            var str10_26 = '';
            if (num10 == 0) {
                alert('\'请输入大于0的数\'');
            }
            while (num10 > 0) {
                var m = num10 % 26;
                /*对26取余*/
                if (m == 0) {
                    m = 26;
                    /*如果余数是0，则10进制数字是26，对应字母z*/
                }
                str10_26 = String.fromCharCode(m + 96) + str10_26;
                /*高位数在最左边*/
                num10 = ((num10 - m) / 26).toFixed(0);
                /*整除26，取十位数，下一次整除便是百位数，下下一次千位*/
            }
            return str10_26;
        }
        //将10进制转38、63等任意进制。
        var Convert10_strNum = function(num10, strNum) {
            var str10_strNum = '';
            var arrStrNum = new Array();
            for (var i = 0; i < strNum.length; i++) {
                arrStrNum[i] = strNum.charAt(i);
            }
            if (num10 == 0) {
                alert('\'请输入大于0的数\'');
            }
            while (num10 > 0) {//num10=249
                var m = num10 % strNum.length;//3849%16=9,取余
                /*if (m == 0) {
                }*/
                /*console.log('arrStrNum[m]:'+arrStrNum[m]);
                console.log('str10_strNum2:'+str10_strNum);
                console.log('num10:'+num10);
                console.log('m:'+m);*/
                str10_strNum = arrStrNum[m] + str10_strNum;
                /*高位数在最左边*/
                num10 = ((num10 - m) / strNum.length).toFixed(0);//取10的整数
                /*不是取整，是除法，必需加.toFixed(0)，否则除法错误*/
            }
            return str10_strNum;
        }
        //除法
        //说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
        //调用：accDiv(arg1,arg2)
        //返回值：arg1除以arg2的精确结果
        function accDiv(arg1, arg2) {
            var t1 = 0, t2 = 0, r1, r2;
            try {
                t1 = arg1.toString().split(".")[1].length
            } catch (e) {
            }
            try {
                t2 = arg2.toString().split(".")[1].length
            } catch (e) {
            }
            with (Math) {
                r1 = Number(arg1.toString().replace(".", ""))
                r2 = Number(arg2.toString().replace(".", ""))
                return (r1 / r2) * pow(10, t2 - t1);
            }
        }

        /*网上转过来的*/
        //给Number类型增加一个div方法，调用起来更加方便。
        Number.prototype.div = function (arg) {/*网上转过来的*/
            return accDiv(this, arg);
        }
        //将38、63等任意进制转10进制。如将38进制域名hao123.com转10进制，不转，无法实现与其它进制进行计算。
        var ConvertStrNum_10 = function (strnum, strNum) {
            var strNum_10 = 0;
            var flag = true;
            /*去重复*/
            strRepeat = Repeat(strNum);
            for (var i = 0; i < strnum.length; i++) {
                for (var j = 0; j < strNum.length; j++) {
                    if (strnum.charAt(i) == strNum.charAt(j)) {/*求出字符串中的每个字符在进制中的排位，排位按10进制计算，(在字符串中的位置或数组中的位置)*/
                        if (strnum.length - i - 1 > 0) {/*非个位数，当i=0时，是最高位，当i=length-1时是最低位个位,要排除*/
                            strNum_10 = strNum_10 + j  * Math.pow(strNum.length, strnum.length - i - 1);
                            /*j+1数字要比下标大1，最高位下标i=0，它的幂length-1*/
                        }
                        else {
                            strNum_10 = strNum_10 + j ;
                            /*个位数*/
                        }
                        flag = true;
                        break;
                    }
                    else {
                        flag = false;
                        /*如果没有匹配到，标记为false,循环结束后提示*/
                    }
                }
            }
            if (flag == false) {/*||strRepeat!=''*/
                alert('其它进制10进制没有匹配到字符,或者进制字符有重复');
            }
            return strNum_10;
        }
        var numWww38_10 = ConvertStrNum_10(strWww38, str38);
        //获取63进制用户名，再转10进制
        var numUser63_10 = ConvertStrNum_10(strUser63, str63);
        //获取10进制身份证
        var cardId10;
        var GetCardId = function () {
            var str = $('#cardId').val();
            var cardId10;
            if (str != '') {
                cardId10 = parseInt(str);
            }
            else {
                cardId10 = 0;
            }
            return cardId10;
        }
        $(document).ready(function () {/*for...in可以遍历所有对象的属性和方法*/
            function fun1() {
                var strNum = '';
                if ($('#boxNum').prop('checked') === true) {/*如果被选中，则把BoardWords.str_10放入对象StrNumObj的实例对象中，否则移除*/
                    $('#boxNum').val(boardWords.str_10);
                }
                else {
                    $('#boxNum').val('');
                }
                if ($('#boxLower').prop('checked') === true) {
                    $('#boxLower').val(boardWords.strab_26);
                }
                else {
                    $('#boxLower').val('');
                }
                if ($('#boxUp').prop('checked') === true) {
                    $('#boxUp').val(boardWords.strAB_26);
                }
                else {
                    $('#boxUp').val('');
                }
                if ($('#boxXian').prop('checked') === true) {
                    $('#boxXian').val(boardWords.str_xian);
                }
                else {
                    $('#boxXian').val('');
                }
                if ($('#boxFu').prop('checked') === true) {
                    $('#boxFu').val(boardWords.str_30);
                }
                else {
                    $('#boxFu').val('');
                }
                $('input[name=boxpwd10]').each(function () {
                    strNum = strNum + $(this).val();
                })
                strNum = strNum + $('#strzdy').val();
                /*获取自定义字符*/
                return strNum;
            }

            /*该方法获取密码的组成内容*/
            //10进制身份加上10进制用户加10进制网址，一起再转Num进制，生成固定密码
            var sum10 = cardId10 + numWww38_10 + numUser63_10;
            $('#get10').click(function () {
                cardId10 = GetCardId();
                wwwValue38 = $('#www').val();
                strWww38 = GetstrWww38(wwwValue38);
                strUser63 = $('#userName').val();
                numWww38_10 = ConvertStrNum_10(strWww38, str38);
                numUser63_10 = ConvertStrNum_10(strUser63, str63);
                sum10 = cardId10 + numWww38_10 + numUser63_10;
                ;
                strNum = fun1();
                var pwd10_Num = Convert10_strNum(sum10, strNum);
                $('#pwd10').val(monthCh + pwd10_Num);
                /*一定含有大写字母，一字是字母开头，10位密码*/
                // return false;
            })
            $('#get6').click(function () {/*每次点击要更新数据，按钮button必须规定type为button*/
                cardId10 = GetCardId();
                wwwValue38 = $('#www').val();
                strWww38 = GetstrWww38(wwwValue38);
                strUser63 = $('#userName').val();
                numWww38_10 = ConvertStrNum_10(strWww38, str38);
                numUser63_10 = ConvertStrNum_10(strUser63, str63);
                sum10 = cardId10 + numWww38_10 + numUser63_10;
                var strSum10 = sum10 + '';
                var pwdstr_4 = strSum10.substring(strSum10.length - 4);
                if (month < 10) {
                    $('#pwd6').val('0' + month + pwdstr_4);
                }
                else {
                    $('#pwd6').val('' + month + pwdstr_4);
                }
            })
            /* --------------------编辑------------------------------- */
<%--             $('#aPwd6').mouseover(function () {
                var aStr = $(this).html();
                var node = $(this).parent();
                node.html('');
                node.html('<input type="text" name="inputPwd6" id="inputPwd6" value="" required>')
                node.children('input:eq(0)').val(aStr);
            })
  --%>
        });
    </script>

</body>
</html>
