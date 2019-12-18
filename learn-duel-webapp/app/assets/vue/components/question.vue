<template>
    <div class="container">
        <div class="row">
            <div class="col align-self-center">
                <h1 class="text-center">Text to replace</h1>

                <div>Remaining Time: <span id="countdown">60</span></div>

                <div class="list-group"></div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "question",
        props: "question-data"
    }

    function displayQuestions(question) {
        $(".text-center").text(question.text);
        $(".list-group").empty().append([
            `<button type='button' onclick='onAnswerChosen(${question.answers[0].id})' title='p.text'>${question.answers[0].text}</button>`,
            `<button type='button' onclick='onAnswerChosen(${question.answers[1].id})' title='p.text'>${question.answers[1].text}</button>`,
            `<button type='button' onclick='onAnswerChosen(${question.answers[2].id})' title='p.text'>${question.answers[2].text}</button>`,
            `<button type='button' onclick='onAnswerChosen(${question.answers[3].id})' title='p.text'>${question.answers[3].text}</button>`

        ].join('\n'));
    };

    $(document).ready(function () {
        connectWebSocket();
    });


</script>

<style scoped>

</style>