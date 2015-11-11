package tutorial.core.repositories;

import org.springframework.data.repository.CrudRepository;
import tutorial.core.model.Account;
import tutorial.core.model.Blog;
import tutorial.core.services.util.BlogList;

/**
 * Created by Radek on 2015-09-04.
 */
public interface BlogRepo extends CrudRepository<Blog, Long> {

    public Blog findByTitle(String title);

//    public BlogList findBlogsByAccount(Account account);
}
