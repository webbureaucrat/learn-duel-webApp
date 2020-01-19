function buildScore(players) {

    let correct = '';
    let wrong = '';
    players[0].correctAnswers.forEach(item => correct += '<li>'+ item.text + '</li>');
    players[0].wrongAnswers.forEach(item => wrong += '<li>'+ item.text + '</li>\n<li>Korrekte Antwort ist : '
        + item.answers.find(a => a.id === item.correctAnswer).text + '</li>');
    return "<div class=\"row\">\n" +
        "        <div class=\"col align-self-center\">\n" +
        "            <h2>Score!</h2>\n" +
        "            <h5>" +
        players[0].name +
        "</h5>\n" +
        "\n" +
        "\n" +
        "            <p><img src=\"/assets/images/circle-check-3x.png\" class=\"figure-img img-fluid rounded .img-thumbnail\" alt=\"...\"> Correct Answers: </p>\n" +
        "            <ol class=\"list-unstyled\">\n" +
        correct +
        "\n" +
        "            </ol>\n" +
        "            <br>\n" +
        "\n" +
        "\n" +
        "            <p> <img src=\"/assets/images/circle-x-3x.png\" class=\"figure-img img-fluid rounded .img-thumbnail\" alt=\"...\"> Wrong Answers: </p>\n" +
        "            <ol class=\"list-unstyled\">\n" +
        wrong +
        "\n" +
        "            </ol>\n" +
        "\n" +
        "            <p><strong>\n" +
        "                Total Score: " +
        players[0].points +
        "\n" +
        "            </strong></p>\n" +
        "        </div>\n" +
        "    </div>";
}

function setScoreBackground(flag) {
    if (flag === 0) {
        $('html, body').css("background-color", "#2BFF60");
    } else {
        $('html, body').css("background-color", "#FF584D");
        $('html, body').css("color", "white");
    }
}

export {buildScore, setScoreBackground};