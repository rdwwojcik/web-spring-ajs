package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutorial.core.model.Account;
import tutorial.core.model.Blog;
import tutorial.core.repositories.AccountRepo;
import tutorial.core.repositories.BlogRepo;
import tutorial.core.services.AccountService;
import tutorial.core.services.exceptions.AccountDoesNotExistException;
import tutorial.core.services.exceptions.AccountExistsException;
import tutorial.core.services.exceptions.BlogExistsException;
import tutorial.core.services.util.AccountList;
import tutorial.core.services.util.BlogList;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Radek on 2015-09-04.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private BlogRepo blogRepo;

    @Override
    public Account findAccount(Long id) {

        return accountRepo.findOne(id);
    }

    @Override
    public Account createAccount(Account data) {

        Account account = accountRepo.findByName(data.getName());
        if(account != null)
        {
            throw new AccountExistsException();
        }
        return accountRepo.save(data);
    }

    @Override
    public Blog createBlog(Long accountId, Blog data) {

        Blog blog = blogRepo.findByTitle(data.getTitle());
        if(blog != null){
            throw new BlogExistsException();
        }

        Account account = accountRepo.findOne(accountId);
        if(account == null){
            throw new AccountDoesNotExistException();
        }

        Blog createdBlog = blogRepo.save(data);
        createdBlog.setOwner(account);

        return createdBlog;
    }

    @Override
    public BlogList findBlogsByAccount(Long accountId) {
        Account account = accountRepo.findOne(accountId);
        if(account == null)
        {
            throw new AccountDoesNotExistException();
        }
        return new BlogList((List) blogRepo.findOne(accountId));
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList((List<Account>) accountRepo.findAll());
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepo.findByName(name);
    }

//    @Override
//    public BlogList findBlogsByAccount(Account data) {
//
//        Account account = accountRepo.findOne(data.getId());
//        if(account == null){
//            throw new AccountDoesNotExistException();
//        }
//
//        return blogRepo.findBlogsByAccount(account);
//    }
}
