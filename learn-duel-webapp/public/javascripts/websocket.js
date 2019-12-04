
function connectWebSocket() {
    console.log("connecting");
    var websocket = new WebSocket("ws://localhost:9000/websocket");
    // websocket.setTimeout = 1;

    websocket.onopen = function(event) {
        console.log("Connected to Websocket");
    }

    websocket.onclose = function () {
        console.log('Connection with Websocket Closed!');
    };

    websocket.onerror = function (error) {
        console.log('Error in Websocket Occured: ' + error);
    };

    websocket.onmessage = function (gameState) {
        // console.log(gameState.data.currentQuestionTime);
        var jsonObject = JSON.parse(gameState.data);
        if (jsonObject.action === "SHOW_RESULT"){
            //get result
            console.log("show result");
            console.log(jsonObject.players);
            $(".row").replaceWith(jsonObject.players);
        }
    };
}