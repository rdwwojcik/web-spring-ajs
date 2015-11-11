package tutorial.rest.resources;

import tutorial.core.model.Blog;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Radek on 2015-08-28.
 */
public class BlogResource extends ResourceSupport{

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Blog toBlog(){
        Blog blog = new Blog();
        blog.setTitle(title);

        return blog;
    }
}
