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
        success: function (results) {
            if (typeof (results) == "object") {
            } else {
                $(".row").replaceWith(results);
            }
        },
        async: true,
        error: function (results) {
            alert('Make call failed');
        }
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
        success: function (results) {
        },
        async: true,
        error: function (results) {
            alert('Make call failed');
        }
    });

}

// $(document).ready(function () {
//     var app = new Vue({
//         el: '#app',
//         render: function (html) {
//             return html(MainContent.default, {
//                 props: {
//                     show: "about"
//                 }
//             });
//         }
//     })
// });

export {displayQuestions, startGame, onAnswerChosen};