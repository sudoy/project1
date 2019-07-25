<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="アカウント登録確認" />
	<jsp:param name="current" value="アカウント登録" />
</jsp:include>

<div class="container">

	<h1>アカウントを登録してよろしいですか？</h1>

	<form class="form-horizontal" method="post" action="S0031.html">

		<div class="form-group">
			<label for="name" class="col-sm-offset-1 col-sm-2  control-label">
				氏名 <span class="badge">必須</span>
			</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					value="${HTMLUtils.XSS(form.name)}" placeholder="氏名" disabled>
			</div>
		</div>

		<div class="form-group">
			<label for="mail" class="col-sm-offset-1 col-sm-2  control-label">
				メールアドレス <span class="badge">必須</span>
			</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="mail" name="mail"
					value="${HTMLUtils.XSS(form.mail)}" placeholder="メールアドレス" disabled>
			</div>
		</div>

		<div class="form-group">
			<label for="password1"
				class="col-sm-offset-1 col-sm-2  control-label"> パスワード <span
				class="badge">必須</span>
			</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" id="password1"
					name="password1" value="${HTMLUtils.XSS(form.password1)}" placeholder="パスワード"
					disabled>
			</div>
		</div>

		<div class="form-group">
			<label for="password2"
				class="col-sm-offset-1 col-sm-2  control-label"> パスワード(確認) <span
				class="badge">必須</span>
			</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" name="password2"
					id="password2" value="${HTMLUtils.XSS(form.password2)}" placeholder="パスワード(確認)"
					disabled>
			</div>
		</div>


		<div class="form-group">
			<label class="col-xs-3 text-right control-label">売上登録権限 <span
				class="badge">必須</span></label>
			<div class="col-sm-3">
				<label class="radio-inline"><input type="radio"
					name="salesAuthority" value="no"
					${HTMLUtils.writeChecked('no',form.salesAuthority)} disabled>権限なし</label>
				<label class="radio-inline"><input type="radio"
					name="salesAuthority" value="yes"
					${HTMLUtils.writeChecked('yes',form.salesAuthority)} disabled>権限あり</label>
			</div>
		</div>

		<div class="form-group">
			<label class="col-xs-3 text-right control-label">アカウント登録権限 <span
				class="badge">必須</span></label>
			<div class="col-sm-3">
				<label class="radio-inline"><input type="radio"
					name="accountAuthority" value="no"
					${HTMLUtils.writeChecked('no',form.accountAuthority)} disabled>権限なし</label>
				<label class="radio-inline"><input type="radio"
					name="accountAuthority" value="yes"
					${HTMLUtils.writeChecked('yes',form.accountAuthority)} disabled>権限あり</label>
			</div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-4">
				<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K
				</button>
				<a href="S0030.html?cancel" class="btn btn-default">キャンセル</a>
			</div>
		</div>

	</form>

</div>
<!--container-->

<!--フッター-->
<jsp:include page="_footer.jsp" />