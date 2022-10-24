import {
  CodePanel
} from "@aire-ux/aire-code-panel/aire-code-panel";

export default function setup() {


  window.customElements.define(
      'aire-code-panel',
      CodePanel
  );
  Object.defineProperty(window.location, 'href', {
    writable: true,
    value: 'https://localhost'
  });
  // (window as any).chai.use(chaiDomDiff);
}
setup();