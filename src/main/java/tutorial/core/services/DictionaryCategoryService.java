package tutorial.core.services;

import tutorial.core.model.DictionaryCategory;

/**
 * Created by Radek on 18.10.2015.
 */
public interface DictionaryCategoryService {

    DictionaryCategory findCategory(long id);
    DictionaryCategory createCategory(DictionaryCategory dictionaryCategory);
}
