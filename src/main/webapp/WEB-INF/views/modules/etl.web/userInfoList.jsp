<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户信息管理</title>
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
		<li class="active"><a href="${ctx}/etl.web/userInfo/">用户信息保存成功列表</a></li>
		<%--<shiro:hasPermission name="etl.web:userInfo:edit"><li><a href="${ctx}/etl.web/userInfo/form">用户信息保存成功添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="userInfo" action="${ctx}/etl.web/userInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>id：</label>
				<form:input path="id" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>用户银行卡号：</label>
				<form:input path="careCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>理财经理：</label>
				<form:input path="manager" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>邀请码：</label>
				<form:input path="invitationCode" htmlEscape="false" maxlength="50" class="input-medium"/>
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
				<th>邀请码</th>
				<th>创建时间</th>
				<shiro:hasPermission name="etl.web:userInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userInfo">
			<tr>
				<td><a href="${ctx}/etl.web/userInfo/form?id=${userInfo.id}">
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
					${userInfo.invitationCode}
				</td>
				<td>
					<fmt:formatDate value="${userInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="etl.web:userInfo:edit"><td>
    				<%--<a href="${ctx}/etl.web/userInfo/form?id=${userInfo.id}">修改</a>--%>
						<a href="${ctx}/etl.web/userInfo/detail?byUserInvitationCode=${userInfo.invitationCode}">查看邀请人</a>
					<%--<a href="${ctx}/etl.web/userInfo/delete?id=${userInfo.id}" onclick="return confirmx('确认要删除该用户信息保存成功吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>