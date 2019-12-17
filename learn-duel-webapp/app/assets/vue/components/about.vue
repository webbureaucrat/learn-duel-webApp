<template>
  <div class="greeting-form">
<!--    <h1>{{ greeting }}</h1>-->
<!--    <p>Value: {{ toggleText }}</p>-->
<!--    <our-button ref="button" @click="toggle">Toggle button</our-button>-->
<!--    <our-js-button ref="jsButton" @click="toggle">Toggle JS button</our-js-button>-->
    <div class="row">
      <div class="col align-self-center">
        <h3 class="display-3">Learn Duel</h3>


        <p class="lead">The game Learn Duel is based on QuizDuel and works in a similar fashion, but with a new twist:
          <br />
          You play with questions based on your school or study assignments.
          <br>
        </p>

        <figure class="figure">
          <figcaption class="figure-caption text-center">View of Learn Duel gameplay.</figcaption>
        </figure>
        <!--<img src="@routes.Assets.versioned("images/game.PNG")" class="img-fluid img-thumbnail rounded mx-auto d-block"/>-->

        <br>
        <h4> How to play:</h4>

        <p><small>
          First you have to type in your player name(s).
          <br />
          If you are playing alone, the answers can be selected with the mouse or the keys 1-4.
          <br />In multiplayer mode player 1 can specify his answer with the keys 1-4 and\nplayer 2 with the keys 6-9.
          <br /><br />
          By clicking "Play Learn Duel", you enter the game mode.
          <br />
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
            <td>	&#10004;</td>
            <td>	&#10004;</td>
            <td>	&#10004;</td>
            <td>	&#10004;</td>
            <td>	&#10004;</td>
          </tr>
          </tbody>
        </table>
        <br>
        <h5>License:</h5>
        <p><small>This work is licensed under the terms of the MIT license. For a copy, see <a href="https://opensource.org/licenses/MIT">https://opensource.org/licenses/MIT</a> .
          <br /><br />
          If you would like to contribute, you can access our sources under following GitHub repository.
        </small></p>
        <button class="btn btn-outline-secondary" id="openSources">Show Sources</button>
        <!--<p><small>
            <a href="https://github.com/webbureaucrat/learn-duel-webApp" target="_blank" rel="noopener">Learn-Duel</a>
        </small></p>-->
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "about"
    }
</script>
<!--<script lang="ts">-->
<!--  import Vue from 'vue';-->

<!--  import { Type as OurButton } from './common/_our-button.vue';-->

<!--  Vue.component('our-button', require('./common/_our-button.vue').default);-->
<!--  Vue.component('our-js-button', require('./common/_our-js-button.vue').default);-->

<!--  // Since a JS component doesn't have type, we need to provide the type in order for Typescript to compile.-->
<!--  interface OurJsButton extends Vue {-->
<!--    testMessage(): void-->
<!--  }-->

<!--  export default Vue.extend({-->
<!--    props: {-->
<!--      greeting: {-->
<!--        type: String,-->
<!--        required: true-->
<!--      }-->
<!--    },-->
<!--    data: function() {-->
<!--      return {-->
<!--        toggleValue: false-->
<!--      };-->
<!--    },-->
<!--    computed: {-->
<!--      toggleText(): string {-->
<!--        if (this.toggleValue) {-->
<!--          return "on";-->
<!--        } else {-->
<!--          return "off";-->
<!--        }-->
<!--      }-->
<!--    },-->
<!--    methods: {-->
<!--      toggle(): void {-->
<!--        this.toggleValue = !this.toggleValue;-->
<!--        (<OurButton>(this.$refs.button)).testMessage();-->
<!--        (<OurJsButton>(this.$refs.jsButton)).testMessage();-->
<!--      }-->
<!--    }-->
<!--  });-->
<!--</script>-->

<!--<style scoped lang="scss">-->
<!--  .greeting-form {-->
<!--    display: block;-->
<!--    text-align: center;-->
<!--    padding: 25px 0px;-->
<!--  }-->
<!--</style>-->
