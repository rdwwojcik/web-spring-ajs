package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutorial.core.model.BlogEntry;
import tutorial.core.repositories.BlogEntryRepo;
import tutorial.core.services.BlogEntryService;

import javax.transaction.Transactional;

/**
 * Created by Radek on 14.09.2015.
 */
@Service
@Transactional
public class BlogEntryServiceImpl implements BlogEntryService {

    @Autowired
    BlogEntryRepo blogEntryRepo;

    @Override
    public BlogEntry findBlogEntry(Long id) {
        return blogEntryRepo.findOne(id);
    }

//    @Override
//    public BlogEntry deleteBlogEntry(Long id) {
//        return blogEntryRepo.deleteBlogEntry(id);
//    }

    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
        return blogEntryRepo.save(data);
    }
}
