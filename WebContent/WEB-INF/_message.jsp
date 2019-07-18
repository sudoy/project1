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
<c:if test="${!empty(success)}">
			<div class="alert alert-success alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="margin-off"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
					<strong> 成功しました！</strong><br>
				</h4><br>
				<ul>
				<c:forEach var="suc" items="${success}">
					<li>${suc}</li>
				</c:forEach>
				</ul>
			</div>
</c:if>
<c:if test="${!empty(error)}">
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" id="alert" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="margin-off"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					<strong> エラーが発生しました！</strong>
				</h4><br>
				<ul>
				<c:forEach var="err" items="${error}">
					<li>${err}</li>
				</c:forEach>
				</ul>
			</div>
</c:if>