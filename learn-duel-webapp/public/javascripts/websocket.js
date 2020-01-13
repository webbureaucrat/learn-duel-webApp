import {setTimer, displayQuestions} from './LearnDuel';

function connectWebSocket() {


    console.log("connecting");
    var websocket = new WebSocket("ws://localhost:9000/websocket");
    // websocket.setTimeout = 1;

    websocket.onopen = function(event) {
        console.log("Connected to Websocket");
    };

    websocket.onclose = function () {
        console.log('Connection with Websocket Closed!');
    };

    websocket.onerror = function (error) {
        console.log('Error in Websocket Occured: ' + error);
    };

    websocket.onmessage = function (gameState) {
        // console.log(gameState.data.currentQuestionTime);
        let jsonObject = JSON.parse(gameState.data);
        if (jsonObject.action === "TIMER_UPDATE") {
            // jquery selection does not work
            console.log(jsonObject.action.toString());
            document.querySelector('#countdown').textContent = (document.querySelector('#countdown').textContent - 1).toString();
        }
        else if (jsonObject.action === "SHOW_RESULT"){
            //get result
            console.log("show result");
            console.log(jsonObject.players);
            $(".row").replaceWith(jsonObject.players);
        }
        else if (jsonObject.action === "SHOW_GAME") {
            displayQuestions(jsonObject.currentQuestion);
            document.querySelector('#countdown').textContent = (60).toString();
        }

    };
}

export {connectWebSocket};