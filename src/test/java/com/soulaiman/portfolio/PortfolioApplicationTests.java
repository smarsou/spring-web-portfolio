package com.soulaiman.portfolio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class PortfolioApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void homePageShouldGetMyName() throws Exception{
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Soulaïman Marsou")));
	}

	@Test
	void loginPageShouldGet() throws Exception{
		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("action=\"/login\"")));
	}

	@Test
	void adminPageShouldBeRedirected() throws Exception{
		this.mockMvc.perform(get("/admin")).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(header().string("Location", is("http://localhost/login")));
	}

}
