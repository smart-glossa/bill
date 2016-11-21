var ws = new WebSocket("ws://" + location.host + "/bill/websocket");

ws.onopen = function() {
};

ws.onmessage = function(message) {
	if (message.data === "product") {
		if ($(".displayAll").length > 0) {
			$(".displayAll").remove();
			$(".mainArea")[0].appendChild(displayProducts());
		}
	} else if (message.data === "logout") {
		$("#logout").click();
	}
};

function postToServer(responseContent) {
	ws.send(responseContent);
}

function closeConnect() {
	ws.close();
}
