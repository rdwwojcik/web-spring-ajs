package tutorial.core.repositories;

import org.springframework.data.repository.CrudRepository;
import tutorial.core.model.DictionaryGroup;

/**
 * Created by Radek on 18.10.2015.
 */
public interface DictionaryGroupRepo extends CrudRepository<DictionaryGroup, Long> {
}
