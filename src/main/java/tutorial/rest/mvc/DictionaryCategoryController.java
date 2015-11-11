package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tutorial.core.services.DictionaryCategoryService;
import tutorial.rest.resources.dictionary.DictionaryCategoryResource;

/**
 * Created by Radek on 18.10.2015.
 */
@Controller
@RequestMapping("/rest/dictionaryCategory")
public class DictionaryCategoryController {

    @Autowired
    private DictionaryCategoryService dictionaryCategoryService;

    @RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<DictionaryCategoryResource> getDictionaryCategory(){

        return null;
    }

}
