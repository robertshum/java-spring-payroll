package payroll;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee {

  private @Id @GeneratedValue Long id;
  private String name;
  private String role;

  Employee() {
  };

  Employee(String name, String role) {
    this.name = name;
    this.role = role;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getRole() {
    return this.role;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Employee)) {
      return false;
    }

    Employee employee = (Employee) obj;

    // Check ID, role and name must match
    boolean isIdMatch = Objects.equals(this.id, employee.getId());
    boolean isNameMatch = Objects.equals(this.name, employee.getName());
    boolean isRoleMatch = Objects.equals(this.role, employee.getRole());

    return isIdMatch && isNameMatch && isRoleMatch;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.name, this.role);
  }

  @Override
  public String toString() {
    return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
  }
}
