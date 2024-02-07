package payroll;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

  private final EmployeeRepository repo;

  EmployeeController(EmployeeRepository repo) {
    this.repo = repo;
  }

  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/employees")
  List<Employee> all() {
    return repo.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repo.save(newEmployee);
  }

  // Find 1 item
  @GetMapping("/employees/{id}")
  Employee one(@PathVariable Long id) {
    return repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  // Update 1 item
  @PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
    return repo.findById(id)
        .map(employee -> {
          employee.setName(newEmployee.getName());
          employee.setRole(newEmployee.getRole());
          return repo.save(employee);
        })
        .orElseGet(() -> {
          newEmployee.setId(id);
          return repo.save(newEmployee);
        });
  }

  @DeleteMapping("/employees/{id}")
  void deleteEmployee(@PathVariable Long id) {
    repo.deleteById(id);
  }
}
