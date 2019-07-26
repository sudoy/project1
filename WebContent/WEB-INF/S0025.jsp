<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="売上詳細削除確認" />
	<jsp:param name="current" value="売上検索" />
</jsp:include>

<!-- 売上削除システム表示 -->
<div class="container">
	<h1 class="bold check bm20">売上を削除してよろしいですか？</h1>

	<!-- form -->
	<form class="form-horizontal" method="post" action="S0025.html">
		<%-- 削除処理用saleId --%>
		<input type="hidden" name="saleId" value="${form.saleId}">

		<!-- 販売日 -->
		<div class="form-group">
			<label for="saleDate" class="col-xs-3 text-right control-label">販売日</label>
			<div class="col-xs-2">
				<input type="text" class="form-control" id="saleDate" name="saleDate" value="${HTMLUtils.XSS(HTMLUtils.dateFormat(form.saleDate))}" disabled>
			</div>
		</div>

		<!-- 担当 -->
		<div class="form-group">
			<label for="accountId" class="col-xs-3 text-right control-label">担当</label>
			<div class="col-xs-5">
				<select class="form-control" id="accountId" name="accountId" disabled>
					<option value="">${HTMLUtils.XSS(form.name)}</option>
				</select>
			</div>
		</div>

		<!-- 商品カテゴリー -->
		<div class="form-group">
			<label class="col-xs-3 text-right control-label">商品カテゴリー</label>
			<div class="col-xs-5">
				<label class="radio-inline">
					<input type="radio" name="categoryId" value="${form.categoryId}" checked disabled>
					${HTMLUtils.XSS(form.categoryName)}
				</label>
			</div>
		</div>

		<!-- 商品名 -->
		<div class="form-group">
			<label for="tradeName" class="col-xs-3 text-right control-label">商品名</label>
			<div class="col-xs-5">
				<input type="text" class="form-control" id="tradeName" name="tradeName" value="${HTMLUtils.XSS(form.tradeName)}" placeholder="商品名" disabled>
			</div>
		</div>

		<!-- 単価 -->
		<div class="form-group">
			<label for="unitPrice" class="col-xs-3 text-right control-label">単価</label>
			<div class="col-xs-2">
				<input type="text" class="form-control text-right" id="unitPrice" name="unitPrice" value="${HTMLUtils.numberFormat(form.unitPrice)}" placeholder="単価" disabled>
			</div>
		</div>

		<!-- 個数 -->
		<div class="form-group">
			<label for="saleNumber" class="col-xs-3 text-right control-label">個数</label>
			<div class="col-xs-2">
				<input type="text" class="form-control text-right" id="saleNumber" name="saleNumber" value="${HTMLUtils.numberFormat(form.saleNumber)}" placeholder="個数" disabled>
			</div>
		</div>

		<!-- 小計 -->
		<div class="form-group">
			<label class="col-xs-3 text-right control-label">小計</label>
			<div class="col-xs-2">
				<input type="text" class="form-control text-right" value="${HTMLUtils.numberFormat(form.subtotal)}" placeholder="小計" disabled>
			</div>
		</div>

		<!-- 備考 -->
		<div class="form-group">
			<label for="note" class="col-xs-3 text-right control-label">備考</label>
			<div class="col-xs-5">
				<textarea class="form-control" rows="5" id="note" name="note" placeholder="備考" disabled>${HTMLUtils.XSS(form.note)}</textarea>
			</div>
		</div>

		<!-- Submit -->
		<div class="form-group">
			<div class="col-xs-8 col-xs-offset-4">
				<button type="submit" class="btn btn-danger">
					<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
					ＯＫ
				</button>
				<a class="btn btn-default" href="S0022.html?saleId=${form.saleId}" role="button">キャンセル</a>
				<!-- 売上詳細表示画面へ -->
			</div>
		</div>

	</form>
</div>
<!-- container -->

<!--フッター-->
<jsp:include page="_footer.jsp" />