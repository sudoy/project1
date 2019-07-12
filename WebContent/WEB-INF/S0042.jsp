<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="アカウント編集" />
	<jsp:param name="current" value="アカウント検索" />
</jsp:include>

<!-- アカウント編集 -->
<div class="container">
		<h1>アカウント編集</h1>

		<!-- メッセージ -->
		<jsp:include page="_message.jsp" />

		<form class="form-horizontal" method="post" action="S0042.html">
			<input type="hidden" name="accountId" value="${form.accountId}">

			<!-- 氏名 -->
			<div class="form-group">
				 <label for="name" class="col-sm-offset-1 col-sm-2  control-label">氏名
				 <span class="badge">必須</span>
	    		 </label>
				 <div class="col-sm-4">
					<input type="text" class="form-control" id="name" name="name" value="${form.name}" placeholder="氏名">
				 </div>
			</div>

			<!-- メールアドレス -->
			<div class="form-group">
				 <label for="mail" class="col-sm-offset-1 col-sm-2  control-label">メールアドレス
				 <span class="badge">必須</span>
	    		 </label>
				 <div class="col-sm-4">
					<input type="text" class="form-control" id="mail" name="mail" value="${form.mail}" placeholder="メールアドレス">
				 </div>
			</div>

			<!-- パスワード -->
			<div class="form-group">
				 <label for="password1" class="col-sm-offset-1 col-sm-2  control-label">パスワード

	    		 </label>
				 <div class="col-sm-4">
					<input type="password" class="form-control" id="password1" name="inputPass" value="${form.inputPass}" placeholder="パスワード">
				 </div>
			</div>

			<!-- パスワード(確認) -->
			<div class="form-group">
				 <label for="password2" class="col-sm-offset-1 col-sm-2  control-label">パスワード(確認)

	    		 </label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="password2" name="inputPass2" value="${form.inputPass2}" placeholder="パスワード(確認)">
				</div>
			</div>

			<!-- 売上登録権限 -->
			<div class="form-group">
				<label class="col-xs-3 text-right control-label">売上登録権限 <span class="badge">必須</span></label>
				<div class="col-sm-3">
						<label class="radio-inline">
							<input type="radio" name="salesAuthority" value="no" <c:if test="${form.salesAuthority == 'no'}">checked</c:if>>
							権限なし
						</label>
						<label class="radio-inline">
							<input type="radio" name="salesAuthority" value="yes" <c:if test="${form.salesAuthority == 'yes'}">checked</c:if>>
							権限あり
						</label>
				</div>
			</div>

			<!-- アカウント登録権限 -->
			<div class="form-group">
				<label class="col-xs-3 text-right control-label">アカウント登録権限 <span class="badge">必須</span></label>
				<div class="col-sm-3">
						<label class="radio-inline">
							<input type="radio" name="accountAuthority" value="no" <c:if test="${form.accountAuthority == 'no'}">checked</c:if>>
							権限なし
						</label>
						<label class="radio-inline">
							<input type="radio" name="accountAuthority" value="yes" <c:if test="${form.accountAuthority == 'yes'}">checked</c:if>>
							権限あり
						</label>
				</div>
			</div>

			<!-- ボタン -->
			<div class="form-group">
	    		<div class="col-sm-offset-4 col-sm-4">
	    			<button type="submit" class="btn btn-primary">
	    			<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 更　新</button>
	    			<a href="S0041.html" class="btn btn-default">キャンセル</a><!-- アカウント検索結果一覧へ -->
				</div>
			</div>

		</form>
	</div><!--container-->

<!--フッター-->
<jsp:include page="_footer.jsp" />