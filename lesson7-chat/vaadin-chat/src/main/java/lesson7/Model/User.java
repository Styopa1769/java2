package lesson7.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@SqlResultSetMapping(
        name = "userMapping",
        classes = @ConstructorResult(
                targetClass = lesson7.Dto.UserDto.class,
                columns = {
                        @ColumnResult(name = "id",type=Long.class),
                        @ColumnResult(name = "name",type=String.class)
                }
        )
)

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "user_entity")
@SuppressWarnings("unused")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @ApiModelProperty("user id")
    private Long id;
    @NotNull
    @Column(name = "name")
    @Size(max = 30)
    @ApiModelProperty("user name")
    private String name;
    @NotNull
    @Column(name = "password_hash")
    @Size(max = 60)
    @ApiModelProperty("hash of password")
    @JsonIgnore
    private String password_hash;
    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Account> accounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
}
