package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Алексей", "Петров", (byte) 41);
        userService.saveUser("Надежда", "Гуляева", (byte) 41);
        userService.saveUser("Евгений", "Дворцов", (byte) 41);
        userService.saveUser("Алексей", "Рубинкович", (byte) 41);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}