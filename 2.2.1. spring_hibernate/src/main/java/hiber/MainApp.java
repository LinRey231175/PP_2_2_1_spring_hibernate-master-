package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      User Alexey = new User("User1", "Lastname1", "user1@mail.ru");
      User Andrey = new User("User2", "Lastname2", "user2@mail.ru");
      User Maxim = new User("User3", "Lastname3", "user3@mail.ru");
      User Oleg = new User("User4", "Lastname4", "user4@mail.ru");

      Car Toyota = new Car("Toyota", 7);
      Car Audi = new Car("Audi", 3);
      Car Porsche = new Car("Porsche", 1);
      Car Nissan = new Car("Nissan", 13);

      userService.add(Alexey.setCar(Toyota).setUser(Alexey));
      userService.add(Andrey.setCar(Audi).setUser(Andrey));
      userService.add(Maxim.setCar(Porsche).setUser(Maxim));
      userService.add(Oleg.setCar(Nissan).setUser(Oleg));



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      User user = userService.getUserByCar("Toyota", 7);
      System.out.println(user.toString());

      try {
         User notFoundUser = userService.getUserByCar("Lada", 9);
      } catch (NoResultException e) {
      }
      context.close();
   }
}
