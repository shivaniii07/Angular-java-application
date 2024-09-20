package com.example.DummyProject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "users_shivani")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "last name cannot be empty")
    @Size(min = 2, max = 30, message = "last name must be between 2 and 30 characters")
    private String lastName;

    @Column(name = "email_id")
    @NotEmpty(message = "email cannot be empty")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@motherson\\.com$",
            message = "Email must end with '@motherson.com'"
    )
    private String emailId;

//    default constructor
    public User()
    {

    }
//    parameterized constructor
   public User(String firstName,String lastName,String emailId)
   {
       super();
       this.firstName=firstName;
       this.lastName=lastName;
       this.emailId=emailId;
   }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
