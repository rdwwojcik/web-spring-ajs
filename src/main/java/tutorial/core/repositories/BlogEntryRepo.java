package tutorial.core.repositories;

import org.springframework.data.repository.CrudRepository;
import tutorial.core.model.BlogEntry;

/**
 * Created by Radek on 14.09.2015.
 */
public interface BlogEntryRepo extends CrudRepository<BlogEntry, Long>{

//    public List<BlogEntry> findBy

}
