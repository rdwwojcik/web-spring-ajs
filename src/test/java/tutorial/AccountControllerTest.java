package tutorial;

/**
 * Created by Radek on 2015-08-30.
 */

import tutorial.core.model.Account;
import tutorial.core.model.Blog;
import tutorial.core.services.AccountService;
import tutorial.core.services.exceptions.AccountDoesNotExistException;
import tutorial.core.services.exceptions.AccountExistsException;
import tutorial.core.services.exceptions.BlogExistsException;
import tutorial.rest.mvc.AccountController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class AccountControllerTest {
    @InjectMocks
    private AccountController controller;
    @Mock
    private AccountService service;
    private MockMvc mockMvc;
    private ArgumentCaptor<Account> accountCaptor;

    public AccountControllerTest() {
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new Object[]{this.controller}).build();
        this.accountCaptor = ArgumentCaptor.forClass(Account.class);
    }

    @Test
    public void createBlogExistingAccount() throws Exception {
        Blog createdBlog = new Blog();
        createdBlog.setId(Long.valueOf(1L));
        createdBlog.setTitle("Test Title");
        Mockito.when(this.service.createBlog(Long.valueOf(Matchers.eq(1L)), (Blog)Matchers.any(Blog.class))).thenReturn(createdBlog);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/accounts/1/blogs", new Object[0])
                .content("{\"title\":\"Test Title\"}").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", is("Test Title")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.links[*].href", org.hamcrest.Matchers.hasItem(org.hamcrest.Matchers.endsWith("/blogs/1"))))
                .andExpect(MockMvcResultMatchers.header().string("Location", org.hamcrest.Matchers.endsWith("/blogs/1")))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createBlogNonExistingAccount() throws Exception {
        Mockito.when(this.service.createBlog(Long.valueOf(Matchers.eq(1L)), (Blog)Matchers.any(Blog.class))).thenThrow(new Throwable[]{new AccountDoesNotExistException()});
        this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/accounts/1/blogs", new Object[0])
                .content("{\"title\":\"Test Title\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createBlogExistingBlogName() throws Exception {
        Mockito.when(this.service.createBlog(Long.valueOf(Matchers.eq(1L)), (Blog)Matchers.any(Blog.class))).thenThrow(new Throwable[]{new BlogExistsException()});
        this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/accounts/1/blogs", new Object[0])
                .content("{\"title\":\"Test Title\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void createAccountNonExistingUsername() throws Exception {
        Account createdAccount = new Account();
        createdAccount.setId(Long.valueOf(1L));
        createdAccount.setPassword("test");
        createdAccount.setName("test");

        Mockito.when(this.service.createAccount((Account)Matchers.any(Account.class))).thenReturn(createdAccount);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/accounts")
                .content("{\"name\":\"test\",\"password\":\"test\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.header().string("Location", org.hamcrest.Matchers.endsWith("/rest/accounts/1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(createdAccount.getName())))
                .andExpect(MockMvcResultMatchers.status().isCreated());

//        ((AccountService)Mockito.
        verify(service).createAccount(accountCaptor.capture());
        String password = ((Account)this.accountCaptor.getValue()).getPassword();
        Assert.assertEquals("test", password);
    }

    @Test
    public void createAccountExistingUsername() throws Exception {
        Account createdAccount = new Account();
        createdAccount.setId(Long.valueOf(1L));
        createdAccount.setPassword("test");
        createdAccount.setName("test");
        Mockito.when(this.service.createAccount((Account)Matchers.any(Account.class))).thenThrow(new Throwable[]{new AccountExistsException()});
        this.mockMvc.perform(MockMvcRequestBuilders.post("/rest/accounts", new Object[0]).content("{\"name\":\"test\",\"password\":\"test\"}").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void getExistingAccount() throws Exception {
        Account foundAccount = new Account();
        foundAccount.setId(1L);
        foundAccount.setPassword("test");
        foundAccount.setName("test");

        Mockito.when(this.service.findAccount(1L)).thenReturn(foundAccount);

        this.mockMvc.perform(get("/rest/accounts/1", new Object[0]))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password", is(nullValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(foundAccount.getName())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    public void getNonExistingAccount() throws Exception {
//        Mockito.when(this.service.findAccount(Long.valueOf(1L))).thenReturn((Object)null);
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/rest/accounts/1", new Object[0])).andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
}
