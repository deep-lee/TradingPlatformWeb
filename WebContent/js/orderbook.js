function get_symbol_data() {
	$.ajax({
        type:"get",
        url:"/TradingPlatformWeb/OrderBookServlet",
        data:$("#symbolForm").serialize(),
        async: true,
        dataType: "text",
        success:function(data){
        		var jsonarray= $.parseJSON(data);
        		if(jsonarray.exists === true) {
        			var symbolData = jsonarray.data;
        			process_data(symbolData);
        		} else {
        			alert("No such sumbol. Retry.");
        		}
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        		alert("error");
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}

function process_data(data) {
	delete_table_rows();
	var bidData = data.bid;
	var askData = data.ask;
	var bidLength = bidData.length;
	var askLength = askData.length;
	if (bidLength >= askLength) {
		for (let i = 0; i < askLength; i++) {
			var bidItem = bidData[i];
			var askItem = askData[i];
			insert_row(bidItem.symbol, bidItem.quanlity, bidItem.price, askItem.price, askItem.quanlity);
		}
		
		for (let i = askLength; i < bidLength; i++ ) {
			var bidItem = bidData[i];
			insert_row(bidItem.symbol, bidItem.quanlity, bidItem.price, "", "");
		}
	} else {
		for (let i = 0; i < bidLength; i++) {
			var bidItem = bidData[i];
			var askItem = askData[i];
			insert_row(bidItem.symbol, bidItem.quanlity, bidItem.price, askItem.price, askItem.quanlity);
		}
		
		for (let i = bidLength; i < askLength; i++ ) {
			var askItem = askData[i];
			insert_row(askItem.symbol, "", "", askItem.price, askItem.quanlity);
		}
	}
}

function insert_row(symbol, bidQty, bidPrice, askPrice, askQty) {
    $("#mytable>tbody").append("<tr><th scope='row' abbr='Model' class='spec'>" + symbol + "</th>" + "<td>" + bidQty + "</td>" + "<td>" + bidPrice + "</td>" + "<td>" + askPrice + "</td>" + "<td>" + askQty + "</td>" + "</tr>");
}

function delete_table_rows() {
	var tb = document.getElementById('mytable');
    var rowNum=tb.rows.length;
    for (i=1;i<rowNum;i++)
    {
        tb.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    }
}