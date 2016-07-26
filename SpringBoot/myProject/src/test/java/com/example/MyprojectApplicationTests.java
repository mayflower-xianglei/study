package com.example;

import com.example.Web.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
public class MyprojectApplicationTests {

	private MockMvc mvc;

	@Before
	public void setUp() throws  Exception{
		mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	@Test
	public void contextLoads() throws Exception{
		RequestBuilder request = null;

		request = get("/users/test");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("test is ok")));

		request = get("/users/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));

		request = post("/users/")
				.param("name", "xianglei")
				.param("age", "20")
				.param("sex", "male");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		request = get("/users/xianglei");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"name\":\"xianglei\",\"age\":20,\"sex\":\"male\"}")));

		request = get("/users/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"name\":\"xianglei\",\"age\":20,\"sex\":\"male\"}]")));

		request = put("/users/xianglei")
				.param("name", "xianglei")
				.param("age", "50")
				.param("sex", "male");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		request = get("/users/xianglei");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"name\":\"xianglei\",\"age\":50,\"sex\":\"male\"}")));

		request = post("/users/")
				.param("name", "keya")
				.param("age", "20")
				.param("sex", "female");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("success")));

		request = get("/users/");
		mvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"name\":\"keya\",\"age\":20,\"sex\":\"female\"},{\"name\":\"xianglei\",\"age\":50,\"sex\":\"male\"}]")));

	}

}
