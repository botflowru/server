package user;

import javax.persistence.*;

@Entity
@Table(name = "bot", schema = "public")
public class Bot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "bot_name")
    private String name;
    private String email;

    public Bot() {}

    public Bot(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Bot(String name, String email, Integer id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
