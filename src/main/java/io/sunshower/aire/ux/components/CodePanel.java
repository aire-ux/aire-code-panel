package io.sunshower.aire.ux.components;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import elemental.json.Json;
import io.sunshower.arcus.condensation.Condensation;
import io.sunshower.lang.tuple.Pair;
import java.util.Locale;
import java.util.stream.IntStream;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

@Tag("aire-code-panel")
@JsModule(Paths.CodePanel_SOURCE)
//@CssImport(Paths.CodePanel_STYLES)
// @JsModule("@aire-ux/aire-code-panel/aire-code-panel")
// @CssImport("@aire-ux/aire-code-panel/styles/aire-code-panel.css")

/** uncomment this if you have deployed this component into NPMJS */
@JsModule("prismjs/prism.js")
//@StyleSheet("prismjs/themes/prism.css")
//@CssImport(value = "prismjs/themes/prism.css", themeFor = "aire-code-panel")
//@CssImport(value = Paths.CodePanel_STYLES, themeFor = "aire-code-panel")
@NpmPackage(value = "prismjs", version = "1.29.0")
// @NpmPackage(value = "@${organzation}/@aire-code-panel", version = Versions.CodePanel_VERSION)
public class CodePanel extends Component implements HasSize {

  static final Condensation condensation;

  static final PropertyDescriptor<String, String> LANGUAGE;

  static final PropertyDescriptor<String, String> CONTENTS;


  static final String PREFIX;

  private static final String REGISTER_PLUGIN_FUNCTION_NAME;
  private static final String REGISTER_LANGUAGE_FUNCTION_NAME;

  private static final String PLUGIN_PREFIX;

  static {
    condensation = Condensation.create("json");
    PREFIX = "prismjs/components/%s";
    PLUGIN_PREFIX = "prismjs/plugins/%s/%s";
    LANGUAGE = PropertyDescriptors.propertyWithDefault("language", "");
    CONTENTS = PropertyDescriptors.propertyWithDefault("contents", "");
    REGISTER_PLUGIN_FUNCTION_NAME = "registerPlugin";
    REGISTER_LANGUAGE_FUNCTION_NAME = "registerLanguage";
//    LINE_NUMBERS_ENABLED = PropertyDescriptors.propertyWithDefault("line-numbers", true);
  }


  private final LanguageLoader loader;


  public CodePanel(LanguageLoader loader) {
    this.loader = loader;
  }

  public void setLineNumbersEnabled(boolean enabled) {
    if (!isLineNumbersEnabled()) {
      registerPlugin("line-numbers");
    }
    getElement().setAttribute("line-numbers", enabled);
  }

  public boolean isLineNumbersEnabled() {
    val lineNumbers = getElement().getAttribute("line-numbers");
    if (lineNumbers == null) {
      return false;
    }
    return Boolean.parseBoolean(lineNumbers);
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


  @SneakyThrows
  public void setLanguage(Language language) {
    val definition = loader.load(PREFIX.formatted(language.getFileName())).toString();
    getElement().callJsFunction(
        REGISTER_LANGUAGE_FUNCTION_NAME,
        language.name().toLowerCase(Locale.ROOT),
        definition
    );
  }

  public void registerPlugin(String name) {
    val stylesheet = PLUGIN_PREFIX.formatted(name, "prism-" + name + ".min.css");
    val js = loader.load(PLUGIN_PREFIX.formatted(name, "prism-" + name + ".min.js"));
    registerStyles(stylesheet);
    getElement().callJsFunction(
        REGISTER_PLUGIN_FUNCTION_NAME,
        js.toString()
    );
  }


  @NonNull
  protected LanguageLoader getLoader() {
    return loader;
  }


  public void registerStyles(String... names) {
    val results = IntStream.range(0, names.length)
        .mapToObj(i -> Pair.of(i, names[i]))
        .map(pair -> Pair.of(pair.fst, loader.load(pair.snd()).toString()))
        .reduce(Json.createArray(), (t, u) -> {
          t.set(u.fst, Json.create(u.snd));
          return t;
        }, (u, v) -> u);
    getElement().callJsFunction("importStyles", results);
  }

}
