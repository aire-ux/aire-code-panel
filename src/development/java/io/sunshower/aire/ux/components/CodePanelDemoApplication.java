package io.sunshower.aire.ux.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import io.sunshower.aire.ux.components.routes.CodePanelRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@PWA(name = "CodePanel Demo", shortName = "Demo")
@ComponentScan(basePackageClasses = CodePanelRoute.class)
@EnableVaadin({"io.sunshower.aire.ux.components.routes"})
public class CodePanelDemoApplication extends VerticalLayout implements AppShellConfigurator {

  public static void main(String[] args) {
    SpringApplication.run(CodePanelDemoApplication.class, args);
  }
}
