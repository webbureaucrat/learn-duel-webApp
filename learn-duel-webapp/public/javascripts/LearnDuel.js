function startTimer(duration, display) {
    let timer = duration, minutes, seconds;
    setInterval(function () {
        //minutes = parseInt(timer / 60, 10)
        seconds = parseInt(timer % 60, 10);

        //minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        //display.textContent = minutes + ":" + seconds;
        display.textContent = seconds;

        if (--timer < 0) {
            timer = duration;
        }
    }, 1000);
}

window.onload = function () {
    let oneMinute = 60,
        display = $('#countdown');
    startTimer(oneMinute, display);

    $("#openSources").click(function () {
        window.open("https://github.com/webbureaucrat/learn-duel-webApp", '_blank');
    })
};

function onAnswerChosen(pid) {
    let xhttp = new XMLHttpRequest();
    console.log("onAnswerChosen");
    xhttp.onreadystatechange = function () {
        //ToDo don't know if status code is correct
        if (this.readyState === 4 && this.status === 200) {
            console.log("onreadystatechange");
            $('.row').html(this.responseText);
        }
    };
    xhttp.open("GET", "/onAnswerChosen?position=" + pid, true);
    xhttp.send();
}


