import {html, PolymerElement} from '@polymer/polymer/polymer-element.js';

/**
 * `landing-page-alt`
 *
 * LandingPageAlt element.
 *
 * @customElement
 * @polymer
 */
class LandingPageAlt extends PolymerElement {

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
        return 'landing-page-alt';
    }

    static get properties() {
        return {
            // Declare your properties here.
        };
    }
}

customElements.define(LandingPageAlt.is, LandingPageAlt);
