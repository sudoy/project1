<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.abc.asms.utils.HTMLUtils" %>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="アカウント検索結果一覧" />
	<jsp:param name="current" value="アカウント検索" />
</jsp:include>

<div class="container">
	<h1>アカウント検索結果一覧</h1>

	<table class="table table-hover table-border-top">
		<thead>
			<tr>
				<th class="col-sm-2">操作</th>
				<th class="col-sm-1 text-right">No</th>
				<th class="col-sm-2">氏名</th>
				<th class="col-sm-3">メールアドレス</th>
				<th class="col-sm-3">権限</th>
			</tr>
		</thead>
		<tbody>
<c:forEach var="accountList" items="${list}">
			<tr class="table-border-top">
				<td>
<c:if test="${10 <= account.authority}">
					<a class="btn btn-primary" href="S0042.html">
						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 編集
					</a>
					<a class="btn btn-danger" href="S0044.html">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 削除
					</a>
</c:if>
				</td>
				<td class="text-right">${accountList.account_id}</td>
				<td>${HTMLUtils.XSS(accountList.name)}</td>
				<td>${HTMLUtils.XSS(accountList.mail)}</td>
				<td>${accountList.authority}</td>
			</tr>
</c:forEach>
		</tbody>
	</table>

</div><!--class="container"-->

<!--フッター-->
<jsp:include page="_footer.jsp" />