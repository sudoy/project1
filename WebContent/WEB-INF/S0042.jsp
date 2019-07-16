<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.abc.asms.utils.HTMLUtils" %>
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
				 <label for="name" class="col-sm-offset-1 col-sm-2  control-label ${HTMLUtils.errorMessage(error,'氏名')}">氏名
				 <span class="badge">必須</span>
	    		 </label>
				 <div class="col-sm-4">
					<input type="text" class="form-control ${HTMLUtils.errorFrame(error,'氏名')}" id="name" name="name" value="${form.name}" placeholder="氏名">
				 </div>
			</div>

			<!-- メールアドレス -->
			<div class="form-group">
				 <label for="mail" class="col-sm-offset-1 col-sm-2  control-label ${HTMLUtils.errorMessage(error,'メールアドレス')}">メールアドレス
				 <span class="badge">必須</span>
	    		 </label>
				 <div class="col-sm-4">
					<input type="text" class="form-control ${HTMLUtils.errorFrame(error,'メールアドレス')}" id="mail" name="mail" value="${form.mail}" placeholder="メールアドレス">
				 </div>
			</div>

			<!-- パスワード -->
			<div class="form-group">
				 <label for="password1" class="col-sm-offset-1 col-sm-2  control-label ${HTMLUtils.errorMessage(error,'パスワード')}">パスワード

	    		 </label>
				 <div class="col-sm-4">
					<input type="password" class="form-control ${HTMLUtils.errorFrame(error,'パスワード')}" id="password1" name="inputPass" value="${form.inputPass}" placeholder="パスワード">
				 </div>
			</div>

			<!-- パスワード(確認) -->
			<div class="form-group">
				 <label for="password2" class="col-sm-offset-1 col-sm-2  control-label ${HTMLUtils.errorMessage(error,'(確認)')}">パスワード(確認)

	    		 </label>
				<div class="col-sm-4">
					<input type="password" class="form-control ${HTMLUtils.errorFrame(error,'(確認)')}" id="password2" name="inputPass2" value="${form.inputPass2}" placeholder="パスワード(確認)">
				</div>
			</div>

			<!-- 売上登録権限 -->
			<div class="form-group">
				<label class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'売上')}">売上登録権限 <span class="badge">必須</span></label>
				<div class="col-sm-3">
						<label class="radio-inline ${HTMLUtils.errorMessage(error,'売上')}">
							<input type="radio" name="salesAuthority" value="no" ${HTMLUtils.writeChecked('no',form.salesAuthority)}>
							権限なし
						</label>
						<label class="radio-inline ${HTMLUtils.errorMessage(error,'売上')}">
							<input type="radio" name="salesAuthority" value="yes" ${HTMLUtils.writeChecked('yes',form.salesAuthority)}>
							権限あり
						</label>
				</div>
			</div>

			<!-- アカウント登録権限 -->
			<div class="form-group">
				<label class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'アカウント')}">アカウント登録権限 <span class="badge">必須</span></label>
				<div class="col-sm-3">
						<label class="radio-inline ${HTMLUtils.errorMessage(error,'アカウント')}">
							<input type="radio" name="accountAuthority" value="no" ${HTMLUtils.writeChecked('no',form.accountAuthority)}>
							権限なし
						</label>
						<label class="radio-inline ${HTMLUtils.errorMessage(error,'アカウント')}">
							<input type="radio" name="accountAuthority" value="yes" ${HTMLUtils.writeChecked('yes',form.accountAuthority)}>
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