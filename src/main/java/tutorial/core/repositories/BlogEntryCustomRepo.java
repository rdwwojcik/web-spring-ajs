package tutorial.core.repositories;

import tutorial.core.model.BlogEntry;

/**
 * Created by Radek on 14.09.2015.
 */
public interface BlogEntryCustomRepo {

    public BlogEntry deleteBlogEntry(Long id);
}
