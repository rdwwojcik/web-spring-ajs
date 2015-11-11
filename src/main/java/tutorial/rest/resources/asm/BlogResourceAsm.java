package tutorial.rest.resources.asm;

import tutorial.core.model.Blog;
import tutorial.rest.mvc.AccountController;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.resources.BlogResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Radek on 2015-08-28.
 */
public class BlogResourceAsm extends ResourceAssemblerSupport<Blog, BlogResource> {


    public BlogResourceAsm() {
        super(BlogController.class, BlogResource.class);
    }

    @Override
    public BlogResource toResource(Blog blog) {
        BlogResource blogResource = new BlogResource();
        blogResource.setTitle(blog.getTitle());
        blogResource.add(linkTo(BlogController.class).slash(blog.getId()).withSelfRel());
        blogResource.add(linkTo(BlogController.class).slash(blog.getId()).slash("entries").withRel("entries"));

        if(blog.getOwner() != null){
            blogResource.add(linkTo(AccountController.class).slash(blog.getOwner().getId()).withRel("owner"));
        }

        return blogResource;
    }
}
