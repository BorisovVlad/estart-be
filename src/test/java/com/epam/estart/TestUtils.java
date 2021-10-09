package com.epam.estart;

public final class TestUtils {
  public static String cutIdFromJson(String json) {
    return "{" + json.substring(45);
  }
}
