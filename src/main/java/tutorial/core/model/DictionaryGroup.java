package tutorial.core.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Radek on 16.10.2015.
 */
@Entity(name = "slownik_grupy")
public class DictionaryGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_slownik_grupy_id")
    @SequenceGenerator(name = "seq_slownik_grupy_id", sequenceName = "seq_slownik_grupy_id")
    @Column(name = "sg_id")
    private long    id;
    @ManyToOne
    @JoinColumn(name = "sg_id_kategorii")
    private DictionaryCategory dictionaryCategory;
    @Column(name = "sg_nazwa")
    private String  name;
    @Column(name = "sg_opis")
    private String  description;
    @Column(name = "sg_czas_bazy")
    private Date    timeDatabase;
    @OneToMany(mappedBy = "dictionaryGroup")
    private Set<DictionaryWord> dictionaryWords;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DictionaryCategory getDictionaryCategory() {
        return dictionaryCategory;
    }

    public void setDictionaryCategory(DictionaryCategory dictionaryCategory) {
        this.dictionaryCategory = dictionaryCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeDatabase() {
        return timeDatabase;
    }

    public void setTimeDatabase(Date timeDatabase) {
        this.timeDatabase = timeDatabase;
    }

    public Set<DictionaryWord> getDictionaryWords() {
        return dictionaryWords;
    }

    public void setDictionaryWords(Set<DictionaryWord> dictionaryWords) {
        this.dictionaryWords = dictionaryWords;
    }
}
