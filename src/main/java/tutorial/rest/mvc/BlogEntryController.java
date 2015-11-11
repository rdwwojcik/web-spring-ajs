package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import tutorial.core.model.BlogEntry;
import tutorial.core.services.BlogEntryService;
import tutorial.rest.resources.BlogEntryResources;
import tutorial.rest.resources.asm.BlogEntryResourceAsm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Radek on 2015-08-21.
 */
@Controller
@RequestMapping(value = "/rest/blog-entries")
public class BlogEntryController {

    private BlogEntryService service;

    @Autowired
    public BlogEntryController(BlogEntryService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{blogEntryId}", method = RequestMethod.GET)
    public ResponseEntity<BlogEntryResources> getBlogEntry(@PathVariable Long blogEntryId){

        BlogEntry entry = service.findBlogEntry(blogEntryId);

        if(entry != null) {
            BlogEntryResources res = new BlogEntryResourceAsm().toResource(entry);

            return new ResponseEntity<BlogEntryResources>(res, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<BlogEntryResources>(HttpStatus.NOT_FOUND);
        }
    }

//--------------------------------------------------------------------------------

//    @RequestMapping(value = "/{blogEntryId}", method = RequestMethod.DELETE)
//    public ResponseEntity<BlogEntryResources> deleteBlogEntry(@PathVariable Long blogEntryId) {
//        BlogEntry entry = this.service.deleteBlogEntry(blogEntryId);
//        if(entry != null) {
//            BlogEntryResources res = (new BlogEntryResourceAsm()).toResource(entry);
//            return new ResponseEntity(res, HttpStatus.OK);
//        } else {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//    }

//--------------------------------------------------------------------------------

    @RequestMapping(value = {"/{blogEntryId}"},method = {RequestMethod.PUT})
    public ResponseEntity<BlogEntryResources> updateBlogEntry(@PathVariable Long blogEntryId, @RequestBody BlogEntryResources sentBlogEntry) {
        BlogEntry updatedEntry = this.service.updateBlogEntry(blogEntryId, sentBlogEntry.toBlogEntry());
        if(updatedEntry != null) {
            BlogEntryResources res = (new BlogEntryResourceAsm()).toResource(updatedEntry);
            return new ResponseEntity(res, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

//--------------------------------------------------------------------------------
}
