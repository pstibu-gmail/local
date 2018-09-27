package com.local.msvc.test.controller.component;

import static org.hamcrest.Matchers.startsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.local.msvc.controller.HelloController;

/**
 * 
 * @author Tibu Padmakumar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class HelloControllerTest
{

    private MockMvc mvc;

    /**
     * @throws Exception
     *             -
     */
    @Before
    public void setUp()
            throws Exception
    {
        this.mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    /**
     * @throws Exception
     *             -
     */
    @SuppressWarnings("nls")
    @Test
    public void getHello()
            throws Exception
    {
        this.mvc.perform(MockMvcRequestBuilders.get("/echo").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string(startsWith("{\"status\":\"Success\",\"response\":\"Greetings from Local".replace("\\", ""))));
    }
}
