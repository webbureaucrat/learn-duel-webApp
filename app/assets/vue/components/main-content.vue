<template>
    <div>
        <!--<nav-bar v-bind:show.sync="show"></nav-bar>-->
        <question v-if="show === 'question'"></question>
        <about v-else @clicked="onClickChild"></about>
    </div>
</template>


<script>
    import Question from "./question";
    import Result from "./result";
    import About from "./about";
    import {startGame} from "./LearnDuel;"
    import {connectWebSocket} from "./websocket";

    export default {
        name: "main-content",
        components: {Result, Question, About},
        props: ['show'],
        methods:{
            onClickChild (value) {
                switch(value){
                    case 'question':
                        connectWebSocket();
                        startGame();
                        this.show = 'question';
                        break;
                    case 'about':
                        this.show = 'about';
                        break;
                    default:
                        this.show = 'about';
                }
                this.$forceUpdate();
            }
        }
    }

</script>

<style scoped>

</style>