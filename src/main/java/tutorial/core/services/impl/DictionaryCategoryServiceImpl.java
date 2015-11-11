package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutorial.core.model.DictionaryCategory;
import tutorial.core.repositories.DictionaryCategoryRepo;
import tutorial.core.services.DictionaryCategoryService;

import javax.transaction.Transactional;

/**
 * Created by Radek on 18.10.2015.
 */
@Service
@Transactional
public class DictionaryCategoryServiceImpl implements DictionaryCategoryService {

    @Autowired
    private DictionaryCategoryRepo dictionaryCategoryRepo;

    @Override
    public DictionaryCategory findCategory(long id) {

        DictionaryCategory dictionaryCategory = dictionaryCategoryRepo.findOne(id);

        return dictionaryCategory;
    }

    @Override
    public DictionaryCategory createCategory(DictionaryCategory dictionaryCategory) {

        DictionaryCategory results = dictionaryCategoryRepo.save(dictionaryCategory);
        return results;
    }
}
