import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';

/**
 * `landing-page`
 *
 * LandingPage element.
 *
 * @customElement
 * @polymer
 */
class LandingPage extends PolymerElement {

    static get template() {
        return html`
            <style include="shared-styles">
                :host {
                    display: block;
                    height: 100%;
                }
            </style>
        `;
    }

    static get is() {
        return 'landing-page';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(LandingPage.is, LandingPage);
