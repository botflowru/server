package user;

import dao.BotDao;
import dao.UserDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    @GetMapping("/bots")
    public List<Bot> getBots(@RequestParam("email") String email) {
        BotDao botDao = new BotDao();
        return botDao.getBots(email);
    }

    @GetMapping("/bot")
    public void actionBot(@RequestParam("action") String action, @RequestParam("bot") Bot bot) {
        BotDao botDao = new BotDao();
        switch (action) {
            case "save":
                botDao.save(bot);
                break;
            case "update":
                botDao.update(bot);
                break;
            case "delete":
                botDao.delete(bot);
                break;
        }
    }
}
