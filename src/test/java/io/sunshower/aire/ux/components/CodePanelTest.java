package io.sunshower.aire.ux.components;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.aire.ux.test.AireTest;
import com.aire.ux.test.Navigate;
import com.aire.ux.test.RouteLocation;
import com.aire.ux.test.Select;
import com.aire.ux.test.ViewTest;
import com.aire.ux.test.spring.EnableSpring;
import io.sunshower.aire.ux.components.routes.CodePanelRoute;
import org.springframework.boot.test.context.SpringBootTest;

@AireTest
@EnableSpring
@RouteLocation(scanPackage = "io.sunshower.aire.ux.components.routes")
@SpringBootTest(classes = CodePanelDemoApplication.class)
class CodePanelTest {

  @ViewTest
  @Navigate("aire-code-panel")
  void ensureCodePanelHostIsInjectable(@Select CodePanelRoute ex) {
    assertNotNull(ex);
  }
}
