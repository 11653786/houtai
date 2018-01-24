<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>开卡信息保存成功管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/etl.web/userByInfo/">邀请用户列表</a></li>
		<li><a href="${ctx}/etl.web/userInfo/">用户信息保存成功列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="userByInfo" action="${ctx}/etl.web/userByInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户姓名</th>
				<th>邀请人邀请码</th>
				<th>手机号</th>
				<th>预约开卡日</th>
				<th>创建时间</th>
				<%--<shiro:hasPermission name="etl.web:userByInfo:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userByInfo">
			<tr>
				<td><a href="${ctx}/etl.web/userByInfo/form?id=${userByInfo.id}">
					${userByInfo.name}
				</a></td>
				<td>
					${userByInfo.byUserInvitationCode}
				</td>
				<td>
					${userByInfo.phone}
				</td>
				<td>
					<fmt:formatDate value="${userByInfo.advanceOpenCodeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${userByInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<%--<shiro:hasPermission name="etl.web:userByInfo:edit"><td>--%>
    				<%--<a href="${ctx}/etl.web/userByInfo/form?id=${userByInfo.id}">修改</a>--%>
					<%--<a href="${ctx}/etl.web/userByInfo/delete?id=${userByInfo.id}" onclick="return confirmx('确认要删除该开卡信息保存成功吗？', this.href)">删除</a>--%>
				<%--</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>