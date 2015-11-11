package tutorial.rest.resources.asm;

import tutorial.core.services.util.BlogList;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.resources.BlogListResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Radek on 2015-08-29.
 */
public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResource> {

    public BlogListResourceAsm() {
        super(BlogController.class, BlogListResource.class);
    }

    @Override
    public BlogListResource toResource(BlogList blogList) {
        BlogListResource blogListResource = new BlogListResource();
        blogListResource.setBlogs((new BlogResourceAsm()).toResources(blogList.getBlogs()));

        return blogListResource;
    }
}
