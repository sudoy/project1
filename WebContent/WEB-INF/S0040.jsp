<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="アカウント検索" />
	<jsp:param name="current" value="アカウント検索" />
</jsp:include>

<!--アカウント検索条件入力-->
<div class="container">
	<h1>アカウント検索</h1>
	<!-- メッセージ -->
	<jsp:include page="_message.jsp" />
	<form class="form-horizontal" method="post" action="S0040.html">

		<!-- 氏名 -->
		<div class="form-group">
			<label for="name"
				class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'氏名')}">氏名
				<span class="badge">部分一致</span>
			</label>
			<div class="col-xs-5">
				<input type="text"
					class="form-control ${HTMLUtils.errorFrame(error,'氏名')}" id="name"
					name="name" placeholder="氏名" value="${HTMLUtils.XSS(acf.name)}">
			</div>
		</div>

		<!-- メールアドレス -->
		<div class="form-group">
			<label for="mail"
				class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'メールアドレス')}">メールアドレス</label>
			<div class="col-xs-5">
				<input type="text"
					class="form-control ${HTMLUtils.errorFrame(error,'メールアドレス')}"
					id="mail" name="mail" placeholder="メールアドレス" value="${HTMLUtils.XSS(acf.mail)}">
			</div>
		</div>

		<!-- 売上登録権限 -->
		<div class="form-group">
			<label class="col-xs-3 text-right control-label">売上登録権限</label>
			<div class="col-sm-5">
				<label class="radio-inline"> <input type="radio"
					name="salesAuthority" value="all"
					${HTMLUtils.checkedAuthority('all',acf.salesAuthority)}>全て
				</label> <label class="radio-inline"> <input type="radio"
					name="salesAuthority" value="no"
					${HTMLUtils.checkedAuthority('no',acf.salesAuthority)}>権限なし
				</label> <label class="radio-inline"> <input type="radio"
					name="salesAuthority" value="yes"
					${HTMLUtils.checkedAuthority('yes',acf.salesAuthority)}>権限あり
				</label>
			</div>
		</div>

		<!-- アカウント登録権限 -->
		<div class="form-group">
			<label class="col-xs-3 text-right control-label">アカウント登録権限</label>
			<div class="col-sm-5">
				<label class="radio-inline"> <input type="radio"
					name="accountAuthority" value="all"
					${HTMLUtils.checkedAuthority('all',acf.accountAuthority)}>全て
				</label> <label class="radio-inline"> <input type="radio"
					name="accountAuthority" value="no"
					${HTMLUtils.checkedAuthority('no',acf.accountAuthority)}>権限なし
				</label> <label class="radio-inline"> <input type="radio"
					name="accountAuthority" value="yes"
					${HTMLUtils.checkedAuthority('yes',acf.accountAuthority)}>権限あり
				</label>
			</div>
		</div>

		<!-- Submit -->
		<div class="form-group">
			<div class="col-xs-8 col-xs-offset-4">
				<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					検 索
				</button>
				<a class="btn btn-default" href="S0040.html" role="button">クリア</a>
			</div>
		</div>
	</form>
</div>


<!--フッター-->
<jsp:include page="_footer.jsp" />