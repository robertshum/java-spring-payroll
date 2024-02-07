package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {

    return args -> {
      // Employees
      log.info("Preloading " + employeeRepository.save(new Employee("Robert", "Shum", "Software Developer")));

      log.info("Preloading " + employeeRepository.save(new Employee("George", "Washington", "President")));

      employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));

      // Orders
      orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
      orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

      orderRepository.findAll().forEach(order -> {
        log.info("Preloaded " + order);
      });
    };
  }
}
