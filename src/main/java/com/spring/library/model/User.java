package com.spring.library.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import lombok.Data;
import com.spring.library.model.Role;

import java.util.ArrayList;
import java.util.List;

@Data
@Table(name="users")
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "100"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private long id;

    @Email
    @NotNull
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(name="first_name", nullable = false)
    @Pattern(regexp = "([A-Z][a-z]*)([\\s\\'-][A-Z][a-z]*)*")
    private String firstName;

    @NotBlank
    @Column(name="last_name", nullable = false)
    @Pattern(regexp = "([A-Z][a-z]*)([\\s\\'-][A-Z][a-z]*)*")
    private String lastName;


    @Pattern(regexp = "^(?=(.*[a-z]){2})(?=(.*[A-Z]){2})(?=(.*[0-9]){2})(?=(.*[!@_$#%&]){2})[\\w!@$#%&]*$")
    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id", nullable=false)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id",nullable = false)
    private List<Book> books= new ArrayList<>(); ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (id != user.id) return false;
        if (!email.equals(user.email)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!password.equals(user.password)) return false;
        if (!role.equals(user.role)) return false;
        return books.equals(user.books);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + email.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + books.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", books=" + books +
                '}';
    }
}
