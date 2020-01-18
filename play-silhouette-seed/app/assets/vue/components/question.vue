<template>
    <div class="container">
        <div class="row">
            <div class="col align-self-center">
                <h1 class="text-center">Text to replace</h1>

                <div>Remaining Time: <span id="countdown">60</span></div>

                <div class="list-group">
                    <button id='answer0' v-on:click='onAnswerChosen(0)' title='p.text'>${question.answers[0].text}</button>
                    <button id='answer1' v-on:click='onAnswerChosen(1)' title='p.text'>${question.answers[1].text}</button>
                    <button id='answer2' v-on:click='onAnswerChosen(2)' title='p.text'>${question.answers[2].text}</button>
                    <button id='answer3' v-on:click='onAnswerChosen(3)' title='p.text'>${question.answers[3].text}</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import {onAnswerChosen} from "../../../../../learn-duel-webapp/public/javascripts/LearnDuel";
    import {startGame} from "../../../../public/javascripts/LearnDuel";
    import {connectWebSocket} from "../../../../public/javascripts/websocket";


    export default {
        name: "question",
        props: "question-data",
        methods: {
            onAnswerChosen: onAnswerChosen,
        }
    }

    startGame();
    connectWebSocket();


</script>

<style scoped>

</style>