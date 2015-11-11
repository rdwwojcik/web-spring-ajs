package tutorial.core.services;

import tutorial.core.model.DictionaryWord;

/**
 * Created by Radek on 18.10.2015.
 */
public interface DictionaryWordService {

    DictionaryWord findWord(long id);
    DictionaryWord createWord(DictionaryWord dictionaryWord);
}
