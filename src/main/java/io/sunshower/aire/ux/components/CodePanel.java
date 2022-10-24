package io.sunshower.aire.ux.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.HtmlContainer;

@Tag("aire-code-panel")
@JsModule(Paths.CodePanel_SOURCE)
@CssImport(Paths.CodePanel_STYLES)
//@JsModule("@aire-ux/aire-code-panel/aire-code-panel")
//@CssImport("@aire-ux/aire-code-panel/styles/aire-code-panel.css")

/**
 * uncomment this if you have deployed this component into NPMJS
 */
@NpmPackage(value = "prismjs", version = "1.29.0")
//@NpmPackage(value = "@${organzation}/@aire-code-panel", version = Versions.CodePanel_VERSION)
public class CodePanel extends HtmlContainer {

  public CodePanel() {
    add(new Button("Hello from CodePanel!"));
  }

}