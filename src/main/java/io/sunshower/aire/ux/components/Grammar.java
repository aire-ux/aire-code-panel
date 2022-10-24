package io.sunshower.aire.ux.components;

import io.sunshower.arcus.condensation.Alias;
import io.sunshower.arcus.condensation.Attribute;
import io.sunshower.arcus.condensation.RootElement;
import lombok.Getter;
import lombok.Setter;

@RootElement
public class Grammar {

  @Setter
  @Getter(onMethod = @__({@Alias, @Attribute}))
  private Rule comment;

  @Setter
  @Getter(onMethod = @__({@Alias, @Attribute}))
  private Rule string;

  @Setter
  @Getter(
      onMethod =
          @__({
            @Attribute,
            @Alias(read = "string-template", write = "string-template"),
          }))
  private Rule stringTemplate;

  @Setter
  @Getter(
      onMethod =
          @__({
            @Attribute,
            @Alias(read = "eol-comment", write = "eol-comment"),
          }))
  private Rule eolComment;
}
