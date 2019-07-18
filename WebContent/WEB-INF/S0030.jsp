<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="アカウント登録" />
	<jsp:param name="current" value="アカウント登録" />
</jsp:include>

	<div class="container">

		<h1>アカウント登録</h1>

		<!-- メッセージ -->
		<jsp:include page="_message.jsp" />

		<form class="form-horizontal" method="post" action="S0030.html">

			<!-- 氏名 -->
			<div class="form-group">
				 <label for="name" class="col-sm-offset-1 col-sm-2  control-label ${HTMLUtils.errorMessage(error,'氏名')}">
				 	氏名 <span class="badge">必須</span>
	    		 </label>
				 <div class="col-sm-4">
					<input type="text" class="form-control ${HTMLUtils.errorFrame(error,'氏名')}" id="name" name="name" value="${form.name}" placeholder="氏名">
				 </div>
			</div>

			<!-- メールアドレス -->
			<div class="form-group">
				 <label for="mail" class="col-sm-offset-1 col-sm-2  control-label ${HTMLUtils.errorMessage(error,'メールアドレス')}">
				 	メールアドレス <span class="badge">必須</span>
	    		 </label>
				 <div class="col-sm-4">
					<input type="text" class="form-control ${HTMLUtils.errorFrame(error,'メールアドレス')}" id="mail" name="mail" value="${form.mail}" placeholder="メールアドレス">
				 </div>
			</div>

			<!-- パスワード -->
			<div class="form-group">
				 <label for="password1" class="col-sm-offset-1 col-sm-2  control-label ${HTMLUtils.errorMessage(error,'パスワード')}">
				 	パスワード <span class="badge">必須</span>
				 </label>
				 <div class="col-sm-4">
					<input type="password" class="form-control ${HTMLUtils.errorFrame(error,'パスワード')}" id="password1" name="password" value="${form.password}" placeholder="パスワード">
				 </div>
			</div>

			<!-- パスワード(確認) -->
			<div class="form-group">
				 <label for="password2" class="col-sm-offset-1 col-sm-2  control-label ${HTMLUtils.errorMessage(error,'確認')}">
				 	パスワード(確認) <span class="badge">必須</span>
	    		 </label>
				<div class="col-sm-4">
					<input type="password" class="form-control ${HTMLUtils.errorFrame(error,'確認')}" id="password2" name="password2" value="${form.password2}" placeholder="パスワード(確認)">
				</div>
			</div>

			<!-- 売上登録権限 -->
			<div class="form-group">
				<label class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'売上')}">
					売上登録権限 <span class="badge">必須</span>
				</label>
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
				<label class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'アカウント')}">
					アカウント登録権限 <span class="badge">必須</span>
				</label>
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
						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 登　録
					</button>
				</div>
			</div>

		</form>
	</div><!--container-->


<!--フッター-->
<jsp:include page="_footer.jsp" />