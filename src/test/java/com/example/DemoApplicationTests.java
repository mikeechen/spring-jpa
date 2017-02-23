package com.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumController.class)
public class DemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	private Gson gson = new GsonBuilder().create();

	@MockBean
	AlbumRepository repo;

	@Test
	public void postTest() throws Exception {
		HashMap<String, String> json = new HashMap<>();

		json.put("name", "Waking Up");
		json.put("bandName", "One Republic");

		MockHttpServletRequestBuilder req = MockMvcRequestBuilders.post("/album")
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(json));

		this.mvc.perform(req)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Waking Up")))
				.andExpect(jsonPath("$.bandName", is("One Republic")));

		verify(this.repo).save(any(Album.class));
	}

}
