package tutorial.core.services;

import tutorial.core.model.DictionaryGroup;

/**
 * Created by Radek on 18.10.2015.
 */
public interface DictionaryGroupService {

    DictionaryGroup findGroup(long id);
    DictionaryGroup createGroup(DictionaryGroup dictionaryGroup);
}
