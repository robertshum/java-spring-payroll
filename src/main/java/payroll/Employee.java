package payroll;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee {

  private @Id @GeneratedValue Long id;
  private String firstName;
  private String lastName;

  // @Deprecated
  // private String name;

  private String role;

  Employee() {
  };

  Employee(String firstName, String lastName, String role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.firstName + " " + this.lastName;
  }

  public String getRole() {
    return this.role;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    String[] parts = name.split(" ");
    this.firstName = parts[0];
    this.lastName = parts[1];
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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
    boolean isNameMatch = Objects.equals(this.firstName, employee.getFirstName())
        && Objects.equals(this.lastName, employee.getLastName());
    boolean isRoleMatch = Objects.equals(this.role, employee.getRole());

    return isIdMatch && isNameMatch && isRoleMatch;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.firstName, this.lastName, this.role);
  }

  @Override
  public String toString() {
    return "Employee{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName
        + '\'' + ", role='" + this.role + '\'' + '}';
  }
}
