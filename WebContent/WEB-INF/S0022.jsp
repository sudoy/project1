<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>
<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="売上詳細表示" />
	<jsp:param name="current" value="売上検索" />
</jsp:include>

<!-- 売上登録システム表示 -->
<div class="container">
	<div class="padding-off col-xs-12">
		<div class="col-xs-8"><h1>売上詳細表示</h1></div>
		<form method="post" action="S0022.html?saleId=${form.saleId}">
			<div class="col-xs-4 text-right topmargin">変更履歴<br>
				<div class="btn-group" role="group" aria-label="...">
					<div class="floatr">
						<input type="submit" class="btn btn-info" value="参照">
					</div>
					<div class="floatr">
						<select class="form-control" id="history" name="history">
<c:forEach var="history" items="${form.histories}" varStatus="i">
							<option value="${history.key}" ${HTMLUtils.writeSelected(history.key,form.historyId)}>${history.value}</option>
</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- メッセージ -->
	<div class="col-xs-12 padding-off">
		<jsp:include page="_message.jsp" />
	</div>
<c:if test="${not empty form.updateAt}">
	<div class="form-group  border-bt col-xs-12">
		<label class="col-xs-1 text-right textdown">${HTMLUtils.S0022_1(form.historyId,form.histories)}日</label>
		<div class="col-xs-5">
			<label class="textdown normal">${form.updateAt}</label>
		</div>
		<label class="col-xs-1 text-right textdown">${HTMLUtils.S0022_1(form.historyId,form.histories)}者</label>
		<div class="col-xs-5">
			<label class="textdown normal">${form.updateBy}</label>
		</div>
	</div>
</c:if>
	<!-- form -->
	<form class="form-horizontal" action="S0023.html" method="post">
		<!-- 販売日 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">販売日</label>
			<div class="col-xs-5">
				<label class="textdown normal">${HTMLUtils.XSS(HTMLUtils.dateFormat(form.saleDate))}</label>
			</div>
		</div>

		<!-- 担当 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">担当</label>
			<div class="col-xs-5">
				<label class="textdown normal">${HTMLUtils.XSS(form.name)}</label>
			</div>
		</div>

		<!-- 商品カテゴリー -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">商品カテゴリー</label>
			<div class="col-xs-5">
				<label class="textdown normal">${HTMLUtils.XSS(form.categoryName)}</label>
			</div>

		</div>

		<!-- 商品名 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">商品名</label>
			<div class="col-xs-5">
				<label class="textdown normal">${HTMLUtils.XSS(form.tradeName)}</label>
			</div>
		</div>

		<!-- 単価 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">単価</label>
			<div class="col-xs-2 right-btn">
				<label class="text-right textdown normal">${HTMLUtils.numberFormat(HTMLUtils.XSS(form.unitPrice))}</label>
			</div>
			<label class="col-xs-1 text-left textdown">円</label>
		</div>

		<!-- 個数 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">個数</label>
			<div class="col-xs-2 right-btn">
				<label class="text-right textdown normal">${HTMLUtils.numberFormat(HTMLUtils.XSS(form.saleNumber))}</label>
			</div>
			<label class="col-xs-1 text-left textdown">個</label>
		</div>

		<!-- 税率 -->
		<div class="form-group">
			<label for="tax" class="col-xs-3 text-right control-label">税率</label>
			<div class="col-xs-2 right-btn">
				<label class="text-right textdown normal">${HTMLUtils.numberFormat(form.rate)}</label>
			</div>
			<label class="col-xs-1 text-left textdown">％</label>
		</div>

		<!-- 小計 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">小計</label>
			<div class="col-xs-2 right-btn">
				<label class="text-right textdown normal">${
					HTMLUtils.numberFormat(HTMLUtils.rounding(
					form.unitPrice * form.saleNumber * (1 + form.rate / 100)
					))}</label>
			</div>
			<label class="col-xs-1 text-left textdown">円</label>
		</div>

		<!-- 備考 -->
		<div class="form-group">
			<label class="col-xs-3 text-right textdown">備考</label>
			<div class="col-xs-5">
				<label class="textdown normal">${HTMLUtils.newLine(HTMLUtils.XSS(form.note))}</label>
			</div>
		</div>

		<!-- Submit -->
		<div class="form-group">
			<div class="col-xs-8 col-xs-offset-4">
				<c:if test="${form.salesAuthority == 'ok'}">
					<a class="btn btn-primary" href="S0023.html?saleId=${form.saleId}"
						role="button"><span class="glyphicon glyphicon-ok"
						aria-hidden="true"></span> 編 集</a>
					<a class="btn btn-danger" href="S0025.html?saleId=${form.saleId}"
						role="button"><span class="glyphicon glyphicon-remove"
						aria-hidden="true"></span> 削 除</a>
				</c:if>
				<a class="btn btn-default" href="S0021.html" role="button">キャンセル</a>
			</div>
		</div>

	</form>
</div>

<!--フッター-->
<jsp:include page="_footer.jsp" />