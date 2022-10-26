package io.sunshower.aire.ux.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.val;

public class ClassPathLanguageLoader implements
    LanguageLoader {

  private WeakReference<ClassLoader> classLoader;

  public ClassPathLanguageLoader(@NonNull ClassLoader loader) {
    this.classLoader = new WeakReference<>(loader);
  }

  public ClassPathLanguageLoader() {
    this(Thread.currentThread().getContextClassLoader());
  }

  @Override
  public CharSequence load(String path) {
    val cl = classLoader();
    try (
        val inputStream =
            cl.getResourceAsStream(path);
        val reader = new BufferedReader(new InputStreamReader(inputStream))
    ) {
      return reader.lines().collect(Collectors.joining());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  protected ClassLoader classLoader() {
    val cl = classLoader.get();
    if (cl == null) {
      throw new IllegalStateException(
          "Error: classloader is null (garbage-collected, so this is probably an error)");
    }
    return cl;
  }
}
