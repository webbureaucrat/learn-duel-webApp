<template>
    <div>
        <!--<nav-bar v-bind:show.sync="show"></nav-bar>-->
        <question ></question>

    </div>
</template>

<script>
    import About from "./about";
    import Question from "./question";
    import Result from "./result";
    import {startGame} from "../../../../public/javascripts/LearnDuel";
    import {connectWebSocket} from "../../../../public/javascripts/websocket";

    export default {
        name: "main-content",
        components: {Result, Question, About},
        props: ['show'],
        // methods:{
        //     startGame :startGame,
        //     connectWebSocket: connectWebSocket,
        //     onClickChild (value) {
        //         switch(value){
        //             case 'question':
        //                 this.show = 'question';
        //                 startGame();
        //                 connectWebSocket();
        //                 break;
        //             case 'about':
        //                 this.show = 'about';
        //                 break;
        //             case 'login':
        //                 this.show = 'login';
        //             default:
        //                 this.show = 'about';
        //         }
        //         this.$forceUpdate();
        //     }
        // }
    }

</script>

<style scoped>

</style>