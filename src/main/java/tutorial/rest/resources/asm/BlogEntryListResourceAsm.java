package tutorial.rest.resources.asm;

import tutorial.core.services.util.BlogEntryList;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.resources.BlogEntryListResource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

/**
 * Created by Radek on 2015-08-30.
 */
public class BlogEntryListResourceAsm extends ResourceAssemblerSupport<BlogEntryList, BlogEntryListResource> {

    public BlogEntryListResourceAsm() {
        super(BlogController.class, BlogEntryListResource.class);
    }

    @Override
    public BlogEntryListResource toResource(BlogEntryList blogEntryList) {
        List resources = (new BlogEntryResourceAsm()).toResources(blogEntryList.getEntries());
        BlogEntryListResource listResource = new BlogEntryListResource();
        listResource.setEntries(resources);
        listResource.add(ControllerLinkBuilder.linkTo(((BlogController) ControllerLinkBuilder.methodOn(BlogController.class, new Object[0])).findAllBlogEntries(blogEntryList.getBlogId())).withSelfRel());
        return listResource;
    }
}
