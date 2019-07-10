<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="ダッシュボード" />
</jsp:include>


<!--ダッシュボード-->
<div class="container">
	<h1>ダッシュボード</h1>

	<!--合計売上-->
	<!--前月-->
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading"><h5>前月（${lastmonthval}月）の売上合計</h5></div>
			<c:set var="salelastmonth" value="${salelastmonth}" />
			<div class="panel-body text-center"><fmt:formatNumber value="${salelastmonth}" pattern="#,##0"/>円</div>
		</div>
	</div>
	<!--今月-->
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading"><h5>今月（${monthval}月）の売上合計</h5></div>
			<c:set var="salemonth" value="${salemonth}" />
			<div class="panel-body text-center"><fmt:formatNumber value="${salemonth}" pattern="#,##0"/>円</div>
		</div>
	</div>
	<!--前月比-->
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading"><h5>前月比</h5></div>
			<div class="panel-body text-center up-color">
				<c:if test="${1 <= ratio}">
					<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>120.00％
				</c:if>

				<!--前月比下がっているとき→ class="panel-body text-center down-color" class="glyphicon glyphicon-arrow-down"-->
			</div>
		</div>
	</div>

	<!--個別売上-->
	<div class="col-sm-12">
	<div class="panel panel-default">
		<div class="panel-heading"><h5>今月の${account.name}さんの売上</h5></div>

		<!-- Table -->
		<table class="table">
			<thead>
				<tr>
					<th class="col-sm-1">No</th>
					<th class="col-sm-2">販売日</th>
					<th class="col-sm-3">商品カテゴリー</th>
					<th class="col-sm-3">商品名</th>
					<th class="col-sm-1">単価</th>
					<th class="col-sm-1">個数</th>
					<th class="col-sm-1">小計</th>
				</tr>
			</thead>
			<tbody>

			<c:forEach var="f" items="${findList}" varStatus="i">
				<tr>

					<td>${i.count}</td>
					<td>${f.saleDate}</td>
					<td>${f.categoryName}</td>
					<td>${f.tradeName}</td>

					<c:set var="unitPrice" value="${f.unitPrice}" />
					<td><fmt:formatNumber value="${unitPrice}" pattern="#,##0"/></td>

					<c:set var="saleNumber" value="${f.saleNumber}" />
					<td><fmt:formatNumber value="${saleNumber}" pattern="#,##0"/></td>

					<c:set var="subtotal" value="${f.subtotal}" />
					<td><fmt:formatNumber value="${subtotal}" pattern="#,##0"/></td>


				</tr>
			</c:forEach>

				<tr>
					<td colspan="5"></td>
					<th>合計</th>
					<c:set var="total" value="${total}" />
					<td><fmt:formatNumber value="${total}" pattern="#,##0"/></td>
				</tr>
			</tbody>
		</table>

		</div>
	</div><!--個別売上ここまで-->

</div><!--class="container"-->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>