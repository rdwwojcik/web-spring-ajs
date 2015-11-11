package tutorial.core.repositories;

import org.springframework.data.repository.CrudRepository;
import tutorial.core.model.DictionaryCategory;

/**
 * Created by Radek on 18.10.2015.
 */
public interface DictionaryCategoryRepo extends CrudRepository<DictionaryCategory, Long> {
}
