package tutorial.core.model;

import javax.persistence.*;

/**
 * Created by Radek on 2015-08-21.
 */
@Entity
@Table(name = "blog_entry")
public class BlogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_blog_enty_id")
    @SequenceGenerator(name = "seq_blog_enty_id", sequenceName = "seq_blog_enty_id", allocationSize = 1)
    @Column(name = "be_id")
    private Long id;
    @Column(name = "be_title")
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "be_id_blog", nullable = false)
    private Blog blog;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
