<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    			<li>
     		 		<a href="C0020.html?button=lastyear&date=${form.date}" aria-label="Previous">
        			<span aria-hidden="true">&laquo;</span>
        			前年
      				</a>
      			</li>
      			<li>
     		 		<a href="C0020.html?button=lastmonth&date=${form.date}" aria-label="Previous">
        			<span aria-hidden="true">&laquo;</span>
        			前月
      				</a>
    			</li>
    		</ul>
    	</div>

    	<div class="col-sm-4 center-cal">

    		<h2>${form.date.getYear()}年${form.date.getMonthValue()}月</h2>

   		</div>

   		<div class="col-sm-4 right-btn">
   			<ul class="pagination">
   				<li>
    				<a href="C0020.html?button=nextmonth&date=${form.date}" aria-label="Next">
      				翌月
        			<span aria-hidden="true">&raquo;</span>
      				</a>
      			</li>

      			<li>
      				<a href="C0020.html?button=nextyear&date=${form.date}" aria-label="Next">
      				翌年
        			<span aria-hidden="true">&raquo;</span>
      				</a>
      			</li>
      		</ul>
      	</div>
	</nav>

	<!--合計売上-->
	<!--前月-->
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading"><h5>前月（${form.lastmonthval}月）の売上合計</h5></div>
			<div class="panel-body text-center"><fmt:formatNumber value="${form.salelastmonth}" pattern="#,##0"/>円</div>
		</div>
	</div>
	<!--今月-->
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading"><h5>今月（${form.monthval}月）の売上合計</h5></div>
			<div class="panel-body text-center"><fmt:formatNumber value="${form.salemonth}" pattern="#,##0"/>円</div>
		</div>
	</div>
	<!--前月比-->
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading"><h5>前月比</h5></div>

				<c:if test="${1 <= form.percent && form.salelastmonth != 0}">
					<div class="panel-body text-center up-color">
					<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>
					<fmt:formatNumber value="${form.percent}" type="PERCENT" maxFractionDigits="2"/>
					</div>
				</c:if>

				<c:if test="${form.percent < 1}">
					<div class="panel-body text-center down-color">
					<span class="glyphicon glyphicon-arrow-down" aria-hidden="true"></span>
					<fmt:formatNumber value="${form.percent}" type="PERCENT" maxFractionDigits="2"/>
					</div>
				</c:if>

				<c:if test="${form.salelastmonth == 0 && 1 <= form.salemonth}">
					<div class="panel-body text-center">
					99999.99%
					</div>
				</c:if>

				<!--前月比下がっているとき→ class="panel-body text-center down-color" class="glyphicon glyphicon-arrow-down"-->

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

			<c:forEach var="f" items="${findList}">
				<tr>

					<td>${f.saleId}</td>
					<td>${f.saleDate}</td>
					<td>${f.categoryName}</td>
					<td>${f.tradeName}</td>

					<td><fmt:formatNumber value="${f.unitPrice}" pattern="#,##0"/></td>
					<td><fmt:formatNumber value="${f.saleNumber}" pattern="#,##0"/></td>
					<td><fmt:formatNumber value="${f.subtotal}" pattern="#,##0"/></td>

				</tr>
			</c:forEach>

				<tr>
					<td colspan="5"></td>
					<th>合計</th>
					<td><fmt:formatNumber value="${form.total}" pattern="#,##0"/></td>
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