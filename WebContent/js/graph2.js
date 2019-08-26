(function(){
	$.ajax({
        url: "graph",
        type: "GET",
        data: {date : $("#graphDate").val()},
	 	dataType: 'json'
      }).done(function (result) {
    	  var thisYear = result["thisYear"];
    	  var thisYearList = result["thisYearList"];console.log(thisYearList);
    	  var lastYear = result["lastYear"];
    	  var lastYearList = result["lastYearList"];console.log(lastYearList);
    	  var maxSale = result["maxSale"];

    	  var ctx = document.getElementById("graph2ThisYear");
    	  var graph2ThisYear = new Chart(ctx, {

			//グラフの種類
			type: 'line',

			//データの設定
			data: {

				//データ項目のラベル
				labels: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],

				//データセット
				datasets: [{

					//凡例
					label: thisYear + "年の売上",

					//背景色
					backgroundColor: "rgba(113,159,196,0.4)",

					//枠線の色
					borderColor: "rgba(113,159,196,1)",

					//直線
					lineTension: 0,

					//ホバーの認識範囲
					pointHitRadius: 15,

					//グラフのデータ
					data: thisYearList

				}]
			},

			//オプションの設定
			options: {
				scales: {
					yAxes: [{
						ticks: {
							//最小値を0にする
							beginAtZero: true,
							min: 0,
							//最大値
							suggestedMax: maxSale
						}
					}]
				},
				tooltips: {
					callbacks:{
						label: function (tooltipItem, data) {
							return data.datasets[0].data[tooltipItem.index] + "万";
						}
					}
				}
			}
		});
    	  var ctx = document.getElementById("graph1LastYear");
    	  var graph1LastYear = new Chart(ctx, {

			//グラフの種類
			type: 'line',

			//データの設定
			data: {

				//データ項目のラベル
				labels: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],

				//データセット
				datasets: [{

					//凡例
					label: lastYear + "年の売上",

					//背景色
					backgroundColor: "rgba(197,197,197,0.4)",

					//枠線の色
					borderColor: "rgba(197,197,197,1)",

					//直線
					lineTension: 0,

					//ホバーの認識範囲
					pointHitRadius: 15,

					//グラフのデータ
					data: lastYearList

				}]
			},

			//オプションの設定
			options: {
				scales: {
					yAxes: [{
						ticks: {
							//最小値を0にする
							beginAtZero: true,
							min: 0,
							//最大値
							suggestedMax: maxSale
						}
					}]
				},
				tooltips: {
					callbacks:{
						label: function (tooltipItem, data) {
							return data.datasets[0].data[tooltipItem.index] + "万";
						}
					}
				},
			}
		});
	});
}());