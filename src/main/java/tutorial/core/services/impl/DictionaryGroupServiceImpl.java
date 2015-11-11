package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tutorial.core.model.DictionaryGroup;
import tutorial.core.repositories.DictionaryGroupRepo;
import tutorial.core.services.DictionaryGroupService;

import javax.transaction.Transactional;

/**
 * Created by Radek on 18.10.2015.
 */
@Service
@Transactional
public class DictionaryGroupServiceImpl implements DictionaryGroupService {

    @Autowired
    private DictionaryGroupRepo dictionaryGroupRepo;

    @Override
    public DictionaryGroup findGroup(long id) {
        DictionaryGroup group = dictionaryGroupRepo.findOne(id);
        return group;
    }

    @Override
    public DictionaryGroup createGroup(DictionaryGroup dictionaryGroup) {
        DictionaryGroup group = dictionaryGroupRepo.save(dictionaryGroup);
        return group;
    }
}
