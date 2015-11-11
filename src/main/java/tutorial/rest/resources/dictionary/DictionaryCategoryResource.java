package tutorial.rest.resources.dictionary;

import org.springframework.hateoas.ResourceSupport;
import java.util.Date;

/**
 * Created by Radek on 18.10.2015.
 */
public class DictionaryCategoryResource extends ResourceSupport {

    private String  name;
    private String  description;
    private Date    time_database;

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
}
