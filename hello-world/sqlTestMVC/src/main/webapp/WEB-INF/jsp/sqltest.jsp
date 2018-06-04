<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<h2>进行sql测试</h2>
<form method="post" id="test1" action="${pageContext.request.contextPath}/goExecute">
    <table border="1">
        <thead>
            <td width="600">执行条件</td>
            <td>//////</td>
            </thead>
            <tbody>
            <td>
                <input style="width: 600px;" type="text" id="sqlin" name="sql" width="600">
            </td>
            <td>
                <input type="submit" id="sub1" value="自定义执行sql"/>
            </td>
            </tbody>
        </table>
    </form>
    <hr />
<form action="${pageContext.request.contextPath}/selectTest" method="get">
    <span>select ... froms
        <select name="selectTable" id="selectTable" >
            <option value="goods">goods</option>
            <option value="mysqlrecord">mysqlrecord</option>
            <option value="ord_goods">ord_goods</option>
            <option value="t_ord">t_ord</option>
        </select>
    </span>&nbsp;
    where
    <select name="whereif" id="whereif">
        <c:if test="${tableDTOS != null}">
            <c:forEach items="${tableDTOS}" var="tableDTO">
                <c:if test="${tableDTO.name == 'goods'}">
                    <c:forEach items="${tableDTO.columns}" var="column">
                        <option value=${column}>${column}</option>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </c:if>
    </select>
    =
    <input type="text" name="whereValue">
    <span>查询条数:<input type="number" name="count"/></span>
    <input type="submit" value="测试查询"/>
</form>
<hr />
<form action="${pageContext.request.contextPath}/updateTest" method="get">
    <span>update
        <select name="updateTable" id="updateTable" >
            <option value="goods">goods</option>
            <option value="mysqlrecord">mysqlrecord</option>
            <option value="ord_goods">ord_goods</option>
            <option value="t_ord">t_ord</option>
        </select>
    </span>&nbsp;
    set
    <select name="updateColumn" id="updateColumn">
        <c:if test="${tableDTOS != null}">
            <c:forEach items="${tableDTOS}" var="tableDTO">
                <c:if test="${tableDTO.name == 'goods'}">
                    <c:forEach items="${tableDTO.columns}" var="column">
                        <option value=${column}>${column}</option>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </c:if>
    </select>
    =
    <input type="text" name="updateValue">
    where
    <select name="updateWhereIf" id="updateWhereIf">
        <c:if test="${tableDTOS != null}">
            <c:forEach items="${tableDTOS}" var="tableDTO">
                <c:if test="${tableDTO.name == 'goods'}">
                    <c:forEach items="${tableDTO.columns}" var="column">
                        <option value=${column}>${column}</option>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </c:if>
    </select>
    =
    <input type="text" name="updateWhereValue">
    <input type="submit" value="测试更新"/>
</form>
<hr />
<form action="${pageContext.request.contextPath}/insertTest" method="get">
    <span>insert into
        <select name="insertTable">
            <option value="goods">goods</option>
            <option value="mysqlrecord">mysqlrecord</option>
            <option value="ord_goods">ord_goods</option>
            <option value="t_ord">t_ord</option>
        </select>
        values ---</span>&nbsp;
    <span>插入条数:<input type="number" name="count2"/></span>
    <input type="submit" value="测试插入"/>
</form>
<hr />
<form action="${pageContext.request.contextPath}/doubleSelectTest" method="post">
    select ... from goods a
    <select name="joinNum">
        <option value="0">inner join</option>
        <option value="1">left join</option>
    </select>
    ord_goods b on a.id=b.gid where a.id =
    <input style="width: 50px" type="number" name="doubleValue" />
    limit
    <input style="width: 50px" type="number" name="doubleStart" />
    ,
    <input style="width: 50px" type="number" name="doubleSum" />
    <input  type="submit" value="两表联查" />
</form>
<hr />
<c:if test="${response !=null}">
    <h2>------结果展示------</h2>
    <h2>执行sql语句: ${response.sql}</h2>

    <h2>执行sql返回信息: ${response.msg}</h2>
    <h2>执行sql时间: ${response.time}ms</h2>

    <h2
            <c:if test="${response.code == 'error'}">style="color: red"</c:if>
            <c:if test="${response.code == 'OK'}">style="color: green"</c:if>
    >执行sql状态: ${response.code}</h2>

    <c:if test="${response.resList != null}">
        <table>
            <c:forEach items="${response.resList}" var="item">

            </c:forEach>
        </table>
    </c:if>
</c:if>
<hr/>

<c:if test="${tableDTOS != null}">
    <c:forEach items="${tableDTOS}" var="tableDTO">
        <h4>表:${tableDTO.name}(
            <c:forEach items="${tableDTO.columns}" var="column">
                ${column},
            </c:forEach>
            ) ------表数据量:${tableDTO.sumCount} </h4>
    </c:forEach>
</c:if>

<c:if test="${indexDTOS != null}">
    <form id="tableNameForm" action="${pageContext.request.contextPath}/gosql">
        <select id="tableSelect"></select>
        <input type="hidden" id="tableName" name="tableName"/>
    </form>
    <table border="1">
        <tr>
            <td>表名</td>
            <td>索引是否能包括重复词</td>
            <td>索引名称</td>
            <td>索引中的列序列号</td>
            <td>列名</td>
            <td>列在索引中存储方式</td>
            <td>索引中唯一数目估计值</td>
            <td>是否整列被编入索引</td>
            <td>关键字是否压缩</td>
            <td>是否含有null</td>
            <td>索引方式</td>
        </tr>
        <c:forEach items="${indexDTOS}" var="indexDto">
            <tr>
                <td>${indexDto.tableName}</td>
                <c:if test="${indexDto.non_unique eq '0'}">
                    <td>不能</td>
                </c:if>
                <c:if test="${indexDto.non_unique eq '1'}">
                    <td>可以</td>
                </c:if>
                <td>${indexDto.key_name}</td>
                <td>${indexDto.seq_in_index}</td>
                <td>${indexDto.column_name}</td>
                <td>${indexDto.collation}</td>
                <td>${indexDto.cardinlity}</td>
                <c:choose>
                    <c:when test="${indexDto.sub_part == null}">
                        <td>是</td>
                    </c:when>
                    <c:otherwise>
                        <td>${indexDto.sub_part}</td>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${indexDto.packed == null}">
                        <td>没有</td>
                    </c:when>
                    <c:otherwise>
                        <td>${indexDto.packed}</td>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${indexDto.isNull == null}">
                        <td>没有</td>
                    </c:when>
                    <c:otherwise>
                        <td>${indexDto.isNull}</td>
                    </c:otherwise>
                </c:choose>
                <td>${indexDto.indexType}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
<script>
    $(function () {
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/getTables",
            success: function (data) {
                $.each(data, function (key, value) {
                    if (value == "${tableName}") {
                        $("#tableSelect").append("<option value='" + value + "' selected=selected>" + value + "</option>")
                    } else {
                        $("#tableSelect").append("<option value='" + value + "'>" + value + "</option>")
                    }
                });
            }
        });

        $("#tableSelect").change(function () {
            var tableName = $("#tableSelect").val();
            $("#tableName").val(tableName);
            $("#tableNameForm").submit();
        });

        $("#selectTable").change(function () {
            $("#whereif").empty();
            var tableName2 = $("#selectTable").val();
            <c:if test="${tableDTOS != null}">
            <c:forEach items="${tableDTOS}" var="tableDTO">
            if(tableName2 == "${tableDTO.name}"){
                <c:forEach items="${tableDTO.columns}" var="column">
                    var value ="${column}";
                    $("#whereif").append("<option value='"+value+"'>"+value+"</option>");
                </c:forEach>
            }
            </c:forEach>
            </c:if>
        });

        $("#updateTable").change(function () {
            $("#updateColumn").empty();
            $("#updateWhereIf").empty();
            var tableName2 = $("#updateTable").val();
            <c:if test="${tableDTOS != null}">
                <c:forEach items="${tableDTOS}" var="tableDTO">
                if(tableName2 == "${tableDTO.name}"){
                    <c:forEach items="${tableDTO.columns}" var="column">
                        var value ="${column}";
                        $("#updateColumn").append("<option value='"+value+"'>"+value+"</option>");
                        $("#updateWhereIf").append("<option value='"+value+"'>"+value+"</option>");
                    </c:forEach>
                }
                </c:forEach>
            </c:if>
        });
    });
</script>
</html>
