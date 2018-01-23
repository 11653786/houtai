<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/userInfo/userInfo/">用户信息列表</a></li>
    <shiro:hasPermission name="userInfo:userInfo:edit">
        <li><a href="${ctx}/userInfo/userInfo/form">用户信息添加</a></li>
    </shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="userInfo" action="${ctx}/userInfo/userInfo/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>姓名：</label>
            <form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>卡号：</label>
            <form:input path="careCode" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>理财经理：</label>
            <form:input path="manager" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>手机号：</label>
            <form:input path="phone" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>用户邀请码：</label>
            <form:input path="invitationCode" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li><label>输入邀请码：</label>
            <form:input path="byUserInvitationCode" htmlEscape="false" maxlength="50" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>id</th>
        <th>姓名</th>
        <th>用户银行卡号</th>
        <th>理财经理</th>
        <th>手机号</th>
        <th>邀请码</th>
        <th>邀请人邀请码(邀请你的人的邀请码)</th>
        <th>预约开卡日</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="userInfo">
        <tr>
            <td><a href="${ctx}/userInfo/userInfo/form?id=${userInfo.id}">
                    ${userInfo.id}
            </a></td>
            <td>
                    ${userInfo.name}
            </td>
            <td>
                    ${userInfo.careCode}
            </td>
            <td>
                    ${userInfo.manager}
            </td>
            <td>
                    ${userInfo.phone}
            </td>
            <td>
                    ${userInfo.invitationCode}
            </td>
            <td>
                    ${userInfo.byUserInvitationCode}
            </td>
            <td>
                <fmt:formatDate value="${userInfo.advanceOpenCodeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <fmt:formatDate value="${userInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>

            <td>
                <shiro:hasPermission name="userInfo:userInfo:edit">
                    <a href="${ctx}/userInfo/userInfo/form?id=${userInfo.id}">修改</a>
                </shiro:hasPermission>
                <a href="${ctx}/userInfo/userInfo/detail?byUserInvitationCode=${userInfo.invitationCode}">邀请用户查看</a>
                    <%--<a href="${ctx}/userInfo/userInfo/delete?id=${userInfo.id}" onclick="return confirmx('确认要删除该用户信息吗？', this.href)">删除</a>--%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>