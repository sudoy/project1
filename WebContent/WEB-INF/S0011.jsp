<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="" />
	<jsp:param name="current" value="" />
</jsp:include>

<!-- 売上登録システム表示 -->
		<div class="container">
			<h1 class="bold check bm20">売上を登録してよろしいですか？</h1>
			<!-- form -->
			<form class="form-horizontal" action="S0011.html" method="POST">

				<!-- 販売日 -->
				<div class="form-group">
					<label for="saleDate" class="col-xs-3 textright control-label">販売日</label>
					<div class="col-xs-2">
						<input type="text" class="form-control" id="saleDate" name="saleDate" value="${form.saleDate}" disabled>
					</div>
				</div>

				<!-- 担当 -->
				<div class="form-group">
					<label for="accountId" class="col-xs-3 textright control-label">担当</label>
					<div class="col-xs-5">
						<select class="form-control" id="accountId" name="accountId" disabled>

							<c:forEach var="a" items="${accountList}">

								<c:if test="${a.accountId == form.accountId}">
									<option value="${a.accountId}" selected>${HTMLUtils.XSS(a.name)}</option>
								</c:if>

								<c:if test="${a.accountId != form.accountId}">
									<option value="${a.accountId}">${HTMLUtils.XSS(a.name)}</option>
								</c:if>

							</c:forEach>

						</select>
					</div>
				</div>

				<!--仕様変更箇所-->
				<!-- 商品カテゴリー -->
				<div class="form-group">
					<label for="categoryId" class="col-xs-3 textright control-label">商品カテゴリー</label>
					<div class="col-xs-5">

						<c:forEach var="c" items="${categoryList}">

							<c:if test="${c.categoryId == form.categoryId}">
								<label class="radio-inline entryradio"><input type="radio" name="categoryId" value="${c.categoryId}" disabled checked>${HTMLUtils.XSS(c.categoryName)}</label>
							</c:if>

							<c:if test="${c.categoryId != form.categoryId}">
								<label class="radio-inline entryradio"><input type="radio" name="categoryId" value="${c.categoryId}" disabled>${HTMLUtils.XSS(c.categoryName)}</label>
							</c:if>

						</c:forEach>

					</div>
				</div>

				<!-- 商品名 -->
				<div class="form-group">
					<label for="tradeName" class="col-xs-3 textright control-label">商品名</label>
					<div class="col-xs-5">
						<input type="text" class="form-control" id="tradeName" name="tradeName" value="${form.tradeName}" placeholder="商品名" disabled>
					</div>
				</div>

				<!-- 単価 -->
				<div class="form-group">
					<label for="unitPrice" class="col-xs-3 textright control-label">単価</label>
					<div class="col-xs-2">
						<input type="text" class="form-control textright" id="unitPrice" name="unitPrice" value="<fmt:formatNumber value="${form.unitPrice}" pattern="#,##0"/>" placeholder="単価" disabled>
					</div>
				</div>

				<!-- 個数 -->
				<div class="form-group">
					<label for="saleNumber" class="col-xs-3 textright control-label">個数</label>
					<div class="col-xs-2">
						<input type="text" class="form-control textright" id="saleNumber" name="saleNumber" value="<fmt:formatNumber value="${form.saleNumber}" pattern="#,##0"/>" placeholder="個数" disabled>
					</div>
				</div>

				<!-- 小計 -->
				<div class="form-group">
					<label class="col-xs-3 textright control-label">小計</label>
					<div class="col-xs-2">
						<input type="text" class="form-control textright" value="${form.subtotal}" placeholder="小計" disabled>
					</div>
				</div>

				<!-- 備考 -->
				<div class="form-group">
					<label for="note" class="col-xs-3 textright control-label">備考</label>
					<div class="col-xs-5">
						<textarea class="form-control" rows="5" id="note" name="note" placeholder="詳細" disabled>${form.note}</textarea>
					</div>
				</div>

				<!-- Submit -->
				<div class="form-group">
					<div class="col-xs-8 col-xs-offset-4">
						<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> ＯＫ</button>
						<a class="btn btn-default" href="S0010.html?cancel=1" role="button">キャンセル</a>
					</div>
				</div>
			</form>
		</div>

<!--フッター-->
<jsp:include page="_footer.jsp" />