package tutorial.dictionary;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tutorial.core.model.DictionaryCategory;
import tutorial.core.services.DictionaryCategoryService;
import tutorial.rest.mvc.DictionaryCategoryController;

import javax.servlet.ServletContext;

/**
 * Created by Radek on 18.10.2015.
 */
public class DictionaryCategoryControllerTest {

    @InjectMocks
    private DictionaryCategoryController dictionaryCategoryController;
    @Mock
    private DictionaryCategoryService dictionaryCategoryService;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dictionaryCategoryController).build();
    }

    @Test
    public void getExistingCategory() throws Exception{

        DictionaryCategory dictionaryCategory = new DictionaryCategory();
        dictionaryCategory.setId(1);
        dictionaryCategory.setName("Slownik 1");
        dictionaryCategory.setDescription("Opis slownik 1");

        Mockito.when(this.dictionaryCategoryService.findCategory(1L)).thenReturn(dictionaryCategory);


    }
}
