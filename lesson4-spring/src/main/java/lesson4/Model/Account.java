package lesson4.Model;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty("account id")
    private Long id;
    @NotNull
    @Column(name = "number", unique = true)
    @ApiModelProperty("unique account number")
    private String number;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
