package co.istad.ite2_mbanking_api_v2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String uuid;
    @Column(unique = true, nullable = false)
    private String nationalCardId;
    @Column(nullable = false)
    private Integer pin;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, length = 50)
    private String name;
    private String profileImage;
    @Column(length = 8)
    private String gender;
    private LocalDate dob;
    @Column(length = 100)
    private String cityOrProvince;
    @Column(length = 100)
    private String khanOrDistrict;
    @Column(length = 100)
    private String village;
    @Column(length = 100)
    private String street;
    @Column(length = 50)
    private String employeeType;
    @Column(length = 50)
    private String position;
    @Column(length = 50)
    private String companyName;
    @Column(length = 100)
    private String mainSourceOfIncome;
    private BigDecimal monthlyIncomeRange;
    @Column(unique = true)
    private String oneSignalId;
    @Column(unique = true)
    private String studentIdCard;

    @OneToMany
    private List<UserAccount> userAccountList;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    private Boolean isDeleted;
    private Boolean isBlocked;
    private LocalDateTime createdAt;

}
