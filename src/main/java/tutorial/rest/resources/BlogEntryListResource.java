package tutorial.rest.resources;

import tutorial.core.model.BlogEntry;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radek on 2015-08-30.
 */
public class BlogEntryListResource extends ResourceSupport{

    private List<BlogEntry> entries = new ArrayList<>();

    public List<BlogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntry> entries) {
        this.entries = entries;
    }
}
