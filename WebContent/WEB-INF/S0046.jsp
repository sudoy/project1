<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="新パスワード入力" />
	<jsp:param name="current" value="" />
	<jsp:param name="bgc" value="bgg" />
</jsp:include>

<div class="container">

	<div class="row row-center">

		<form method="post" action="S0046.html?user=${form.mail}">
			<div class="col-md-4 col-md-offset-4 widthform">
				<div class="form-group headmargin">
					<h1 class="midasi2 h2">物品売上管理システム</h1>
				</div>
			</div>
			<div class="col-md-8 col-md-offset-2">
				<!-- メッセージ -->
				<jsp:include page="_message.jsp" />
			</div>
			<div class="col-md-4 col-md-offset-4 widthform">
				<div class="form-group">
					<h2 class="midasi3 h3">新パスワード入力</h2>
					<input type="password" class="form-control ${HTMLUtils.regexpErrorFrame(error,'^新?パスワード.*$')}" name="password1" placeholder="新パスワード" value="${form.password1}">
					<input type="password" class="form-control ${HTMLUtils.errorFrame(error,'確認')}" name="password2" placeholder="新パスワード確認" value="${form.password2}">
					<button type="submit" class="btn btn-primary btn-lg btn-block topmargin">変更</button>
				</div>

			</div>
		</form>
	</div>

</div>

<!--フッター-->
<jsp:include page="_footer.jsp" />