<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>登录</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/statics/dist/jquery-3.5.1.min.js"></script>
</head>
<body>

学生：<input type="radio" name="type" id="rdo_stu" value="0" />
<br/>
教师：<input type="radio" name="type" id="rdo_tch" value="1" />
账号：<input id="account" type="text"><br/>
密码：<input id="password" type="text"><br/>


<button id="submitButton">登录</button>
<script>
    $(function() {
        var postUrl = "${pageContext.request.contextPath}/login";
        <!--Json对象-->
        var userInfo = {
            "type": "1",
            "account": "220000001",
            "password": "123456"
        };

        $("#submitButton").click(function () {
            $.ajax({
                type : "post",
                url : postUrl,
                datatype : "json",
                contentType : "application/json;charset=UTF-8",
                data : JSON.stringify(userInfo),
                success : function(data) {
                    alert(JSON.stringify(data));
                },
                error : function() {
                    alert("error");
                }
            });
        });
    });
</script>
</body>
</html>
