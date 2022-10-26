package io.sunshower.aire.ux.components.routes;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.RouteScope;
import io.sunshower.aire.ux.components.CodePanel;
import io.sunshower.aire.ux.components.Language;
import lombok.val;

@RouteScope
@Route("aire-code-panel")
public class CodePanelRoute extends VerticalLayout {

  private final CodePanel panel;

  public CodePanelRoute() {
    setSizeFull();
    panel = new CodePanel();
    panel.setLanguage("java");
    panel.setContents(
        """


public static void main(String[] args) {
  System.out.println("hello");
}
        """);

    panel.setLineNumbersEnabled(true);
    panel.registerStyles("prismjs/themes/prism-dark.css");

    add(new Button("Toggle Numbers", (click) -> {
      panel.setLineNumbersEnabled(!panel.isLineNumbersEnabled());

    }));
    add(panel);
  }

  @Override
  protected void onAttach(AttachEvent attachEvent) {
    panel.setLanguage(Language.JAVA);
  }

}
