package tutorial.core.repositories;

import org.springframework.data.repository.CrudRepository;
import tutorial.core.model.DictionaryWord;

/**
 * Created by Radek on 18.10.2015.
 */
public interface DictionaryWordRepo extends CrudRepository<DictionaryWord, Long> {
}
