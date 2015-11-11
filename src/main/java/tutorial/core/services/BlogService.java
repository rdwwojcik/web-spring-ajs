package tutorial.core.services;

import tutorial.core.model.Blog;
import tutorial.core.model.BlogEntry;
import tutorial.core.services.util.BlogEntryList;
import tutorial.core.services.util.BlogList;

/**
 * Created by Radek on 2015-08-28.
 */
public interface BlogService {

    public BlogEntry createBlogEntry(Long blogId, BlogEntry data);

    public BlogList findAllBlogs();

    public BlogEntryList fiindAllBlogEntries(Long blogId);

    public Blog findBlog(Long id);
}
