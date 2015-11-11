package tutorial.core.services.util;

import tutorial.core.model.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Radek on 2015-08-28.
 */
public class BlogList {

    private List<Blog> blogs = new ArrayList<>();

    public BlogList(){

    }

    public BlogList(List listResults){
        this.blogs = listResults;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
