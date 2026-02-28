package chmihun.service;

import chmihun.config.AppConfig;
import chmihun.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // 1. Инициализируем контекст Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Получаем бин сервиса
        UserService userService = context.getBean(UserService.class);

        // 3. Создаем тестовых пользователей
        User user1 = new User();
        user1.setName("Ivan");
        user1.setSurname("Ivanov");
        user1.setAge(25);

        User user2 = new User();
        user2.setName("Petr");
        user2.setSurname("Petrov");
        user2.setAge(30);

        // 4. Сохраняем в базу данных
        userService.add(user1);
        userService.add(user2);

        // 5. Исправлено: Получаем список напрямую из сервиса
        List<User> users = userService.listUsers();

        for (User user : users) {
            System.out.println("------------------------");
            System.out.println("Id: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Surname: " + user.getSurname());
            System.out.println("Age: " + user.getAge());
        }

        // 6. Закрываем контекст
        context.close();
    }
}
