<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>
<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="売上詳細編集確認" />
	<jsp:param name="current" value="売上検索" />
</jsp:include>

<!-- 売上登録システム表示 -->
<div class="container">
	<h1 class="bold check bm20">売上を編集してよろしいですか？</h1>

	<!-- form -->
	<form class="form-horizontal" action="S0024.html" method="post">
		<%--更新時の確認用で渡すsaleId --%>
		<input type="hidden" name="saleId" value="${form.saleId}">

		<!-- 販売日 -->
		<div class="form-group">
			<label for="saleDate" class="col-xs-3 text-right textdown">販売日</label>
			<div class="col-xs-2">
				<input type="text" class="form-control" id="saleDate" name="saleDate" value="${HTMLUtils.XSS(form.saleDate)}" disabled>
				<input type="hidden" name="saleDate" value="${HTMLUtils.XSS(form.saleDate)}">
			</div>
		</div>

		<!-- 担当 -->
		<div class="form-group">
			<label for="accountId" class="col-xs-3 text-right textdown">担当</label>
			<div class="col-xs-5">
				<select class="form-control" id="accountId" name="accountId" disabled>
					<option value="${form.accountId}">${HTMLUtils.XSS(form.name)}</option>
				</select>
				<input type="hidden" name="accountId" value="${form.accountId}">
			</div>
		</div>

		<!-- 商品カテゴリー -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">商品カテゴリー</label>
			<div class="col-xs-5">
				<label class="radio-inline">
					<input type="radio" name="categoryId" value="${form.categoryId}" checked disabled>${HTMLUtils.XSS(form.categoryName)}
				</label>
				<input type="hidden" name="categoryId" value="${form.categoryId}">
			</div>
		</div>

		<!-- 商品名 -->
		<div class="form-group">
			<label for="tradeName" class="col-xs-3 text-right textdown">商品名</label>
			<div class="col-xs-5">
				<input type="text" class="form-control" id="tradeName" name="tradeName" value="${HTMLUtils.XSS(form.tradeName)}" placeholder="商品名" disabled> <input type="hidden" name="tradeName" value="${HTMLUtils.XSS(form.tradeName)}">
			</div>
		</div>

		<!-- 単価 -->
		<div class="form-group">
			<label for="unitPrice" class="col-xs-3 text-right textdown">単価</label>
			<div class="col-xs-2">
				<input type="text" class="form-control text-right" id="unitPrice" name="unitPrice" value="${HTMLUtils.numberFormat(form.unitPrice)}"placeholder="単価" disabled>
				<input type="hidden" name="unitPrice" value="${form.unitPrice}">
			</div>
			<label class="col-xs-1 text-left textdown">円</label>
		</div>

		<!-- 個数 -->
		<div class="form-group">
			<label for="saleNumber" class="col-xs-3 text-right textdown">個数</label>
			<div class="col-xs-2">
				<input type="text" class="form-control text-right" id="saleNumber" name="saleNumber" value="${HTMLUtils.numberFormat(form.saleNumber)}" placeholder="個数" disabled>
				<input type="hidden" name="saleNumber" value="${form.saleNumber}">
			</div>
			<label class="col-xs-1 text-left textdown">個</label>
		</div>

		<!-- 税率 -->
		<div class="form-group">
			<label for="tax" class="col-xs-3 text-right control-label">税率</label>
			<div class="col-xs-2 right-btn">
				<input type="tax" class="form-control text-right" value="${HTMLUtils.numberFormat(form.rate)}" placeholder="小計" disabled>
			</div>
			<label class="col-xs-1 text-left textdown">％</label>
		</div>

		<!-- 小計 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">小計</label>
			<div class="col-xs-2">
				<input type="text" class="form-control text-right" value="${
					HTMLUtils.numberFormat(HTMLUtils.rounding(
					form.unitPrice * form.saleNumber * (1 + form.rate / 100)
					))}" placeholder="小計" disabled>
			</div>
			<label class="col-xs-1 text-left textdown">円</label>
		</div>

		<!-- 備考 -->
		<div class="form-group">
			<label for="note" class="col-xs-3 text-right textdown">備考</label>
			<div class="col-xs-5">
				<textarea class="form-control" rows="5" id="note" name="note" placeholder="備考" disabled>${HTMLUtils.XSS(form.note)}</textarea>
				<input type="hidden" name="note" value="${HTMLUtils.XSS(form.note)}">
			</div>
		</div>

		<!-- Submit -->
		<div class="form-group">
			<div class="col-xs-8 col-xs-offset-4">
				<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> ＯＫ
				</button>
				<a class="btn btn-default" href="S0023.html?${form.input}" role="button">キャンセル</a>
			</div>
		</div>
	</form>
</div>

<!--フッター-->
<jsp:include page="_footer.jsp" />