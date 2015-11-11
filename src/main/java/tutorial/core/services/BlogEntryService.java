package tutorial.core.services;

import tutorial.core.model.BlogEntry;
import org.springframework.stereotype.Service;

/**
 * Created by Radek on 2015-08-21.
 */
@Service
public interface BlogEntryService {

//    public BlogEntry find(Long id);

    public BlogEntry findBlogEntry(Long id);
//    public BlogEntry deleteBlogEntry(Long id);

    public BlogEntry updateBlogEntry(Long id, BlogEntry data);
}
