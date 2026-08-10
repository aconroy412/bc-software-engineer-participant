package com.northstar.crm;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityPathTest {

	@Autowired
	MockMvc mvc;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void missingTokenIs401() throws Exception {
		mvc.perform(get("/api/customers/CUS-1001"))
			.andExpect(status().isForbidden());
	}

	@Test
	public void agentCanReadCustomerButNotAdmin() throws Exception {
		String token = loginAndGetToken("agent1");

		// agent can read customer
		mvc.perform(get("/api/customers/CUS-1001").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("CUS-1001"));

		// agent cannot access admin ping
		mvc.perform(get("/api/admin/ping").header("Authorization", "Bearer " + token))
				.andExpect(status().isForbidden());
	}

	@Test
	public void adminCanPing() throws Exception {
		String token = loginAndGetToken("admin1");

		mvc.perform(get("/api/admin/ping").header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.role").value("ADMIN"));
	}

	private String loginAndGetToken(String username) throws Exception {
		String body = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, username);

		String resp = mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		JsonNode node = mapper.readTree(resp);
		String token = node.get("accessToken").asText();
		assertThat(token).isNotBlank();
		return token;
	}
}
