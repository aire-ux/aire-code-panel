package io.sunshower.aire.ux.components;

import io.sunshower.arcus.condensation.Alias;
import io.sunshower.arcus.condensation.RootElement;
import lombok.Getter;
import lombok.Setter;

@RootElement
public class Rule {

  /** the alias for this rule */
  @Setter
  @Getter(onMethod = @__({@Alias(read = "alias", write = "alias")}))
  private String alias;

  /** the pattern for this rule */
  @Setter
  @Getter(onMethod = @__({@Alias(read = "pattern", write = "pattern")}))
  private String pattern;

  @Setter
  @Getter(onMethod = @__({@Alias(read = "lookbehind", write = "lookbehind")}))
  private boolean lookbehind;
}
