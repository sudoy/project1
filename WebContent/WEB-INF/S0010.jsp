<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="売上登録" />
	<jsp:param name="current" value="売上登録" />
</jsp:include>


<!-- 売上登録システム表示 -->
<div class="container">

	<h1>売上登録</h1>

	<!-- メッセージ -->
	<jsp:include page="_message.jsp" />

	<!-- form -->
	<form class="form-horizontal" method="POST" action="S0010.html">

		<!-- 販売日 -->
		<div class="form-group">
			<label for="saleDate"
				class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'販売日')}">販売日
				<span class="badge">必須</span>
			</label>
			<div class="col-xs-2">
				<input type="text"
					class="form-control ${HTMLUtils.errorFrame(error,'販売日')}"
					id="saleDate" name="saleDate" value="${HTMLUtils.XSS(form.saleDate)}"
					placeholder="販売日" autofocus>
			</div>
		</div>

		<!-- 担当 -->
		<div class="form-group">
			<label for="accountId"
				class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'担当')}">担当
				<span class="badge">必須</span>
			</label>
			<div class="col-xs-5">
				<select class="form-control ${HTMLUtils.errorFrame(error,'担当')}"
					id="accountId" name="accountId">
					<option value="">選択してください</option>

					<c:forEach var="a" items="${accountList}">

						<option value="${a.accountId}"
							${HTMLUtils.writeSelected(a.accountId,form.accountId)}>${HTMLUtils.XSS(a.name)}</option>

					</c:forEach>

				</select>
			</div>
		</div>

		<!-- 商品カテゴリー -->
		<div class="form-group">
			<label
				class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'商品カテゴリー')}">商品カテゴリー
				<span class="badge">必須</span>
			</label>
			<div class="col-xs-5">

				<c:forEach var="c" items="${categoryList}">

					<label
						class="radio-inline entryradio ${HTMLUtils.errorMessage(error,'商品カテゴリー')}"><input
						type="radio" name="categoryId" value="${c.categoryId}"
						${HTMLUtils.writeChecked(c.categoryId,form.categoryId)}>${HTMLUtils.XSS(c.categoryName)}</label>

				</c:forEach>

			</div>
		</div>

		<!-- 商品名 -->
		<div class="form-group">
			<label for="tradeName"
				class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'商品名')}">商品名
				<span class="badge">必須</span>
			</label>
			<div class="col-xs-5">
				<input type="text"
					class="form-control ${HTMLUtils.errorFrame(error,'商品名')}"
					id="tradeName" name="tradeName" value="${HTMLUtils.XSS(form.tradeName)}"
					placeholder="商品名">
			</div>
		</div>

		<!-- 単価 -->
		<div class="form-group">
			<label for="unitPrice"
				class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'単価')}">単価
				<span class="badge">必須</span>
			</label>
			<div class="col-xs-2">
				<input type="text"
					class="form-control text-right ${HTMLUtils.errorFrame(error,'単価')}"
					id="unitPrice" name="unitPrice" value="${HTMLUtils.XSS(form.unitPrice)}"
					placeholder="単価">
			</div>
		</div>

		<!-- 個数 -->
		<div class="form-group">
			<label for="saleNumber"
				class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'個数')}">個数
				<span class="badge">必須</span>
			</label>
			<div class="col-xs-2">
				<input type="text"
					class="form-control text-right ${HTMLUtils.errorFrame(error,'個数')}"
					id="saleNumber" name="saleNumber" value="${HTMLUtils.XSS(form.saleNumber)}"
					placeholder="個数">
			</div>
		</div>

		<!-- 備考 -->
		<div class="form-group">
			<label for="note"
				class="col-xs-3 text-right control-label ${HTMLUtils.errorMessage(error,'備考')}">備考</label>
			<div class="col-xs-5">
				<textarea class="form-control ${HTMLUtils.errorFrame(error,'備考')}"
					rows="5" id="note" name="note" placeholder="備考">${HTMLUtils.XSS(form.note)}</textarea>
			</div>
		</div>

		<!-- Submit -->
		<div class="form-group">
			<div class="col-xs-8 col-xs-offset-4">
				<button type="submit" class="btn btn-primary">
					<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 登 録
				</button>
			</div>
		</div>
	</form>
</div>
<!--/container-fluid-->

<!--フッター-->
<jsp:include page="_footer.jsp" />