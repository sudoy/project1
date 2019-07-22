<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="売上登録確認" />
	<jsp:param name="current" value="売上登録" />
</jsp:include>

<!-- 売上登録システム表示 -->
		<div class="container">
			<h1 class="bold check bm20">売上を登録してよろしいですか？</h1>
			<!-- form -->
			<form class="form-horizontal" action="S0011.html" method="POST">

				<!-- 販売日 -->
				<div class="form-group">
					<label for="saleDate" class="col-xs-3 text-right control-label">販売日</label>
					<div class="col-xs-2">
						<input type="text" class="form-control" id="saleDate" name="saleDate" value="${form.saleDate}" disabled>
						<input type="hidden" name="saleDate" value="${form.saleDate}">
					</div>
				</div>

				<!-- 担当 -->
				<div class="form-group">
					<label for="accountId" class="col-xs-3 text-right control-label">担当</label>
					<div class="col-xs-5">
						<select class="form-control" id="accountId" name="accountId" disabled>

							<c:forEach var="a" items="${accountList}">

								<c:if test="${a.accountId == form.accountId}">
									<option value="${a.accountId}" selected>${HTMLUtils.XSS(a.name)}</option>
								</c:if>

							</c:forEach>

						</select>
						<input type="hidden" name="accountId" value="${form.accountId}">
					</div>
				</div>

				<!--仕様変更箇所-->
				<!-- 商品カテゴリー -->
				<div class="form-group">
					<label class="col-xs-3 text-right control-label">商品カテゴリー</label>
					<div class="col-xs-5">

						<c:forEach var="c" items="${categoryList}">

							<label class="radio-inline entryradio ${HTMLUtils.errorMessage(error,'商品カテゴリー')}">
								<input type="radio" name="categoryId" value="${c.categoryId}" ${HTMLUtils.writeChecked(c.categoryId , form.categoryId)} disabled>
								${HTMLUtils.XSS(c.categoryName)}
							</label>

						</c:forEach>
						<input type="hidden" name="categoryId" value="${form.categoryId}">
					</div>
				</div>

				<!-- 商品名 -->
				<div class="form-group">
					<label for="tradeName" class="col-xs-3 text-right control-label">商品名</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" id="tradeName" name="tradeName" value="${form.tradeName}" placeholder="商品名" disabled>
						<input type="hidden" name="tradeName" value="${form.tradeName}">
					</div>
				</div>

				<!-- 単価 -->
				<div class="form-group">
					<label for="unitPrice" class="col-xs-3 text-right control-label">単価</label>
					<div class="col-xs-2">
						<input type="text" class="form-control text-right" id="unitPrice" name="unitPrice" value="${HTMLUtils.numberFormat(form.unitPrice)}" placeholder="単価" disabled>
						<input type="hidden" name="unitPrice" value="${form.unitPrice}">
					</div>
				</div>

				<!-- 個数 -->
				<div class="form-group">
					<label for="saleNumber" class="col-xs-3 text-right control-label">個数</label>
					<div class="col-xs-2">
						<input type="text" class="form-control text-right" id="saleNumber" name="saleNumber" value="${HTMLUtils.numberFormat(form.saleNumber)}" placeholder="個数" disabled>
						<input type="hidden" name="saleNumber" value="${form.saleNumber}">
					</div>
				</div>

				<!-- 小計 -->
				<div class="form-group">
					<label class="col-xs-3 text-right control-label">小計</label>
					<div class="col-xs-2">
						<input type="text" class="form-control text-right" value="${HTMLUtils.numberFormat(form.subTotal)}" placeholder="小計" disabled>

					</div>
				</div>

				<!-- 備考 -->
				<div class="form-group">
					<label for="note" class="col-xs-3 text-right control-label">備考</label>
					<div class="col-xs-5">
						<textarea class="form-control" rows="5" id="note" name="note" placeholder="備考" disabled>${form.note}</textarea>
						<input type="hidden" name="note" value="${form.note}">
					</div>
				</div>

				<!-- Submit -->
				<div class="form-group">
					<div class="col-xs-8 col-xs-offset-4">
						<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> ＯＫ</button>
						<a class="btn btn-default" href="S0010.html?cancel=1&${form.cancelData}" role="button">キャンセル</a>
					</div>
				</div>
			</form>
		</div>

<!--フッター-->
<jsp:include page="_footer.jsp" />