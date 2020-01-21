<template>
    <div class="col" id="showResult">
        <h1 class="text-center"> </h1>

        <div>Remaining Time: <span id="countdown"> </span></div>

        <div class="list-group">
            <button id='answer0' v-on:click='onAnswerChosen(1)'></button>
            <button id='answer1' v-on:click='onAnswerChosen(2)'></button>
            <button id='answer2' v-on:click='onAnswerChosen(3)'></button>
            <button id='answer3' v-on:click='onAnswerChosen(4)'></button>
        </div>
    </div>
</template>

<script>
    import {onAnswerChosen} from "./LearnDuel";


    export default {
        name: "question",
        props: "question-data",
        methods: {
            onAnswerChosen(id) {
                onAnswerChosen(id)
            }
        },
        // mounted() {
        //     connectWebSocket();
        //     console.log("After connectWebSocket() in question.vue");
        //     startGame();
        // }
    }





</script>

<style scoped>

</style>