function startTimer(duration, display) {
    let timer = duration, minutes, seconds;
    setInterval(function () {
        //minutes = parseInt(timer / 60, 10)
        seconds = parseInt(timer % 60, 10);

        seconds = seconds < 10 ? "0" + seconds : seconds;

        document.querySelector(display).textContent = seconds;

        if (--timer < 0) {
            timer = duration;
        }
    }, 1000);
}

window.onload = function () {
    let oneMinute = 60,
        display = "#countdown";
    startTimer(oneMinute, display);

    $("#openSources").click(function () {
        window.open("https://github.com/webbureaucrat/learn-duel-webApp", '_blank');
    })
}
;

function onAnswerChosen_bla(pid) {
    $.ajax({
        url: "/onAnswerChosen?position=" + pid,
        type: 'GET',
        success: function (results) {
            if (typeof (results) == "object") {
                //display results if necessary
                console.log(typeof (results));
                displayQuestions(results);
            } else {
                console.log(typeof (results));
                console.log(results);
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
    $(".list-group").empty().append([
        "<button type='button' onclick='onAnswerChosen_bla(" + question.answers[0].id + ")' title='p.text'>" + question.answers[0].text + "</button>",
        "<button type='button' onclick='onAnswerChosen_bla(" + question.answers[1].id + ")' title='p.text'>" + question.answers[1].text + "</button>",
        "<button type='button' onclick='onAnswerChosen_bla(" + question.answers[2].id + ")' title='p.text'>" + question.answers[2].text + "</button>",
        "<button type='button' onclick='onAnswerChosen_bla(" + question.answers[3].id + ")' title='p.text'>" + question.answers[3].text + "</button>"

    ].join('\n'));
}


