<template>
    <div class="container">
        <!--    <h1>{{ greeting }}</h1>-->
        <!--    <p>Value: {{ toggleText }}</p>-->
        <!--    <our-button ref="button" @click="toggle">Toggle button</our-button>-->
        <!--    <our-js-button ref="jsButton" @click="toggle">Toggle JS button</our-js-button>-->
        <div class="row">
            <div class="col align-self-center">
                <h3 class="display-3">Learn Duel</h3>


                <p class="lead">The game Learn Duel is based on QuizDuel and works in a similar fashion, but with a new
                    twist:
                    <br/>
                    You play with questions based on your school or study assignments.
                    <br>
                    <button class="btn btn-outline-secondary" id="play-game" v-on:click="playGame">Play the game!</button>
                </p>

                <figure class="figure">
                    <figcaption class="figure-caption text-center">View of Learn Duel gameplay.</figcaption>
                </figure>
                <!--<img src="@routes.Assets.versioned("images/game.PNG")" class="img-fluid img-thumbnail rounded mx-auto d-block"/>-->

                <br>
                <h4> How to play:</h4>

                <p><small>
                    When the game starts, it shows you the questions you want to learn.
                    By clicking with your mouse on one of the four answers, you will get the next question.
                    You will have 60 seconds to answer each question.
                </small></p>
                <br>
                <h4>Possible features in future:</h4>

                <ul class="list-unstyled">
                    <li>defining own questions</li>
                    <li>increase max player count</li>
                </ul>
                <br>

                <h4>Supported Browsers:</h4>
                <table class="table">
                    <tbody>
                    <tr>
                        <td>Edge</td>
                        <td>Chrome</td>
                        <td>Safari</td>
                        <td>Firefox</td>
                        <td>Opera</td>
                    </tr>
                    <tr>
                        <td> &#10004;</td>
                        <td> &#10004;</td>
                        <td> &#10004;</td>
                        <td> &#10004;</td>
                        <td> &#10004;</td>
                    </tr>
                    </tbody>
                </table>
                <br>
                <h5>License:</h5>
                <p><small>This work is licensed under the terms of the MIT license. For a copy, see <a
                        href="https://opensource.org/licenses/MIT">https://opensource.org/licenses/MIT</a> .
                    <br/><br/>
                    If you would like to contribute, you can access our sources under following GitHub repository.
                </small></p>
                <button class="btn btn-outline-secondary" id="openSources" @click="openSources">Show Sources</button>
                <!--<p><small>
                    <a href="https://github.com/webbureaucrat/learn-duel-webApp" target="_blank" rel="noopener">Learn-Duel</a>
                </small></p>-->
            </div>
        </div>
    </div>
</template>

<script>
    import 'bootstrap';

    export default {
        name: "about",
        methods: {
            openSources: function () {
                window.open("https://github.com/webbureaucrat/learn-duel-webApp", '_blank');
            },
            playGame: function() {
                this.$emit('clicked', 'question')
            }
        }
    }
</script>

<!--<style scoped lang="scss">-->
<!--  .greeting-form {-->
<!--    display: block;-->
<!--    text-align: center;-->
<!--    padding: 25px 0px;-->
<!--  }-->
<!--</style>-->
