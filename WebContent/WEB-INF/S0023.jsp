<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.abc.asms.utils.HTMLUtils" %>
<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="売上詳細編集" />
	<jsp:param name="current" value="売上検索" />
</jsp:include>

<!-- 売上詳細編集システム表示 -->
	<div class="container">
		<h1>売上詳細編集</h1>

		<!-- メッセージ -->
		<jsp:include page="_message.jsp" />

		<!-- form -->
		<form class="form-horizontal" action="S0023.html" method="post">
			<%--更新時の確認用で渡すsaleId --%>
			<input type="hidden" name="saleId" value="${form.saleId}">

			<!-- 販売日 -->
			<div class="form-group">
				<label for="saleDate" class="col-xs-3 text-right control-label">販売日 <span class="badge">必須</span></label>
				<div class="col-xs-2">
					<input type="text" class="form-control" id="saleDate" name="saleDate" value="${HTMLUtils.dateFormat(form.saleDate)}">
				</div>
			</div>

			<!-- 担当 -->
			<div class="form-group">
				<label for="accountId" class="col-xs-3 text-right control-label">担当 <span class="badge">必須</span></label>
				<div class="col-xs-5">
					<select class="form-control" id="accountId" name="accountId">
					<c:forEach var="account" items="${form.accountList}">
						<option value="${account.key}"${HTMLUtils.writeSelected(account.key,form.accountId)}>${account.value}</option>
					</c:forEach>
					</select>
				</div>
			</div>

			<!-- 商品カテゴリー -->
			<div class="form-group">
				<label for="categoryId" class="col-xs-3 text-right control-label">商品カテゴリー <span class="badge">必須</span></label>
				<div class="col-xs-5">
					<select class="form-control" id="categoryId" name="categoryId">
					<c:forEach var="category" items="${form.categoryList}">
						<option value="${category.key}"${HTMLUtils.writeSelected(category.key,form.categoryId)}>${category.value}</option>
					</c:forEach>
					</select>
				</div>
			</div>

			<!-- 商品名 -->
			<div class="form-group">
				<label for="tradeName" class="col-xs-3 text-right control-label">商品名 <span class="badge">必須</span></label>
				<div class="col-xs-5">
					<input type="text" class="form-control" id="tradeName" name="tradeName" value="${form.tradeName}" placeholder="商品名">
				</div>
			</div>

			<!-- 単価 -->
			<div class="form-group">
				<label for="unitPrice" class="col-xs-3 text-right control-label">単価 <span class="badge">必須</span></label>
				<div class="col-xs-2">
					<input type="text" class="form-control text-right" id="unitPrice" name="unitPrice" value="${form.unitPrice}" placeholder="単価">
				</div>
			</div>

			<!-- 個数 -->
			<div class="form-group">
				<label for="saleNumber" class="col-xs-3 text-right control-label">個数 <span class="badge">必須</span></label>
				<div class="col-xs-2">
					<input type="text" class="form-control text-right" id="saleNumber" name="saleNumber" value="${form.saleNumber}" placeholder="個数">
				</div>
			</div>

			<!-- 備考 -->
			<div class="form-group">
				<label for="note" class="col-xs-3 text-right control-label">備考</label>
				<div class="col-xs-5">
					<textarea class="form-control" rows="5" id="note" name="note" placeholder="詳細">${form.note}</textarea>
				</div>
			</div>

			<!-- Submit -->
			<div class="form-group">
				<div class="col-xs-8 col-xs-offset-4">
					<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 更　新</button>
					<a class="btn btn-default" href="S0021.html?" role="button">キャンセル</a>
				</div>
			</div>

		</form>
	</div>

<!--フッター-->
<jsp:include page="_footer.jsp" />