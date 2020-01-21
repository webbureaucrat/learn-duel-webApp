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
    console.log()
    document.querySelector(".text-center").textContent = question.text;
    document.querySelector("#answer0").innerHTML = question.answers[0].text;
    document.querySelector("#answer1").innerHTML = question.answers[1].text;
    document.querySelector("#answer2").innerHTML = question.answers[2].text;
    document.querySelector("#answer3").innerHTML = question.answers[3].text;
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