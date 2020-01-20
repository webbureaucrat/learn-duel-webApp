<template>
    <div class="col">
        <h1 class="text-center"> </h1>

        <div>Remaining Time: <span id="countdown"> </span></div>

        <div class="list-group">
            <button id='answer0' v-on:click='onAnswerChosen(1)' title='p.text'></button>
            <button id='answer1' v-on:click='onAnswerChosen(2)' title='p.text'></button>
            <button id='answer2' v-on:click='onAnswerChosen(3)' title='p.text'></button>
            <button id='answer3' v-on:click='onAnswerChosen(4)' title='p.text'></button>
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