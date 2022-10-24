import "./harness";

import {
  fixture,
  expect, nextFrame,
} from "@open-wc/testing";

import {
  beforeEach,
  describe,
  it,
  vi
} from "vitest";
import {
  CodePanel
} from "@aire-ux/aire-code-panel/aire-code-panel"


import {html} from "lit";


describe('a aire-code-panel', async () => {
  let element: CodePanel;
  beforeEach(async () => {
    element = await fixture(html`
      <aire-code-panel></aire-code-panel>
    `);

    await nextFrame();
  });

  it('should mount the component', () => {
    let elements = document.querySelectorAll('aire-code-panel');
    expect(elements.length).to.equal(1);
  });

});