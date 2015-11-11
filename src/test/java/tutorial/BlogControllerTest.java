package tutorial;

/**
 * Created by Radek on 2015-08-30.
 */

import tutorial.core.model.Account;
import tutorial.core.model.Blog;
import tutorial.core.model.BlogEntry;
import tutorial.core.services.BlogService;
import tutorial.core.services.exceptions.BlogNotFoundException;
import tutorial.core.services.util.BlogEntryList;
import tutorial.core.services.util.BlogList;
import tutorial.rest.mvc.BlogController;
import org.hamcrest.Matcher;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

public class BlogControllerTest {
    @InjectMocks
    private BlogController controller;
    @Mock
    private BlogService blogService;
    private MockMvc mockMvc;

    public BlogControllerTest() {
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.controller}).build();
    }

    @Test
    public void findAllBlogs() throws Exception {
        ArrayList list = new ArrayList();
        Blog blogA = new Blog();
        blogA.setId(Long.valueOf(1L));
        blogA.setTitle("Title A");
        list.add(blogA);
        Blog blogB = new Blog();
        blogB.setId(Long.valueOf(2L));
        blogB.setTitle("Title B");
        list.add(blogB);
        BlogList allBlogs = new BlogList();
        allBlogs.setBlogs(list);

        Mockito.when(this.blogService.findAllBlogs()).thenReturn(allBlogs);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/blogs", new Object[0]))
                .andExpect(MockMvcResultMatchers.jsonPath("$.blogs[*].title", Matchers.hasItems(new Matcher[]{Matchers.endsWith("Title A"), Matchers.endsWith("Title B")})))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getBlog() throws Exception {
        Blog blog = new Blog();
        blog.setTitle("Test Title");
        blog.setId(Long.valueOf(1L));
        Account account = new Account();
        account.setId(Long.valueOf(1L));
        blog.setOwner(account);
        Mockito.when(this.blogService.findBlog(Long.valueOf(1L))).thenReturn(blog);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/blogs/1", new Object[0])).andExpect(MockMvcResultMatchers.jsonPath("$.links[*].href", Matchers.hasItem(Matchers.endsWith("/blogs/1")))).andExpect(MockMvcResultMatchers.jsonPath("$.links[*].href", Matchers.hasItem(Matchers.endsWith("/blogs/1/entries")))).andExpect(MockMvcResultMatchers.jsonPath("$.links[*].href", Matchers.hasItem(Matchers.endsWith("/accounts/1")))).andExpect(MockMvcResultMatchers.jsonPath("$.links[*].rel", Matchers.hasItems(new Matcher[]{Matchers.is("self"), Matchers.is("owner"), Matchers.is("entries")}))).andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("Test Title"))).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createBlogEntryExistingBlog() throws Exception {
        Blog blog = new Blog();
        blog.setId(Long.valueOf(1L));
        BlogEntry entry = new BlogEntry();
        entry.setTitle("Test Title");
        entry.setId(Long.valueOf(1L));
        Mockito.when(this.blogService.createBlogEntry(Long.valueOf(org.mockito.Matchers.eq(1L)), (BlogEntry)org.mockito.Matchers.any(BlogEntry.class))).thenReturn(entry);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/blogs/1/blog-entries", new Object[0]).content("{\"title\":\"Generic Title\"}").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is(entry.getTitle()))).andExpect(MockMvcResultMatchers.jsonPath("$.links[*].href", Matchers.hasItem(Matchers.endsWith("rest/blog-entries/1")))).andExpect(MockMvcResultMatchers.header().string("Location", Matchers.endsWith("rest/blog-entries/1"))).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createBlogEntryNonExistingBlog() throws Exception {
        Mockito.when(this.blogService.createBlogEntry(Long.valueOf(org.mockito.Matchers.eq(1L)), (BlogEntry)org.mockito.Matchers.any(BlogEntry.class))).thenThrow(new Throwable[]{new BlogNotFoundException()});
        this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/blogs/1/blog-entries", new Object[0]).content("{\"title\":\"Generic Title\"}").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void listBlogEntriesForExistingBlog() throws Exception {
        BlogEntry entryA = new BlogEntry();
        entryA.setId(Long.valueOf(1L));
        entryA.setTitle("Test Title");
        BlogEntry entryB = new BlogEntry();
        entryB.setId(Long.valueOf(2L));
        entryB.setTitle("Test Title");
        ArrayList blogListings = new ArrayList();
        blogListings.add(entryA);
        blogListings.add(entryB);
        BlogEntryList list = new BlogEntryList();
        list.setEntries(blogListings);
        list.setBlogId(Long.valueOf(1L));
        Mockito.when(this.blogService.fiindAllBlogEntries(Long.valueOf(1L))).thenReturn(list);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/blogs/1/blog-entries", new Object[0])).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.jsonPath("$.links[*].href", Matchers.hasItem(Matchers.endsWith("/blogs/1/blog-entries")))).andExpect(MockMvcResultMatchers.jsonPath("$.entries[*].title", Matchers.hasItem(Matchers.is("Test Title")))).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void listBlogEntriesForNonExistingBlog() throws Exception {
        Mockito.when(this.blogService.fiindAllBlogEntries(Long.valueOf(1L))).thenThrow(new Throwable[]{new BlogNotFoundException()});
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/blogs/1/blog-entries", new Object[0]))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
