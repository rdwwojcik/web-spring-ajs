package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import tutorial.core.model.Blog;
import tutorial.core.model.BlogEntry;
import tutorial.core.services.BlogService;
import tutorial.core.services.exceptions.BlogNotFoundException;
import tutorial.core.services.util.BlogEntryList;
import tutorial.core.services.util.BlogList;
import tutorial.rest.exceptions.NotFoundException;
import tutorial.rest.resources.BlogEntryListResource;
import tutorial.rest.resources.BlogEntryResources;
import tutorial.rest.resources.BlogListResource;
import tutorial.rest.resources.BlogResource;
import tutorial.rest.resources.asm.BlogEntryListResourceAsm;
import tutorial.rest.resources.asm.BlogEntryResourceAsm;
import tutorial.rest.resources.asm.BlogListResourceAsm;
import tutorial.rest.resources.asm.BlogResourceAsm;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

/**
 * Created by Radek on 2015-08-28.
 */
@Controller
@RequestMapping(value = "/rest/blogs")
public class BlogController {

    BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

//--------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BlogListResource> findAllBlogs(){

        BlogList blogList = blogService.findAllBlogs();
        BlogListResource blogListResource = new BlogListResourceAsm().toResource(blogList);
        return new ResponseEntity<BlogListResource>(blogListResource, HttpStatus.OK);
    }

//--------------------------------------------------------------------------

    @RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
    public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId){

        Blog blog = blogService.findBlog(blogId);
        BlogResource blogResource = new BlogResourceAsm().toResource(blog);

        return new ResponseEntity<BlogResource>(blogResource, HttpStatus.OK);
    }

//--------------------------------------------------------------------------

    @RequestMapping(value = "/{blogId}/blog-entries", method = RequestMethod.POST)
    public ResponseEntity<BlogEntryResources> createBlogEntry(@PathVariable Long blogId, @RequestBody BlogEntryResources sentBlogEntry){

        BlogEntry createdBlogEntry = null;
        try {
            createdBlogEntry = blogService.createBlogEntry(blogId, sentBlogEntry.toBlogEntry());
            BlogEntryResources createdResource = new BlogEntryResourceAsm().toResource(createdBlogEntry);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
            return new ResponseEntity<BlogEntryResources>(createdResource, headers, HttpStatus.CREATED);
        } catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

//--------------------------------------------------------------------------

    @RequestMapping(value = "/{blogId}/blog-entries")
    public ResponseEntity<BlogEntryListResource> findAllBlogEntries(@PathVariable Long blogId){

        try {
            BlogEntryList list = blogService.fiindAllBlogEntries(blogId);
            BlogEntryListResource res = new BlogEntryListResourceAsm().toResource(list);
            return new ResponseEntity<BlogEntryListResource>(res, HttpStatus.OK);
        } catch(BlogNotFoundException exception)
        {
            throw new NotFoundException(exception);
        }
    }
}
