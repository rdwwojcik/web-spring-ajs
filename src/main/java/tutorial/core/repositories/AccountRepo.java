package tutorial.core.repositories;

import org.springframework.data.repository.CrudRepository;
import tutorial.core.model.Account;
import tutorial.core.model.Blog;

/**
 * Created by Radek on 2015-09-03.
 */
public interface  AccountRepo extends CrudRepository<Account, Long> {

    public Account findByName(String name);

//    public Account findAccount(Long id);
//    public Account createAccount(Account data);
//    public Blog createBlog(Long accountId, Blog data);
}
