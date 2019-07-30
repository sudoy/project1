<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<!--仕様変更-->
	<!--ページ移動-->

	<div class="col-sm-3  pagemove">

		<div class="input-group">
			<input type="text" class="form-control" value="${form.page}" placeholder="ページ数" name="page">
			<span class="input-group-btn">
			<a class="btn btn-info" href="S0021.html?page=${form.page}" role="button">ページ移動</a>
			</span>

		</div><!-- /input-group -->
	</div><!--ページ移動-->



	<!--ソート-->

	<div class="col-sm-offset-5 col-sm-4 sort">
		<form class="form-inline" method="POST" action="S0021.html">
			<div class="form-group">
				<select class="form-control" id="item" name="item">
					<option value="s.sale_id" ${HTMLUtils.writeSelected('s.sale_id',form.item)}>NO</option>
					<option value="s.sale_date" ${HTMLUtils.writeSelected('s.sale_date',form.item)}>販売日</option>
					<option value="name" ${HTMLUtils.writeSelected('name',form.item)}>担当</option>
					<option value="category_name" ${HTMLUtils.writeSelected('category_name',form.item)}>商品カテゴリー</option>
					<option value="s.unit_price" ${HTMLUtils.writeSelected('s.unit_price',form.item)}>単価</option>
					<option value="s.sale_number" ${HTMLUtils.writeSelected('s.sale_number',form.item)}>個数</option>
					<option value="rate" ${HTMLUtils.writeSelected('rate',form.item)}>税率</option>
					<option value="total" ${HTMLUtils.writeSelected('total',form.item)}>小計</option>
				</select>
			</div>
			<div class="form-group">
				<select class="form-control" id="sort" name="sort">
					<option value="asc" ${HTMLUtils.writeSelected('asc',form.sort)}>昇順</option>
					<option value="desc" ${HTMLUtils.writeSelected('desc',form.sort)}>降順</option>
				</select>
			</div>
			<button type="submit" class="btn btn-success">ソート</button>
		</form>
	</div><!---->
	<!--ソート-->



	<table class="table table-hover table-border-top">
		<thead>
			<tr>
				<th class="col-sm-1">操作</th>
				<th class="text-right">No</th>
				<th class="col-sm-1">販売日</th>
				<th class="">担当</th>
				<th class="col-sm-2">商品カテゴリー</th>
				<th class="col-sm-4">商品名</th>
				<th class="col-sm-1 text-right">単価</th>
				<th class="col-sm-1 text-right">個数</th>
				<th class="text-right">税率</th>
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
					<td class="text-nowrap">${HTMLUtils.newLine(HTMLUtils.XSS(HTMLUtils.setNewLine(saleData.accountName)))}</td>
					<td>${HTMLUtils.XSS(saleData.categoryName)}</td>
					<td>${HTMLUtils.XSS(saleData.tradeName)}</td>
					<td class="text-right">${HTMLUtils.numberFormat(saleData.unitPrice)}</td>
					<td class="text-right">${HTMLUtils.numberFormat(saleData.saleNumber)}</td>
					<td class="text-right">${HTMLUtils.numberFormat(saleData.rate)}％</td>
					<td class="text-right">
					${HTMLUtils.numberFormat(HTMLUtils.rounding(
					saleData.unitPrice * saleData.saleNumber * (1 + saleData.rate / 100)
					))}
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>



	<!--仕様変更-->

	<!--ページング-->
	<div class="col-sm-offset-2 col-sm-8 page">
		<div class="col-sm-offset-2">
			<nav aria-label="Page navigation">
				<ul class="pagination">

					<li class="disabled">
						<a href="#" aria-label="Previous">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-left"></span>
						</a>
					</li>

					<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">6</a></li>
					<li><a href="#">7</a></li>
					<li><a href="#">8</a></li>
					<li><a href="#">9</a></li>
					<li>
						<a href="#" aria-label="Next">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</div><!--ページング-->

	<!--ページング-->
	<div class="col-sm-offset-2 col-sm-8 page">
		<div class="col-sm-offset-2">
			<nav aria-label="Page navigation">
				<ul class="pagination">

					<li>
						<a href="#" aria-label="Previous">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-left"></span>
						</a>
					</li>

					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">6</a></li>
					<li><a href="#">7</a></li>
					<li><a href="#">8</a></li>
					<li class="active"><a href="#">9<span class="sr-only">(current)</span></a></li>
					<li class="disabled">
						<a href="#" aria-label="Next">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</div><!--ページング-->



	<!--ページング-->
	<div class="col-sm-offset-2 col-sm-8 page">
		<div class="col-sm-offset-2">
			<nav aria-label="Page navigation">
				<ul class="pagination">

					<li class="disabled">
						<a href="#" aria-label="Previous">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-left"></span>
						</a>
					</li>

					<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">6</a></li>
					<li><a href="#">7</a></li>
					<li><a href="#">8</a></li>
					<li class="disabled"><a href="#">…</a></li>
					<li>
						<a href="#" aria-label="Next">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</div><!--ページング-->



	<!--ページング-->
	<div class="col-sm-offset-2 col-sm-8 page">
		<div class="col-sm-offset-2">
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li>
						<a href="#" aria-label="Previous">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-left"></span>
						</a>
					</li>

					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li class="active"><a href="#">5<span class="sr-only">(current)</span></a></li><!--現在ページ-->
					<li><a href="#">6</a></li>
					<li><a href="#">7</a></li>
					<li><a href="#">8</a></li>
					<li><a href="#" class="disabled">…</a></li>
					<li>
						<a href="#" aria-label="Next">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</div><!--ページング-->


	<!--ページング-->
	<div class="col-sm-offset-2 col-sm-8 page">
		<div class="col-sm-offset-2">
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li>
						<a href="#" aria-label="Previous">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-left"></span>
						</a>
					</li>

					<li class="disabled"><a href="#">…</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li class="active"><a href="#">6<span class="sr-only">(current)</span></a></li><!--現在ページ-->
					<li><a href="#">7</a></li>
					<li><a href="#">8</a></li>
					<li><a href="#">9</a></li>
					<li><a href="#">10</a></li>
					<li>
						<a href="#" aria-label="Next">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</div><!--ページング-->

	<!--ページング-->
	<div class="col-sm-offset-2 col-sm-8 page">
		<div class="col-sm-offset-2">
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li>
						<a href="#" aria-label="Previous">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-left"></span>
						</a>
					</li>

					<li class="disabled"><a href="#">…</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">6</a></li>
					<li><a href="#">7</a></li>
					<li class="active"><a href="#">8<span class="sr-only">(current)</span></a></li><!--現在ページ-->
					<li><a href="#">9</a></li>
					<li><a href="#">10</a></li>
					<li>
						<a href="#" aria-label="Next">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</div><!--ページング-->



	<!--ページング-->
	<div class="col-sm-offset-2 col-sm-8 page">
		<div class="col-sm-offset-2">
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li>
						<a href="#" aria-label="Previous">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-left"></span>
						</a>
					</li>

					<li class="disabled"><a href="#">…</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">6</a></li>
					<li><a href="#">7</a></li>
					<li><a href="#">8</a></li>
					<li><a href="#">9</a></li>
					<li class="active"><a href="#">10<span class="sr-only">(current)</span></a></li><!--現在ページ-->
					<li class="disabled">
						<a href="#" aria-label="Previous">
						<span aria-hidden="true" class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</li>
				</ul>
			</nav>
		</div>
	</div><!--ページング-->





</div>
<!--class="container-field"-->


<!--フッター-->
<jsp:include page="_footer.jsp" />