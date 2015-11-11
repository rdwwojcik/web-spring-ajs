package tutorial.core.services.util;

import tutorial.core.model.BlogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radek on 2015-08-28.
 */
public class BlogEntryList {

    private List<BlogEntry> entries = new ArrayList<>();
    private Long blogId;

    public BlogEntryList(){

    }

    public BlogEntryList(Long idBlog, List listResults){
        this.blogId = idBlog;
        this.entries = listResults;
    }

    public List<BlogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntry> entries) {
        this.entries = entries;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
