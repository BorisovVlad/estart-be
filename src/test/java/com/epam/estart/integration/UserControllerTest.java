package com.epam.estart.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.epam.estart.TestData;
import com.epam.estart.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.assertj.core.util.Sets;
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
  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void getById() throws Exception {
    User value = createUser(testData.getUserCreateRequest());
    User expected = objectMapper.readValue(testData.getUserCreateResponse(), User.class).setId(value.getId());
    User actual = getUser(value.getId());
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void create() throws Exception {
    User expected = objectMapper.readValue(testData.getUserCreateResponse(), User.class).setId(null);
    User actual = createUser(testData.getUserCreateRequest()).setId(null);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void update() throws Exception {
    User expected = createUser(testData.getUserCreateRequest())
        .setEmail("newEmail")
        .setAboutMe("newAboutMe")
        .setHardSkills("newHardSkills")
        .setLastName("newLastName")
        .setFirstName("newFirstName")
        .setMainRole("newMainRole")
        .setRoles(Sets.newHashSet())
        .setTags(Sets.newHashSet());
    User actual = updateUser(expected);
    assertThat(actual).isEqualTo(expected);
  }

  private User getUser(UUID id) throws Exception {
    MvcResult result = mockMvc.perform(get("/user/{id}", id))
        .andReturn();
    return objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
  }

  private User createUser(String userJson) throws Exception {
    MvcResult result = mockMvc.perform(post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(userJson))
        .andReturn();
    return objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
  }

  private User updateUser(User user) throws Exception {
    MvcResult result = mockMvc.perform(put("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
        .andReturn();
    return objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
  }
}
