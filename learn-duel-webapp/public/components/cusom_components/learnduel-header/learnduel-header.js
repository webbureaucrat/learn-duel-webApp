// Import the LitElement base class and html helper function

import {LitElement, html, css} from "https://unpkg.com/@polymer/lit-element@latest/lit-element.js?module";

// Extend the LitElement base class
class LearnDuelHeader extends LitElement {

  /**
   * Implement `render` to define a template for your element.
   *
   * You must provide an implementation of `render` for any element
   * that uses LitElement as a base class.
   */
  render(){
    /**
     * `render` must return a lit-html `TemplateResult`.
     *
     * To create a `TemplateResult`, tag a JavaScript template literal
     * with the `html` helper function:
     */
    return html`
       <!-- template content -->
       <heavy-navbar item-count="3">
         <a href="/start" slot="item-1">Play</a>
         <a href="/about" slot="item-2">About</a>
         <a href="#" slot="item-3">Login</a>
       </heavy-navbar>
     `;
  }
}
// Register the new element with the browser.
customElements.define('learnduel-header', LearnDuelHeader);