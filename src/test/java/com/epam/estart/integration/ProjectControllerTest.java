package com.epam.estart.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.epam.estart.TestData;
import com.epam.estart.dto.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
public class ProjectControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private TestData testData;
  @Autowired
  private ObjectMapper objectMapper;

  private Project getProject(UUID id) throws Exception {
    MvcResult result = mockMvc.perform(get("/projects/{id}", id))
        .andReturn();
    return objectMapper.readValue(result.getResponse().getContentAsString(), Project.class);
  }

  private Project createProject(String projectJson) throws Exception {
    MvcResult result = mockMvc.perform(post("/projects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(projectJson))
        .andReturn();
    return objectMapper.readValue(result.getResponse().getContentAsString(), Project.class);
  }

  private Project updateProject(Project project) throws Exception {
    MvcResult result = mockMvc.perform(put("/projects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(project)))
        .andReturn();
    return objectMapper.readValue(result.getResponse().getContentAsString(), Project.class);
  }
}
