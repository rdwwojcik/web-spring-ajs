package tutorial.rest.resources.dictionary;

/**
 * Created by Radek on 06.11.2015.
 */
public class DictionaryWordResource {

    private String  name;
    private String  description;
    private String  value;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    private long    status;
}
