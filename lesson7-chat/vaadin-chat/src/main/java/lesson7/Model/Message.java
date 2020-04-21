package lesson7.Model;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "message_entity")
@SuppressWarnings("unused")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty("account id")
    private Long id;
    @NotNull
    @Column(name = "content", unique = true)
    @ApiModelProperty("unique account number")
    private String content;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
