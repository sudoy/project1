<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.HTMLUtils" %>

<!-- ヘッダー -->
<jsp:include page="_header.jsp"><jsp:param name="page" value="ログイン画面" /></jsp:include>

<!-- メッセージ -->
<jsp:include page="_message.jsp" />

	<div class="container">

		<div class="row row-center">


			<div class="col-md-4 col-md-offset-4 widthform">
				<form method="POST" action="C0010.html">
	  				<div class="form-group headmargin">
		   				<h2 class="midasi2">物品売上管理システム</h2>
						<input type="text" class="form-control ${HTMLUtils.errorMessage(error,'メールアドレス')}"
						name="mail" placeholder="メールアドレス" value="">
						<input type="password" class="form-control ${HTMLUtils.errorMessage(error,'パスワード')}"
						name="password" placeholder="パスワード" value="">
						<button type="submit" class="btn btn-primary btn-lg btn-block topmargin">ログイン</button>
						<a href="C00.html">パスワードを忘れた方はこちらから</a>
					</div>
				</form>
			</div>

		</div>

	</div>


<!--フッター-->
<jsp:include page="_footer.jsp" />