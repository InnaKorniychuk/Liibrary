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
    private List<Book> books;
}
