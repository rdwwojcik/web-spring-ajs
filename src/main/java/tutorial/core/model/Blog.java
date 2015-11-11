package tutorial.core.model;

import javax.persistence.*;

/**
 * Created by Radek on 2015-08-28.
 */
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_blog_id")
    @SequenceGenerator(name = "seq_blog_id", sequenceName = "seq_blog_id", allocationSize = 1)
    @Column(name = "bl_id")
    private Long id;
    @Column(name = "bl_title")
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bl_id_account", nullable = false)
    private Account owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }
}
