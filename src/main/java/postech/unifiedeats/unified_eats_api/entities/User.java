package postech.unifiedeats.unified_eats_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import postech.unifiedeats.unified_eats_api.enums.UserType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email"), @UniqueConstraint(columnNames = "login")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String login;
    private String password;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private UserType type;
}
