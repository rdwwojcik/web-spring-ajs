package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutorial.core.model.DictionaryWord;
import tutorial.core.repositories.DictionaryWordRepo;
import tutorial.core.services.DictionaryWordService;

import javax.transaction.Transactional;

/**
 * Created by Radek on 18.10.2015.
 */
@Service
@Transactional
public class DictionaryWordServiceImpl implements DictionaryWordService {

    @Autowired
    private DictionaryWordRepo dictionaryWordRepo;

    @Override
    public DictionaryWord findWord(long id) {
        DictionaryWord word = dictionaryWordRepo.findOne(id);
        return word;
    }

    @Override
    public DictionaryWord createWord(DictionaryWord dictionaryWord) {
        DictionaryWord word = dictionaryWordRepo.save(dictionaryWord);
        return word;
    }
}
