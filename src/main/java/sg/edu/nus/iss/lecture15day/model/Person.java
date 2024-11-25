package sg.edu.nus.iss.lecture15day.model;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Person {

  
  private String Id;

  @NotBlank
  @Size(min=3, max = 100, message="Min 3 & Max 100 characters, Name is mandatory")
  private String fullName;

  @NotBlank
  @Email(message="Email is mandatory")
  private String email;

  @DateTimeFormat(pattern = "YYYY-MM-DD")
  @Past(message = "LocalDate of Birth is mandatory & must be in past" )
  private LocalDate dateofBirth;

  public Person() {
  }
  public Person(String Id, String fullName, String email, LocalDate dateofBirth) {
    this.Id = Id;
    this.fullName = fullName;
    this.email = email;
    this.dateofBirth = dateofBirth;
}
  public Person(String fullName, String email, LocalDate dateofBirth) {
    this.Id = UUID.randomUUID().toString().substring(0, 4);
    this.fullName = fullName;
    this.email = email;
    this.dateofBirth = dateofBirth;
  }

  public String getId() {
    return Id;
  }

  public void setId(String id) {
    Id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDateofBirth() {
    return dateofBirth;
  }

  public void setDateofBirth(LocalDate dateofBirth) {
    this.dateofBirth = dateofBirth;
  }


  
}
