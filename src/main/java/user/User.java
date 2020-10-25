package user;

import javax.persistence.*;

@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String plan;
    private Integer bots;

    public User(String email, String plan, Integer bots) {
        this.email = email;
        this.plan = plan;
        this.bots = bots;
    }

    public User(Integer id, String email, String plan, Integer bots){
        this.id = id;
        this.email = email;
        this.plan = plan;
        this.bots = bots;
    }

    public User() {}

    public String getEmail() {
        return email;
    }

    public String getPlan() {
        return plan;
    }

    public int getBots() {
        return bots;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
