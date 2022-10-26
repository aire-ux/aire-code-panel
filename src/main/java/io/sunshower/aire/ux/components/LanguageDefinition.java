package io.sunshower.aire.ux.components;


import io.sunshower.arcus.condensation.Attribute;
import io.sunshower.arcus.condensation.RootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@RootElement
public class LanguageDefinition {


  @Setter
  @Getter(onMethod = @__(@Attribute))
  private String type;

  @Setter
  @Getter(onMethod = @__(@Attribute))
  private String name;

  @Setter
  @Getter(onMethod = @__(@Attribute))
  private String contents;

  public LanguageDefinition() {

  }

  public LanguageDefinition(String name, String type, String definition) {
    setName(name);
    setType(type);
    setContents(definition);

  }
}
