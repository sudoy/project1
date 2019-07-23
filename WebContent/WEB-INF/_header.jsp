<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.abc.asms.utils.HTMLUtils" %>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${param.page}｜物品売上管理システム</title><%--表示中のページ名<jsp:param>で--%>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body class="${param.bgc}">

<!--ヘッダー-->
<c:if test="${not empty accounts}">
<nav class="navbar navbar-default">
	<div class="container-field">

		<!--画面が小さくなったときのやつ-->
		<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>

		<!--システム名-->
		<div class="navbar-header">
			<a class="navbar-brand">物品売上管理システム</a>
		</div>

		<!--リンク-->
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

			<%--押した場所にclass="active"をつける→表示中の機能？を<jsp:param>で--%>
			<ul class="nav navbar-nav">
				<li ${HTMLUtils.createHeaderClass('ダッシュボード',param.current)}><a href="C0020.html">ダッシュボード<span class="sr-only">(current)</span></a></li>
				<c:if test="${accounts.authority == 1 or accounts.authority == 11}">
					<li ${HTMLUtils.createHeaderClass('売上登録',param.current)}><a href="S0010.html">売上登録</a></li>
				</c:if>
				<li ${HTMLUtils.createHeaderClass('売上検索',param.current)}><a href="S0020.html">売上検索</a></li>
				<c:if test="${accounts.authority == 10 or accounts.authority == 11}">
					<li ${HTMLUtils.createHeaderClass('アカウント登録',param.current)}><a href="S0030.html">アカウント登録</a></li>
				</c:if>
				<li ${HTMLUtils.createHeaderClass('アカウント検索',param.current)}><a href="S0040.html">アカウント検索</a></li>
			</ul>

			<!--ログアウト-->
			<ul class="nav navbar-nav navbar-right">
				<li><a href="C0030.html">ログアウト</a></li>
			</ul>

		</div><!--div class="collapse navbar-collapse"-->
	</div>
</nav>
</c:if><!--ヘッダーここまで-->