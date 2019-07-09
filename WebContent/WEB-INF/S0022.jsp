<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="売上詳細表示" />
</jsp:include>

<!-- 売上登録システム表示 -->
<div class="container">
	<h1>売上詳細表示</h1>
	<!-- form -->
	<form class="form-horizontal" action="S0023.html" method="post">

		<!-- 販売日 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">販売日</label>
			<div class="col-xs-5">
				<label class="textdown normal">${form.saleDate}</label>
			</div>
		</div>

		<!-- 担当 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">担当</label>
			<div class="col-xs-5">
				<label class="textdown normal">${form.name}</label>
			</div>
		</div>

		<!-- 商品カテゴリー -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">商品カテゴリー</label>
			<div class="col-xs-5">
				<label class="textdown normal">${form.categoryName}</label>
			</div>

		</div>

		<!-- 商品名 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">商品名</label>
			<div class="col-xs-5">
				<label class="textdown normal">${form.tradeName}</label>
			</div>
		</div>

		<!-- 単価 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">単価</label>
			<div class="col-xs-2">
				<label class="text-right textdown normal">${form.unitPrice}</label>
			</div>
		</div>

		<!-- 個数 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">個数</label>
			<div class="col-xs-2">
				<label class="text-right textdown normal">${form.saleNumber}</label>
			</div>
		</div>

		<!-- 小計 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">小計</label>
			<div class="col-xs-2">
				<label class="text-right textdown normal">${form.unitPrice * form.saleNumber}</label>
			</div>
		</div>

		<!-- 備考 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">備考</label>
			<div class="col-xs-5">
				<label class="textdown normal">${form.note}</label>
			</div>
		</div>

		<!-- Submit -->
		<div class="form-group">
			<div class="col-xs-8 col-xs-offset-4">
			<c:if test="${salesAuthority == 'ok'}">
				<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> 編　集</button>
				<button type="submit" class="btn btn-danger"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> 削　除</button>
			</c:if>
				<a class="btn btn-default" href="S0021.html" role="button">キャンセル</a>
			</div>
		</div>

	</form>
</div>

<!--フッター-->
<jsp:include page="_footer.jsp" />