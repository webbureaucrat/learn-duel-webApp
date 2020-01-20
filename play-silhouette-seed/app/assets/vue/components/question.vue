<template>
    <div class="col">
        <h1 class="text-center"> </h1>

        <div>Remaining Time: <span id="countdown"> </span></div>

        <div class="list-group">
            <button id='answer0' @click='onAnswerChosen(1)'> </button>
            <button id='answer1' @click='onAnswerChosen(2)'> </button>
            <button id='answer2' @click='onAnswerChosen(3)'> </button>
            <button id='answer3' @click='onAnswerChosen(4)'> </button>
        </div>
    </div>
</template>

<script>
    import {onAnswerChosen} from "../../../../public/javascripts/LearnDuel";

    export default {
        name: "question",
        props: "question-data",
        methods: {
            onAnswerChosen(id) {
                onAnswerChosen(id)
            }
        },
    }


</script>

<style scoped>

</style>



