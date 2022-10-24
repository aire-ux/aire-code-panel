package io.sunshower.aire.ux.components.routes;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.RouteScope;
import io.sunshower.aire.ux.components.CodePanel;
import lombok.val;

@RouteScope
@Route("aire-code-panel")
public class CodePanelRoute extends VerticalLayout {

  public CodePanelRoute() {
    setSizeFull();
    val panel = new CodePanel();
    panel.setLanguage("java");
    panel.setContents(
        """
        public static void main(String[] args) {
          System.out.println("waddup");
        }
        """);
    add(panel);
  }
}
