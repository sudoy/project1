<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="アカウント編集確認" />
	<jsp:param name="current" value="アカウント検索" />
</jsp:include>

<!-- アカウント編集確認 -->
<div class="container">
	<h1>アカウントを編集してよろしいですか？</h1>

	<form class="form-horizontal" method="post" action="S0043.html">
		<input type="hidden" name="accountId" value="${HTMLUtils.XSS(form.accountId)}">

		<!-- 氏名 -->
		<div class="form-group">
			<label for="name" class="col-sm-offset-1 col-sm-2  control-label">氏名</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					value="${HTMLUtils.XSS(form.name)}" placeholder="氏名" disabled>
				<input type="hidden" name="name" value="${HTMLUtils.XSS(form.name)}">
			</div>
		</div>

		<!-- メールアドレス -->
		<div class="form-group">
			<label for="mail" class="col-sm-offset-1 col-sm-2  control-label">メールアドレス</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="mail" name="mail"
					value="${HTMLUtils.XSS(form.mail)}" placeholder="メールアドレス" disabled>
				<input type="hidden" name="mail" value="${HTMLUtils.XSS(form.mail)}">
			</div>
		</div>

		<!-- パスワード -->
		<div class="form-group">
			<label for="password1"
				class="col-sm-offset-1 col-sm-2  control-label">パスワード</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" id="password1"
					name="password" value="${HTMLUtils.XSS(form.inputPass)}" placeholder="パスワード"
					disabled>
				<input type="hidden" name="password" value="${HTMLUtils.XSS(form.inputPass)}">
			</div>
		</div>

		<!-- パスワード(確認) -->
		<div class="form-group">
			<label for="password2"
				class="col-sm-offset-1 col-sm-2  control-label">パスワード(確認)</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" name="password"
					id="password2" value="${HTMLUtils.XSS(form.inputPass2)}" placeholder="パスワード(確認)"
					disabled>
			</div>
		</div>

		<!-- 売上登録権限 -->
		<div class="form-group">
			<label class="col-xs-3 text-right control-label">売上登録権限</label>
			<div class="col-sm-3">
				<label class="radio-inline"> <input type="radio"
					name="salesAuthority" value="no"
					${HTMLUtils.writeChecked('no',form.salesAuthority)} disabled>
					権限なし
				</label> <label class="radio-inline"> <input type="radio"
					name="salesAuthority" value="yes"
					${HTMLUtils.writeChecked('yes',form.salesAuthority)} disabled>
					権限あり
				</label>
				<input type="hidden" name="salesAuthority" value="${HTMLUtils.XSS(form.salesAuthority)}">
			</div>
		</div>

		<!-- アカウント登録権限 -->
		<div class="form-group">
			<label class="col-xs-3 text-right control-label">アカウント登録権限</label>
			<div class="col-sm-3">
				<label class="radio-inline"> <input type="radio"
					name="accountAuthority" value="no"
					${HTMLUtils.writeChecked('no',form.accountAuthority)} disabled>
					権限なし
				</label> <label class="radio-inline"> <input type="radio"
					name="accountAuthority" value="yes"
					${HTMLUtils.writeChecked('yes',form.accountAuthority)} disabled>
					権限あり
				</label>
				<input type="hidden" name="accountAuthority" value="${HTMLUtils.XSS(form.accountAuthority)}">
			</div>
		</div>

		<!-- ボタン -->
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-4">
				<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> O K
				</button>
				<a href="S0042.html?cancel" class="btn btn-default">キャンセル</a>
			</div>
		</div>

	</form>

</div>
<!--container-->

<!--フッター-->
<jsp:include page="_footer.jsp" />