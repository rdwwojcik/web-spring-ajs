package tutorial;
/**
 * Created by Radek on 2015-08-23.
 */

import tutorial.core.model.Blog;
import tutorial.core.model.BlogEntry;
import tutorial.core.services.BlogEntryService;
import tutorial.rest.mvc.BlogEntryController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class BlogEntryControllerTest {
    @InjectMocks
    private BlogEntryController controller;
    @Mock
    private BlogEntryService service;
    private MockMvc mockMvc;

    public BlogEntryControllerTest() {
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.controller}).build();
    }

    @Test
    public void getExistingBlogEntry() throws Exception {
        BlogEntry entry = new BlogEntry();
        entry.setId(1L);
        entry.setTitle("Test Title");
        Blog blog = new Blog();
        blog.setId(1L);
        entry.setBlog(blog);

        Mockito.when(this.service.findBlogEntry(Long.valueOf(1L))).thenReturn(entry);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/blog-entries/1", new Object[0]))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is(entry.getTitle())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.links[*].href", Matchers.hasItems(new Matcher[]{Matchers.endsWith("/blogs/1"), Matchers.endsWith("/blog-entries/1")})))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.links[*].rel", Matchers.hasItems(new Matcher[]{Matchers.is("self"), Matchers.is("blog")})))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getNonExistingBlogEntry() throws Exception {
        Mockito.when(this.service.findBlogEntry(Long.valueOf(1L))).thenReturn((BlogEntry) null);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/blog-entries/1", new Object[0]))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

//    @Test
//    public void deleteExistingBlogEntry() throws Exception {
//        BlogEntry deletedBlogEntry = new BlogEntry();
//        deletedBlogEntry.setId(Long.valueOf(1L));
//        deletedBlogEntry.setTitle("Test Title");
//        Mockito.when(this.service.deleteBlogEntry(Long.valueOf(1L))).thenReturn(deletedBlogEntry);
//        this.mockMvc.perform(MockMvcRequestBuilders.delete("/rest/blog-entries/1", new Object[0]))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is(deletedBlogEntry.getTitle())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.links[*].href", Matchers.hasItem(Matchers.endsWith("/blog-entries/1"))))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void deleteNonExistingBlogEntry() throws Exception {
//        Mockito.when(this.service.deleteBlogEntry(Long.valueOf(1L))).thenReturn((BlogEntry) null);
//        this.mockMvc.perform(MockMvcRequestBuilders.delete("/rest/blog-entries/1", new Object[0]))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }

    @Test
    public void updateExistingBlogEntry() throws Exception {
        BlogEntry updatedEntry = new BlogEntry();
        updatedEntry.setId(Long.valueOf(1L));
        updatedEntry.setTitle("Test Title");
        Mockito.when(this.service.updateBlogEntry(Long.valueOf(org.mockito.Matchers.eq(1L)), (BlogEntry)org.mockito.Matchers.any(BlogEntry.class))).thenReturn(updatedEntry);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/rest/blog-entries/1", new Object[0]).content("{\"title\":\"Test Title\"}").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is(updatedEntry.getTitle()))).andExpect(MockMvcResultMatchers.jsonPath("$.links[*].href", Matchers.hasItem(Matchers.endsWith("/blog-entries/1")))).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateNonExistingBlogEntry() throws Exception {
        Mockito.when(this.service.updateBlogEntry(Long.valueOf(org.mockito.Matchers.eq(1L)), (BlogEntry)org.mockito.Matchers.any(BlogEntry.class)))
                .thenReturn((BlogEntry) null);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/rest/blog-entries/1", new Object[0]).content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

//    @Test
//    public void getExistingBlogEntry() throws Exception {
//
//        BlogEntry entry = new BlogEntry();
//        entry.setId(1L);
//        entry.setTitle("Test Title");
//
//        when(service.find(1L)).thenReturn(entry);
//
//        mockMvc.perform(get("/rest/blog-entries/1"))
//                .andDo(print())
//                .andExpect(jsonPath("$.title", is(entry.getTitle())))
//                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog-entries/1"))))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getNotExistingBlogEntry() throws Exception {
//        when(service.find(1L)).thenReturn(null);
//
//        mockMvc.perform(get("/rest/blog-entries/1"))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//}
