import {displayQuestions} from '../../../play-silhouette-seed/public/javascripts/LearnDuel';
import {buildScore, setScoreBackground} from '../../../play-silhouette-seed/public/javascripts/score';

function connectWebSocket() {


    console.log("in connectWebSocket connecting");
    var websocket = new WebSocket("ws://localhost:9000/websocket");

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
        let jsonObject = JSON.parse(gameState.data);
        if (jsonObject.action === "TIMER_UPDATE") {
            console.log(jsonObject.action.toString());
            displayQuestions(jsonObject.currentQuestion);
            // jquery selection does not work here
            document.querySelector('#countdown').textContent = jsonObject.currentQuestionTime;
        }
        else if (jsonObject.action === "SHOW_RESULT"){
            console.log("show result");
            console.log(jsonObject.players);
            setScoreBackground(jsonObject.players[0].wrongAnswers.length);
            $('.row').empty().append(buildScore(jsonObject.players));
        }
        else if (jsonObject.action === "SHOW_GAME") {
            console.log(jsonObject.action.toString());
            displayQuestions(jsonObject.currentQuestion);
            // jquery selection does not work here
            document.querySelector('#countdown').textContent = jsonObject.currentQuestionTime;
        }
    };
}

export {connectWebSocket};