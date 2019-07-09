<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
			<div class="panel-heading"><h5>前月（5月）の売上合計</h5></div>
			<div class="panel-body text-center">1,000,000円</div>
		</div>
	</div>
	<!--今月-->
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading"><h5>今月（6月）の売上合計</h5></div>
			<div class="panel-body text-center">1,200,000円</div>
		</div>
	</div>
	<!--前月比-->
	<div class="col-sm-4">
		<div class="panel panel-default">
			<div class="panel-heading"><h5>前月比</h5></div>
			<div class="panel-body text-center up-color">
				<span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span>120.00％
				<!--前月比下がっているとき→ class="panel-body text-center down-color" class="glyphicon glyphicon-arrow-down"-->
			</div>
		</div>
	</div>

	<!--個別売上-->
	<div class="col-sm-12">
	<div class="panel panel-default">
		<div class="panel-heading"><h5>今月のイチローさんの売上</h5></div>

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
				<tr>
					<td>1</td>
					<td>2015/1/15</td>
					<td>食料品</td>
					<td>からあげ弁当</td>
					<td>450</td>
					<td>3</td>
					<td>1,350</td>
				</tr>
				<tr>
					<td>2</td>
					<td>2015/1/15</td>
					<td>食料品</td>
					<td>あんぱん</td>
					<td>120</td>
					<td>10</td>
					<td>1,200</td>
				</tr>
				<tr>
					<td>3</td>
					<td>2015/1/15</td>
					<td>飲料</td>
					<td>コカコーラ 500ml</td>
					<td>130</td>
					<td>5</td>
					<td>650</td>
				</tr>
				<tr>
					<td colspan="5"></td>
					<th>合計</th>
					<td>3,200</td>
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