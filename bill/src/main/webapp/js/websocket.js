var ws = new WebSocket("ws://localhost:8080/bill/websocket");

ws.onopen = function() {
};

ws.onmessage = function(message) {
	if (message.data === "product") {
		$(".displayAll").remove();
		$(".mainArea")[0].appendChild(displayProducts());
	}
};

function postToServer(responseContent) {
	ws.send(responseContent);
}

function closeConnect() {
	ws.close();
}
