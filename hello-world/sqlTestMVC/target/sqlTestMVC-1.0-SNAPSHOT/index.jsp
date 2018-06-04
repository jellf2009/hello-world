<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
    <%--<script src="${pageContext.request.contextPath}/js/vue.js"></script>--%>
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
</head>
<body>
<div>
    <h2>
        <a href="${pageContext.request.contextPath}/gosql">进入sql测试页面</a>
    </h2>

    <h2>
        <%--<a href="/goOriginSql">进入原生的sql测试页面</a>--%>
    </h2>
</div>
</body>
</html>
<script>
    var app = new Vue({
        el: '#app',
        data: {
            message: 'Hello Vue!regsd '
        }
    });

    var app_2 =  new Vue({
        el:'#app_2',
        data:{
            message: '该信息绑定于'+new Date().toLocaleString()
        }
    });


</script>
