<template>
    <div>
        <nav-bar></nav-bar>
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
    }

</script>

<style scoped>

</style>