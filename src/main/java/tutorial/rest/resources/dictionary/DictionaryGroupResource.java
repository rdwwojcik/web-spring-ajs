package tutorial.rest.resources.dictionary;

import tutorial.core.model.DictionaryWord;

import java.util.Date;
import java.util.Set;

/**
 * Created by Radek on 06.11.2015.
 */
public class DictionaryGroupResource {

    private String              name;
    private String              description;

    public Set<DictionaryWord> getDictionaryWords() {
        return dictionaryWords;
    }

    public void setDictionaryWords(Set<DictionaryWord> dictionaryWords) {
        this.dictionaryWords = dictionaryWords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Set<DictionaryWord> dictionaryWords;
}
