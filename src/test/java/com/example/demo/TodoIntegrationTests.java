package com.example.demo;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodoIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createAndGetTodo() throws Exception {
        // Step 1: Create a new todo item
        String newTodoJson = """
                {
                    "task": "Write integration test",
                    "completed": false
                }
                """;

        // Perform POST request to create a todo
        MvcResult result = mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTodoJson))
                .andExpect(status().isCreated())  // Status check
                .andReturn();  // Return the MvcResult for further checks

        // Get the response content as String (the entire response body in JSON format)
        String jsonResponse = result.getResponse().getContentAsString();

        String expectedResponse = """
                {
                  "id":0,
                  "task":"Write integration test",
                  "completed":false
                }
                """;

        JSONAssert.assertEquals(expectedResponse, jsonResponse, true);

        // Step 2: Retrieve the created todo item by ID and check the response again
        int createdId = new com.fasterxml.jackson.databind.ObjectMapper()
                .readTree(jsonResponse)
                .get("id")
                .asInt();

// GET /todos/{id}
        MvcResult getResult = mockMvc.perform(get("/todos/{id}", createdId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String getJson = getResult.getResponse().getContentAsString();

// Erwartete JSON (mit der dynamisch gelesenen ID)
        String expectedGetJson = """
        {
          "id": %d,
          "task": "Write integration test",
          "completed": false
        }
        """.formatted(createdId);

// strict = true: Reihenfolge/Felder müssen exakt passen
        JSONAssert.assertEquals(expectedGetJson, getJson, true);
    }


}