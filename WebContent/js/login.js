function login(){
    $.ajax({
        type:"get",
        url:"/TradingPlatformWeb/LoginServlet",
        data:$("#loginForm").serialize(),
        async: true,
        dataType: "text",
        success:function(data){
        		var jsonarray= $.parseJSON(data);
        		if(jsonarray.login === false) {
        			alert("Login failed! Retry.");
        		} else {
        			storeTrader("username", jsonarray.trader.name);
        			storeTrader("traderId", jsonarray.trader.id);
        			location.href = "/TradingPlatformWeb/orderbook.html";
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

function storeTrader(name,value) 
{ 
	sessionStorage.setItem(name,value); 
	alert(value);
}