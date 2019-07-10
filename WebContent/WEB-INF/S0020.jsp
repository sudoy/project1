<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.abc.asms.utils.HTMLUtils" %>
<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="売上条件検索" />
	<jsp:param name="current" value="売上検索" />
</jsp:include>

<!-- メッセージ -->
<jsp:include page="_message.jsp" />

<!-- 売上登録システム表示 -->
		<div class="container">
			<h1>売上登録</h1>
			<!-- form -->
			<form class="form-horizontal" action="S0020.html" method="POST">

				<!-- 販売日 -->
				<div class="form-group">
					<label class="col-xs-3 text-right control-label">販売日</label>
					<div class="col-xs-2">
						<input type="text" class="form-control errorMessage" id="startsaleDate" name="saleDate1" value="${saleDate1}">
					</div>
					<div class="col-xs-1 text-center control-label">
						～
					</div>
					<div class="col-xs-2">
						<input type="text" class="form-control errorMessage" id="endsaleDate" name="saleDate2" value="${saleDate2}">
					</div>
				</div>

				<!-- 担当 -->
				<div class="form-group">
					<label for="accountId" class="col-xs-3 text-right control-label">担当</label>
					<div class="col-xs-5">
						<select class="form-control" id="accountId" name="accountId">
							<option value="">選択してください</option>
<c:forEach var="account" items="${accountMap}">
							<option value="${account.key}" ${HTMLUtils.writeSelected(account.key,accountId)}>${account.value}</option>
</c:forEach>
						</select>
					</div>
				</div>

				<!-- 商品カテゴリー -->
				<div class="form-group">
					<label class="col-xs-3 text-right control-label">商品カテゴリー</label>
					<div class="col-xs-5">
<c:forEach var="category" items="${categoryMap}">
							<label class="normal"><input type="checkbox" name="categoryId" value="${category.key}" ${HTMLUtils.writeChecked(category.key,categoryId)}>${category.value}</label>&nbsp;
</c:forEach>
					</div>
				</div>

				<!-- 商品名 -->
				<div class="form-group">
					<label for="tradeName" class="col-xs-3 text-right control-label">商品名 <span class="badge">部分一致</span></label>
					<div class="col-xs-5">
						<input type="text" class="form-control" id="tradeName" name="tradeName" value="${tradeName}" placeholder="商品名">
					</div>
				</div>

				<!-- 備考 -->
				<div class="form-group">
					<label for="note" class="col-xs-3 text-right control-label">備考 <span class="badge">部分一致</span></label>
					<div class="col-xs-5">
						<input type="text" class="form-control" id="note" name="note" value="${note}" placeholder="備考">
					</div>
				</div>

				<!-- Submit -->
				<div class="form-group">
					<div class="col-xs-8 col-xs-offset-4">
						<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> 検　索</button>
						<a class="btn btn-default" href="S0020.html" role="button">クリア</a>
					</div>
				</div>
			</form>
		</div>


<!--フッター-->
<jsp:include page="_footer.jsp" />