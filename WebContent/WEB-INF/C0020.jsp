<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="ダッシュボード" />
	<jsp:param name="current" value="ダッシュボード" />
</jsp:include>


<!--ダッシュボード-->
<div class="container">

	<h1 class="dashtitle">ダッシュボード</h1>

	<!-- メッセージ -->
	<jsp:include page="_message.jsp" />

	<!--仕様変更箇所-->
	<nav aria-label="Page navigation">
		<div class="col-sm-4 left-btn">
			<ul class="pagination">
				<li><a href="C0020.html?button=lastyear&date=${form.date}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						前年
				</a></li>
				<li><a href="C0020.html?button=lastmonth&date=${form.date}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						前月
				</a></li>
			</ul>
		</div>

		<div class="col-sm-4 center-cal">

			<h2>${form.date.getYear()}年${form.date.getMonthValue()}月</h2>

		</div>

		<div class="col-sm-4 right-btn">
			<ul class="pagination">
				<li><a href="C0020.html?button=nextmonth&date=${form.date}"
					aria-label="Next"> 翌月 <span aria-hidden="true">&raquo;</span>
				</a></li>

				<li><a href="C0020.html?button=nextyear&date=${form.date}"
					aria-label="Next"> 翌年 <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</div>
	</nav>

	<!-- グラフ -->
	<input type="hidden" id="graphDate" value="${form.date.getYear()}">
	<div class="col-sm-12 bm20">
		<div class="col-sm-6">
			<canvas id="graph1LastYear"></canvas>
		</div>
		<div class="col-sm-6">
			<canvas id="graph2ThisYear"></canvas>
		</div>
	</div>

	<!--合計売上-->
	<!--前月-->
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>前月（${form.lastMonth.getMonthValue()}月）の売上合計</h5>
			</div>
			<div class="panel-body text-center">${HTMLUtils.numberFormat(form.saleLastMonth)}円</div>
		</div>
	</div>
	<!--今月-->
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>今月（${form.date.getMonthValue()}月）の売上合計</h5>
			</div>
			<div class="panel-body text-center">${HTMLUtils.numberFormat(form.saleMonth)}円</div>
		</div>
	</div>
	<!--前月比-->
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>前月比</h5>
			</div>

			<c:if test="${1 <= form.percent && form.saleLastMonth != 0}">
				<div class="panel-body text-center up-color">
					<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>
					<fmt:formatNumber value="${form.percent}" type="PERCENT"
						maxFractionDigits="2" />
				</div>
			</c:if>

			<c:if test="${form.percent < 1}">
				<div class="panel-body text-center down-color">
					<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
					<fmt:formatNumber value="${form.percent}" type="PERCENT"
						maxFractionDigits="2" />
				</div>
			</c:if>

			<c:if test="${form.saleLastMonth == 0 && 1 <= form.saleMonth}">
				<div class="panel-body text-center">-</div>
			</c:if>

			<c:if test="${form.saleLastMonth == 0 && 0 == form.saleMonth}">
				<div class="panel-body text-center up-color">
					<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>
					<fmt:formatNumber value="1" type="PERCENT" maxFractionDigits="2" />
				</div>
			</c:if>

		</div>
	</div>

	<!--個別売上-->
	<div class="col-sm-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5>今月の${HTMLUtils.XSS(account.name)}さんの売上</h5>
			</div>

			<!-- Table -->
			<table class="table">
				<thead>
					<tr>
						<th class="text-right">No</th>
						<th class="col-sm-2">販売日</th>
						<th class="col-sm-3">商品カテゴリー</th>
						<th class="col-sm-3">商品名</th>
						<th class="col-sm-1 text-right">単価</th>
						<th class="col-sm-1 text-right">個数</th>
						<th class="text-right">税率</th>
						<th class="col-sm-1 text-right">小計</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="f" items="${findList}">
						<tr>

							<td class="text-right">${f.saleId}</td>
							<td>${f.saleDate}</td>
							<td>${HTMLUtils.XSS(f.categoryName)}</td>
							<td>${HTMLUtils.XSS(f.tradeName)}</td>

							<td class="text-right">${HTMLUtils.numberFormat(f.unitPrice)}</td>
							<td class="text-right">${HTMLUtils.numberFormat(f.saleNumber)}</td>
							<td class="text-right">${HTMLUtils.numberFormat(f.rate)}％</td>
							<td class="text-right">${
							HTMLUtils.numberFormat(HTMLUtils.rounding(
							f.subTotal * (1 + f.rate/100)
							))}</td>

						</tr>
					</c:forEach>

					<tr>
						<td colspan="6"></td>
						<th class="text-right">合計</th>
						<td class="text-right">${HTMLUtils.numberFormat(form.total)}</td>
					</tr>
				</tbody>
			</table>

		</div>
	</div>
	<!--個別売上ここまで-->

</div>
<!--class="container"-->

<%-- グラフに渡す値
<script>
var thisYear = ${form.date.getYear()}
var lastYear = ${form.date.getYear() - 1}
var thisYearList = ${form.thisYearList}
var lastYearList = ${form.lastYearList}
var maxSale = ${form.maxSale}
</script>--%>

<!--フッター-->
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/myScript.js"></script>
<link rel="stylesheet" href="bootstrap-datepicker-1.9.0-dist/css/bootstrap-datepicker3.min.css">
<script src="bootstrap-datepicker-1.9.0-dist/js/bootstrap-datepicker.min.js"></script>
<script src="bootstrap-datepicker-1.9.0-dist/locales/bootstrap-datepicker.ja.min.js"></script>
<script src="bootstrap-datepicker-1.9.0-dist/js/mydatepicker.js"></script>
<script src="js/Chart.min.js"></script>
<script src="js/graph2.js"></script>
</body>
</html>