import {
  css,
  html,
  HTMLTemplateResult,
  LitElement
} from "lit";

import {
  customElement,
} from 'lit/decorators/custom-element.js'

import {
  property,
} from 'lit/decorators/property.js'

import * as prism from "prismjs";
import {Grammar} from "prismjs";

@customElement('aire-code-panel')
export class CodePanel extends LitElement {


  /**
   * the actual contents of the code-panel
   */
  @property({type: String, attribute: false})
  contents: string | undefined;


  /**
   * the current language
   */
  @property({type: String, attribute: true})
  language: string | undefined;

  // language=CSS
  static
  styles = css`

  `;

  render(): HTMLTemplateResult {
    return html`
      <pre>
        <code class="${this.language}">${this.contents}</code>
      </pre>
    `;
  }

  connectedCallback() {
    // prism.highlight(this.contents!, prism.languages[this.language], this.language!);
  }




  public addLanguage(name: string, grammar: Grammar) : void {
    prism.languages[name] = grammar;
  }


  public removeLanguage(name: string)  : void {
    delete prism.languages[name];
  }


}
