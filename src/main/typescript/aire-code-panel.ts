import {
  CSSResult,
  html,
  HTMLTemplateResult,
  LitElement, PropertyValues, unsafeCSS
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

import * as Prism from "prismjs";
import {css, ThemableMixinClass} from "@vaadin/vaadin-themable-mixin";
(window as any).Prism = Prism;

@customElement('aire-code-panel')
export class CodePanel extends LitElement implements ThemableMixinClass {


  @query('code.code-container')
  private code: HTMLElement | undefined;


  @query('section.container')
  private container: HTMLDivElement | undefined;

  @query('style')
  private styleElement : HTMLStyleElement | undefined;
  /**
   * the actual contents of the code-panel
   */
  @property({type: String, attribute: false})
  contents: string | undefined;

  @property({
    type: Boolean,
    attribute: 'line-numbers',
  })
  lineNumbers: Boolean | undefined;

  /**
   * the current language
   */
  @property({type: String, attribute: true})
  language: string | undefined;

  // private readonly styleValues : Array<CSSResult> = [];

  private dynamicStyles: string[] = [];

  static styles = css`
    pre.line-numbers code {
      margin-left: -6em !important;
      height: 100%;
    
    }
  `;

  constructor() {
    super();
  }

  render(): HTMLTemplateResult {
    return html`
      <style>
        ${this.dynamicStyles.join('\n')}
      </style>
      <section class="container ${this.lineNumbers ? 'line-numbers' : 'no-line-numbers'} ">
        
        <pre class="${this.lineNumbers ? 'line-numbers' : 'no-line-numbers'} language-${this.language}">
          <code class="code-container language-${this.language}">
          </code>
        </pre>
      </section>
    `;
  }

  connectedCallback() {
    super.connectedCallback();

    if (this.code) {
      this.code.innerHTML = this.contents!;
    }
  }



  public importStyles(...styles: string[])  {
    console.log(styles);
    this.dynamicStyles.push(...styles);
    this.requestUpdate();
  }


  attributeChangedCallback(name: string, _old: string | null, value: string | null) {
    super.attributeChangedCallback(name, _old, value);
    this.requestUpdate();
  }



  public registerPlugin(definition: string): void {
    const f = Function(definition);
    f();
    if(this.code) {
      Prism.highlightElement(this.code);
    }
    this.requestUpdate();
  }

  public registerLanguage(name: string, definition: string): void {
    const f = Function(definition);
    f(Prism);
    this.language = name;
    const highlightedDom = Prism.highlight(
        this.contents!,
        Prism.languages[name],
        this.language!
    );
    this.contents = highlightedDom;
    this.requestUpdate();
  }

}



