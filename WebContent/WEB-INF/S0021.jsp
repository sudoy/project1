<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.abc.asms.utils.HTMLUtils"%>

<!-- ヘッダー -->
<jsp:include page="_header.jsp">
	<jsp:param name="page" value="売上検索結果表示" />
	<jsp:param name="current" value="売上検索" />
</jsp:include>



<div class="container">
	<h1>売上検索結果表示</h1>
	<!-- メッセージ -->
	<jsp:include page="_message.jsp" />
	<table class="table table-hover table-border-top">
		<thead>
			<tr>
				<th class="col-sm-1">操作</th>
				<th class="text-right">No</th>
				<th class="col-sm-1">販売日</th>
				<th class="col-sm">担当</th>
				<th class="col-sm-2">商品カテゴリー</th>
				<th class="col-sm-3">商品名</th>
				<th class="col-sm-1 text-right">単価</th>
				<th class="col-sm-1 text-right">個数</th>
				<th class="col-sm-1 text-right">小計</th>
			</tr>
		</thead>
		<tbody>
<c:forEach var="saleData" items="${list}">
			<tr class="table-border-top">
				<td><a class="btn btn-primary"
					href="S0022.html?saleId=${saleData.saleId}"> <span
						class="glyphicon glyphicon-ok" aria-hidden="true"></span> 詳細
				</a></td>
				<td class="text-right">${saleData.saleId}</td>
				<td>${saleData.date}</td>
				<td>${HTMLUtils.XSS(saleData.accountName)}</td>
				<td>${HTMLUtils.XSS(saleData.categoryName)}</td>
				<td>${HTMLUtils.XSS(saleData.tradeName)}</td>
				<td class="text-right">
				<fmt:formatNumber value="${saleData.unitPrice}" pattern="#,##0" />
				</td>
				<td class="text-right">
				<fmt:formatNumber value="${saleData.saleNumber}" pattern="#,##0" />
				</td>
				<td class="text-right">
				<fmt:formatNumber value="${saleData.unitPrice * saleData.saleNumber}" pattern="#,##0" />
				</td>
			</tr>
</c:forEach>
		</tbody>
	</table>

</div>
<!--class="container-field"-->


<!--フッター-->
<jsp:include page="_footer.jsp" />