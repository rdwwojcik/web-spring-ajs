package tutorial.core.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Radek on 16.10.2015.
 */
@Entity(name = "slownik_kategorie")
public class DictionaryCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_slownik_kategorie_id")
    @SequenceGenerator(name = "seq_slownik_kategorie_id", sequenceName = "seq_slownik_kategorie_id")
    @Column(name = "sk_id")
    private long    id;
    @Column(name = "sk_nazwa")
    private String  name;
    @Column(name = "sk_opis")
    private String  description;
    @Column(name = "sk_czas_bazy")
    private Date    time_database;
    @OneToMany(mappedBy = "dictionaryCategory")
    private Set<DictionaryGroup> dictionaryGroups;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getTime_database() {
        return time_database;
    }

    public void setTime_database(Date time_database) {
        this.time_database = time_database;
    }

    public Set<DictionaryGroup> getDictionaryGroups() {
        return dictionaryGroups;
    }

    public void setDictionaryGroups(Set<DictionaryGroup> dictionaryGroups) {
        this.dictionaryGroups = dictionaryGroups;
    }
}
