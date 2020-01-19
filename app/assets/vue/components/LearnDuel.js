window.onload = function () {
    // setTimer(60);
    $("#openSources").click(function () {
        window.open("https://github.com/webbureaucrat/learn-duel-webApp", '_blank');
    })
};

// function setTimer(seconds) {
//     // jquery selection does not work
//     // if (window.location.pathname === '/start') {
//         document.querySelector('#countdown').textContent = seconds;
//     // }
// }

function onAnswerChosen(pid) {
    $.ajax({
        url: "/onAnswerChosen?position=" + pid,
        type: 'GET',
        success: function (results) {},
        async: true,
        error: function (results) {}
    });
}

function displayQuestions(question) {
    $(".text-center").text(question.text);
    $("#answer0").html(question.answers[0].text);
    $("#answer1").html(question.answers[1].text);
    $("#answer2").html(question.answers[2].text);
    $("#answer3").html(question.answers[3].text);
}

function startGame() {
    $.ajax({
        url: "/start",
        type: 'GET',
        success: function (results) {},
        async: true,
        error: function (results) {}
    });

}

export {displayQuestions, startGame, onAnswerChosen};