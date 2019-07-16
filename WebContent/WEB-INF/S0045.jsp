<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="パスワード再設定" />
	<jsp:param name="current" value="" />
	<jsp:param name="bgc" value="bgg" />
</jsp:include>

<div class="container">



	<div class="row row-center">

		<form method="post" action="S0045.html">
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
					<h2 class="midasi3 topmargin h3">パスワード再設定</h2>
					<input type="text" class="form-control ${HTMLUtils.errorFrame(error,'メールアドレス')}" name="mail" placeholder="メールアドレス" value="">
					<button type="submit"class="btn btn-primary btn-lg btn-block topmargin">メール送信</button>

				</div>

			</div>
		</form>
	</div>

</div>

<!--フッター-->
<jsp:include page="_footer.jsp" />