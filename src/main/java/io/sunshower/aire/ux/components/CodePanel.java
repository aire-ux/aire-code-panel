package io.sunshower.aire.ux.components;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import lombok.NonNull;

@Tag("aire-code-panel")
@JsModule(Paths.CodePanel_SOURCE)
@CssImport(Paths.CodePanel_STYLES)
// @JsModule("@aire-ux/aire-code-panel/aire-code-panel")
// @CssImport("@aire-ux/aire-code-panel/styles/aire-code-panel.css")

/** uncomment this if you have deployed this component into NPMJS */
@JsModule("prismjs/prism.js")
@CssImport("prismjs/themes/prism.css")
@NpmPackage(value = "prismjs", version = "1.29.0")
// @NpmPackage(value = "@${organzation}/@aire-code-panel", version = Versions.CodePanel_VERSION)
public class CodePanel extends HtmlContainer {

  static final PropertyDescriptor<String, String> LANGUAGE =
      PropertyDescriptors.propertyWithDefault("language", "");

  static final PropertyDescriptor<String, String> CONTENTS =
      PropertyDescriptors.propertyWithDefault("contents", "");


  private final LanguageLoader loader;


  public CodePanel(LanguageLoader loader) {
    this.loader = loader;
  }

  public CodePanel() {
    this(new ClassPathLanguageLoader());
  }

  public void setLanguage(String language) {
    LANGUAGE.set(getElement(), language);
  }

  public String getLanguage() {
    return LANGUAGE.get(getElement());
  }

  public void setContents(String contents) {
    CONTENTS.set(getElement(), contents);
  }

  public String getContents() {
    return CONTENTS.get(getElement());
  }

  public void addLanguage(Grammar grammar) {
  }


  public void setLanguage(Language language) {

  }


  @NonNull
  protected LanguageLoader getLoader() {
    return loader;
  }
}
