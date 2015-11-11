package tutorial.rest.resources.asm;

import tutorial.core.model.BlogEntry;
import tutorial.rest.mvc.BlogController;
import tutorial.rest.mvc.BlogEntryController;
import tutorial.rest.resources.BlogEntryResources;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by Radek on 2015-08-21.
 */
public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResources> {


    public BlogEntryResourceAsm() {
        super(BlogEntryController.class, BlogEntryResources.class);
    }

    public BlogEntryResources toResource(BlogEntry blogEntry) {
        BlogEntryResources res = new BlogEntryResources();
        res.setTitle(blogEntry.getTitle());
        Link self = ((ControllerLinkBuilder)ControllerLinkBuilder.linkTo(BlogEntryController.class).slash(blogEntry.getId())).withSelfRel();
        res.add(self);
        if(blogEntry.getBlog() != null) {
            res.add(((ControllerLinkBuilder)ControllerLinkBuilder.linkTo(BlogController.class).slash(blogEntry.getBlog().getId())).withRel("blog"));
        }

        return res;
    }

//    @Override
//    public BlogEntryResources toResource(BlogEntry blogEntry) {
//        BlogEntryResources res = new BlogEntryResources();
//        res.setTitle(blogEntry.getTitle());
//
////        Link link = linkTo(methodOn(BlogEntryController.class).getBlogEntry(blogEntry.getId())).withSelfRel();
//        Link link = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();
//        res.add(link.withSelfRel());
//
//        return res;
//    }
}
