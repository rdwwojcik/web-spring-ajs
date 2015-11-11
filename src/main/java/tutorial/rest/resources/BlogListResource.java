package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radek on 2015-08-29.
 */
public class BlogListResource extends ResourceSupport {

    private List<BlogResource> blogs = new ArrayList<>();

    public List<BlogResource> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<BlogResource> blogs) {
        this.blogs = blogs;
    }

}
