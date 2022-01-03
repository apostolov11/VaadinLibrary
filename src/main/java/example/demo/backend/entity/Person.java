package example.demo.backend.entity;

import example.demo.backend.entity.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Person extends BaseEntity {

    @NotEmpty
    @Column(name = "first_name",nullable = false)
    private String firstName;


    @NotEmpty
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Email
    @NotEmpty
    @Column(name = "email",nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column
    private Status status;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email) && status == person.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, status);
    }

    public Person() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}
