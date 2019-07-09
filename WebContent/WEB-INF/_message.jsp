<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
使用する変数はそれぞれList<String>型です。
変数名は
succsess
error
です。
--%>
<!-- メッセージ表示 -->
		<div class="container">
<c:if test="${!empty(success)}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<strong> 成功しました！</strong><br>
				<c:forEach var="suc" items="${success}">
					<li>${suc}</li>
				</c:forEach>
			</div>
</c:if>
<c:if test="${!empty(error)}">
			<div class="alert alert-warning alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<strong> エラーが発生しました！</strong><br>
				<ul>
				<c:forEach var="err" items="${error}">
					<li>${err}</li>
				</c:forEach>
				</ul>
			</div>
</c:if>
		</div>