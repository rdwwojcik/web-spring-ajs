package tutorial.rest.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tutorial.core.services.BlogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Radek on 2015-08-14.
 */
@Controller
public class TestController {

    @RequestMapping("/create")
    @ResponseBody
    public String create(String name){
        String s ="Sukces Id: " + name;
        return s;
    }

//    @RequestMapping(value = "/hello", method = RequestMethod.POST)
//    @ResponseBody
//    public BlogEntry showTest(@RequestBody BlogEntry entry){
//
////        BlogEntry entry =  new BlogEntry();
////        entry.setTitle("Test blog entry");
//
//        return entry;
//    }
}
