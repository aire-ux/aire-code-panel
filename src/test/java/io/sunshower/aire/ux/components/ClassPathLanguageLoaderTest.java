package io.sunshower.aire.ux.components;

import static org.junit.jupiter.api.Assertions.*;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClassPathLanguageLoaderTest {

  private LanguageLoader loader;

  @BeforeEach void setUp() {
    loader = new ClassPathLanguageLoader();
  }


  @Test
  void ensureReadingMinifiedFileWorks() {
    val result = loader.load("prismjs/components/prism-moonscript.min.js").toString();
    assertTrue(result.indexOf("interpolation") > 0);
  }

}