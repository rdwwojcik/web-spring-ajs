package tutorial.core.model;

import javax.persistence.*;

/**
 * Created by Radek on 2015-08-28.
 */
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_account_id")
    @SequenceGenerator(name = "seq_account_id", sequenceName = "seq_account_id")
    @Column(name = "ac_id")
    private Long id;
    @Column(name = "ac_name")
    private String name;
    @Column(name = "ac_password")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
