package tutorial.core.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Radek on 18.10.2015.
 */
@Entity(name = "slownik_slowa")
public class DictionaryWord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_slownik_slowa_id")
    @SequenceGenerator(name = "seq_slownik_slowa_id", sequenceName = "seq_slownik_slowa_id")
    @Column(name = "slo_id")
    private long    id;
    @ManyToOne
    @JoinColumn(name = "slo_id_grupy")
    private DictionaryGroup dictionaryGroup;
    @Column(name = "slo_nazwa")
    private String  name;
    @Column(name = "slo_opis")
    private String  description;
    @Column(name = "slo_wartosc")
    private String  value;
    @Column(name = "slo_status")
    private long    status;
    @Column(name = "slo_czas_bazy")
    private Date    timeDatabase;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DictionaryGroup getDictionaryGroup() {
        return dictionaryGroup;
    }

    public void setDictionaryGroup(DictionaryGroup group) {
        this.dictionaryGroup = group;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Date getTimeDatabase() {
        return timeDatabase;
    }

    public void setTimeDatabase(Date timeDatabase) {
        this.timeDatabase = timeDatabase;
    }
}
