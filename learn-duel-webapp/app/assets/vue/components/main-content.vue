<template>
    <div>
        <nav-bar @clicked="onClickChild"></nav-bar>
        <!--<nav-bar v-bind:show.sync="show"></nav-bar>-->
        <question v-if="show === 'question'"></question>
        <result v-else-if="show === 'result'"></result>
        <about v-else></about>
    </div>
</template>

<script>
    import NavBar from "./nav-bar";
    import About from "./about";
    import Question from "./question";
    import Result from "./result";


    export default {
        name: "main-content",
        components: {Result, Question, About, NavBar},
        props: ['show'],
        methods:{
            onClickChild (value) {
                switch(value){
                    case 'question':
                        this.show = 'question';
                        break;
                    case 'about':
                        this.show = 'about';
                        break;
                    case 'login':
                        this.show = 'login';
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