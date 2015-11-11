package tutorial;

import tutorial.core.services.BlogEntryService;
import tutorial.rest.mvc.TestController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by Radek on 2015-08-21.
 */
public class TestControllerTest {

    @InjectMocks
    private TestController testController;

    @Mock
    private BlogEntryService service;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(post("/hello")
                    .content("{\"title\":\"Test blog title\"}")
                    .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$.title", is("Test blog title")))
            .andDo(print());
    }

}
