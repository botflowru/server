package user;

import dao.UserDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser(@RequestParam("email") String email) {
        UserDao userDao = new UserDao();
        Optional<User> user = userDao.get(email);
        if (user.isPresent()) return user.get();
        User newUser = new User(email, "free", 0);
        userDao.save(newUser);
        return newUser;
    }
}
