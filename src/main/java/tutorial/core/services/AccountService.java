package tutorial.core.services;

import tutorial.core.model.Account;
import tutorial.core.model.Blog;
import tutorial.core.services.util.AccountList;
import tutorial.core.services.util.BlogList;

/**
 * Created by Radek on 2015-08-28.
 */
public interface AccountService {

    public Account findAccount(Long id);
    public Account createAccount(Account data);
    public Blog createBlog(Long accountId, Blog data);
    public BlogList findBlogsByAccount(Long accountId);
    public AccountList findAllAccounts();
    public Account findByAccountName(String name);
//    public BlogList findBlogsByAccount(Account account);
}
