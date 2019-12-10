window.onload = function () {

    setTimer(60);
    $("#openSources").click(function () {
        window.open("https://github.com/webbureaucrat/learn-duel-webApp", '_blank');
    })
};

function setTimer(seconds) {
    // jquery selection does not work
    document.querySelector('#countdown').textContent = seconds;
}

function onAnswerChosen(pid) {
    $.ajax({
        url: "/onAnswerChosen?position=" + pid,
        type: 'GET',
        success: function (results) {
            if (typeof (results) == "object") {
            } else {
                //$(".").replaceWith(results);
                html = $.parseHTML(results);
                html.
                document.open();
                document.write(results);
                document.close();
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
    $(".list-group").empty().append([
        `<button type='button' onclick='onAnswerChosen(${question.answers[0].id})' title='p.text'>${question.answers[0].text}</button>`,
        `<button type='button' onclick='onAnswerChosen(${question.answers[1].id})' title='p.text'>${question.answers[1].text}</button>`,
        `<button type='button' onclick='onAnswerChosen(${question.answers[2].id})' title='p.text'>${question.answers[2].text}</button>`,
        `<button type='button' onclick='onAnswerChosen(${question.answers[3].id})' title='p.text'>${question.answers[3].text}</button>`

    ].join('\n'));
}

$(document).ready(function () {
    if (window.location.pathname === '/start') {
        connectWebSocket();
    }
});
