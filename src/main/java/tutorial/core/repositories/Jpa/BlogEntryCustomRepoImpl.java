package tutorial.core.repositories.Jpa;

import tutorial.core.model.BlogEntry;
import tutorial.core.repositories.BlogEntryCustomRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Radek on 14.09.2015.
 */
public class BlogEntryCustomRepoImpl implements BlogEntryCustomRepo {


    @PersistenceContext
    private EntityManager em;

    @Override
    public BlogEntry deleteBlogEntry(Long id) {

        BlogEntry blogEntry = em.find(BlogEntry.class, id);
        em.remove(blogEntry);

        return blogEntry;
    }
}
