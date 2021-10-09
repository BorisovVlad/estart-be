package com.epam.estart.integration;

import static com.epam.estart.TestUtils.cutIdFromJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.epam.estart.TestData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
@Disabled
@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private TestData testData;

  @Test
  void create() throws Exception {
    MvcResult result = mockMvc.perform(post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(testData.getUserCreateRequest()))
        .andReturn();
    assertThat(cutIdFromJson(result.getResponse().getContentAsString()))
        .isEqualTo(cutIdFromJson(testData.getUserCreateResponse()));
  }
}
