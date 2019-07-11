<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="売上登録" />
	<jsp:param name="current" value="売上登録" />
</jsp:include>



<!-- メッセージ -->
<jsp:include page="_message.jsp" />

<!-- 売上登録システム表示 -->
		<div class="container">
			<h1>売上登録</h1>
			<!-- form -->
			<form class="form-horizontal" method="POST" action="S0010.html">

				<!-- 販売日 -->
				<div class="form-group">
					<label for="saleDate" class="col-xs-3 text-right control-label">販売日 <span class="badge">必須</span></label>
					<div class="col-xs-2">
						<input type="text" class="form-control" id="saleDate" name="saleDate" value="${saleDate}">
					</div>
				</div>

				<!-- 担当 -->
				<div class="form-group">
					<label for="accountId" class="col-xs-3 text-right control-label">担当 <span class="badge">必須</span></label>
					<div class="col-xs-5">
						<select class="form-control" id="accountId" name="accountId">
							<option value="">選択してください</option>

							<c:forEach var="a" items="${accountList}">

								<c:if test="${a.accountId == form.accountId}">
									<option value="${a.accountId}" selected>${a.name}</option>
								</c:if>

								<c:if test="${a.accountId != form.accountId}">
									<option value="${a.accountId}">${a.name}</option>
								</c:if>

							</c:forEach>

						</select>
					</div>
				</div>

				<!-- 商品カテゴリー -->
				<div class="form-group">
					<label for="categoryId" class="col-xs-3 text-right control-label">商品カテゴリー <span class="badge">必須</span></label>
					<div class="col-xs-5">
						<select class="form-control" id="categoryId" name="categoryId">
							<option value="">選択してください</option>

							<c:forEach var="c" items="${categoryList}">

								<c:if test="${c.categoryId == form.categoryId}">
									<option value="${c.categoryId}" selected>${c.categoryName}</option>
								</c:if>

								<c:if test="${c.categoryId != form.categoryId}">
									<option value="${c.categoryId}">${c.categoryName}</option>
								</c:if>

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
						<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 登　録</button>
					</div>
				</div>
			</form>
		</div>
		<!--/container-fluid-->

<!--フッター-->
<jsp:include page="_footer.jsp" />