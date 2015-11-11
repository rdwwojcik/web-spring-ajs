package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutorial.core.model.Blog;
import tutorial.core.model.BlogEntry;
import tutorial.core.repositories.BlogEntryRepo;
import tutorial.core.repositories.BlogRepo;
import tutorial.core.services.BlogService;
import tutorial.core.services.exceptions.BlogNotFoundException;
import tutorial.core.services.util.BlogEntryList;
import tutorial.core.services.util.BlogList;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Radek on 14.09.2015.
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepo        blogRepo;
    @Autowired
    private BlogEntryRepo   blogEntryRepo;

    @Override
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {
        return blogEntryRepo.save(data);
    }

    @Override
    public BlogList findAllBlogs() {
        return new BlogList((List) blogRepo.findAll());
    }

    @Override
    public BlogEntryList fiindAllBlogEntries(Long blogId) {

        Blog blog = blogRepo.findOne(blogId);

        if(blog == null){
            new BlogNotFoundException();
        }

        return new BlogEntryList(blogId, (List) blogEntryRepo.findAll());
    }

    @Override
    public Blog findBlog(Long id) {

        return blogRepo.findOne(id);
    }
}
