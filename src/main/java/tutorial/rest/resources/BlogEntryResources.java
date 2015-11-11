package tutorial.rest.resources;

import tutorial.core.model.Blog;
import tutorial.core.model.BlogEntry;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Radek on 2015-08-21.
 */
public class BlogEntryResources extends ResourceSupport {

    private String title;
    private Blog blog;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public BlogEntry toBlogEntry(){

        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle(title);
        blogEntry.setBlog(blog);

        return blogEntry;
    }

}
