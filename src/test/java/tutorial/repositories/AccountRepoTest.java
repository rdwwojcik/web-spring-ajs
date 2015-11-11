package tutorial.repositories;

import tutorial.config.RepositoryConfiguration;
import tutorial.core.model.Account;
import tutorial.core.repositories.AccountRepo;
import tutorial.core.repositories.jpa.JpaAccountRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Radek on 2015-09-03.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryConfiguration.class)
public class AccountRepoTest {

    @Autowired
    private AccountRepo repo;

    private Account account;

    @Before
    @Transactional
    @Rollback(false)
    public void setup(){
        account = new Account();
        account.setName("name");
        account.setPassword("password");

        repo.save(account);
    }

    @Test
    @Transactional
    public void testFind(){

        assertNotNull(repo.findOne(account.getId()));
    }
}
