package payroll;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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

  private final EmployeeModelAssembler assembler;

  EmployeeController(EmployeeRepository repo, EmployeeModelAssembler assembler) {
    this.repo = repo;
    this.assembler = assembler;
  }

  @GetMapping("/employees")
  CollectionModel<EntityModel<Employee>> all() {

    List<EntityModel<Employee>> employees = repo.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  }

  // end::get-aggregate-root[]

  @PostMapping("/employees")
  Employee newEmployee(@RequestBody Employee newEmployee) {
    return repo.save(newEmployee);
  }

  @GetMapping("/employees/{id}")
  EntityModel<Employee> one(@PathVariable Long id) {

    Employee employee = repo.findById(id)
        .orElseThrow(() -> new EmployeeNotFoundException(id));

    return assembler.toModel(employee);
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
