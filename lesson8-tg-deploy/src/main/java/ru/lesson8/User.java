package ru.lesson8;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Random;

@Entity
@Data
@Getter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Column(name = "name", unique = true)
    @Size(max=20)
    private String name;
    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    private Integer money;

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.password = name + "_pass";
        Random rand = new Random();
        this.money = rand.nextInt(1000);
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Integer getMoney() {
        return money;
    }

    public Long getId() {
        return id;
    }
}
