import {
  CSSResult,
  html,
  HTMLTemplateResult,
  LitElement, unsafeCSS
} from "lit";

import {
  customElement,
} from 'lit/decorators/custom-element.js'

import {
  property,
} from 'lit/decorators/property.js'

import {
  query,
} from 'lit/decorators/query.js'

import * as prism from "prismjs";
import {css, registerStyles, ThemableMixinClass} from "@vaadin/vaadin-themable-mixin";

@customElement('aire-code-panel')
export class CodePanel extends LitElement implements ThemableMixinClass {


  @query('code.code-container')
  private code: HTMLElement | undefined;

  @query('style')
  private styleElement : HTMLStyleElement | undefined;
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

  // private readonly styleValues : Array<CSSResult> = [];

  private dynamicStyles: string[] = [];

  static styles = css`
  
  `;

  constructor() {
    super();
    console.log(this);
  }

  render(): HTMLTemplateResult {
    return html`
      <style>
        ${this.dynamicStyles.join('\n')}
      </style>
      <pre>
        <code class="code-container language-${this.language}">
        </code>
      </pre>
    `;
  }

  connectedCallback() {
    super.connectedCallback();

    if (this.code) {
      this.code.innerHTML = this.contents!;
    }
  }



  public importStyles(...styles: string[])  {
    this.dynamicStyles.push(...styles);
    this.requestUpdate();
  }


  public addLanguageDefinition(name: string, definition: string): void {
    const f = Function(definition);
    f();
    this.language = name;
    const highlightedDom = prism.highlight(
        this.contents!,
        prism.languages.java,
        this.language!
    );
    this.contents = highlightedDom;
  }

}



