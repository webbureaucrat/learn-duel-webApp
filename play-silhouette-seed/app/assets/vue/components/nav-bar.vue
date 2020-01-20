<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
        <div class="container">
            <a class="navbar-brand" href="/">
                Learn Duel
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item" >
                        <a class="nav-link" v-on:click="play">Play
                            <!--<span class="sr-only">(current)</span>-->
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" v-on:click="about">About</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" v-on:click="login">Login</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script>
    export default {
        name: "nav-bar", 
        methods: {
            play: function () {
                this.$emit('clicked', 'question')
            },
            about: function () {
                this.$emit('clicked', 'about')
            },
            login: function () {
                this.$emit('clicked', 'login')
            }
        }
    }
</script>

<style scoped>

</style>