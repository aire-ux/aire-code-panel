import {
  css,
  html,
  HTMLTemplateResult,
  LitElement
} from "lit";

import {
  customElement
} from 'lit/decorators/custom-element.js'

@customElement('aire-code-panel')
export class CodePanel extends LitElement
{


  // language=CSS
static
  styles = css`

    article {
      display: flex;
      flex-direction: column;
      width: 100%;
      height: 100%;
    }
  `;

  render()
:
  HTMLTemplateResult
  {
    return html`
      <article>
        <slot></slot>
      </article>
    `;
  }

}
