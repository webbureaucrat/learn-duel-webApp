function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
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

function openSources() {
    var win = window.open("https://github.com/webbureaucrat/learn-duel-webApp", '_blank');
    win.focus();
}

window.onload = function () {
    var oneMinute = 60,
        display = document.querySelector('#countdown');
    startTimer(oneMinute, display);

    document.getElementById("openSources").addEventListener("click",openSources)
};

