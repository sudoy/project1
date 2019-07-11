<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.HTMLUtils" %>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="" />
	<jsp:param name="current" value="" />
</jsp:include>

<!--アカウント検索条件入力-->
<div class="container">
	<h1>アカウント検索</h1>
</div>

<!-- メッセージ -->
<jsp:include page="_message.jsp" />

<div class="container">
	<form class="form-horizontal" method="post" action="S0040.html">

		<!-- 氏名 -->
		<div class="form-group">
			<label for="name" class="col-xs-3 text-right control-label">氏名 <span class="badge">部分一致</span></label>
			<div class="col-xs-5">
				<input type="text" class="form-control" id="name" name="name" placeholder="氏名" value="${name}">
			</div>
		</div>

		<!-- メールアドレス -->
		<div class="form-group">
			<label for="mail" class="col-xs-3 text-right control-label">メールアドレス</label>
			<div class="col-xs-5">
				<input type="text" class="form-control" id="mail" name="mail" placeholder="メールアドレス" value="${mail}">
			</div>
		</div>

		<!-- 売上登録権限 -->
		<div class="form-group">
			<label class="col-xs-3 text-right control-label">売上登録権限</label>
			<div class="col-sm-5">
					<label class="radio-inline"><input type="radio" name="salesAuthority" value="all" ${HTMLUtils.checkedAuthority('all',salesAuthority)}>全て</label>
					<label class="radio-inline"><input type="radio" name="salesAuthority" value="no" ${HTMLUtils.checkedAuthority('no',salesAuthority)}>権限なし</label>
					<label class="radio-inline"><input type="radio" name="salesAuthority" value="yes" ${HTMLUtils.checkedAuthority('yes',salesAuthority)}>権限あり</label>
			</div>
		</div>

		<!-- アカウント登録権限 -->
		<div class="form-group">
			<label class="col-xs-3 text-right control-label">アカウント登録権限</label>
			<div class="col-sm-5">
					<label class="radio-inline"><input type="radio" name="accountAuthority" value="all" ${HTMLUtils.checkedAuthority('all',salesAuthority)}>全て</label>
					<label class="radio-inline"><input type="radio" name="accountAuthority" value="no" ${HTMLUtils.checkedAuthority('no',salesAuthority)}>権限なし</label>
					<label class="radio-inline"><input type="radio" name="accountAuthority" value="yes" ${HTMLUtils.checkedAuthority('yes',salesAuthority)}>権限あり</label>
			</div>
		</div>

		<!-- Submit -->
		<div class="form-group">
			<div class="col-xs-8 col-xs-offset-4">
				<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 検　索</button>
				<a class="btn btn-default" href="S0040.html" role="button">クリア</a>
			</div>
		</div>
	</form>
</div>


<!--フッター-->
<jsp:include page="_footer.jsp" />