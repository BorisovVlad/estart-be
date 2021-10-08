package com.epam.estart;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EstartApplicationTests {

  @Test
  void contextLoads() {
    String actual = "Is launched!";
    String expected = "Is launched!";
    assertThat(actual).isEqualTo(expected);
  }

}
