package de.telran.lesson3.domain_layer.entity.jpa;

import de.telran.lesson3.domain_layer.entity.Cart;
import de.telran.lesson3.domain_layer.entity.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.extern.java.Log;
/*
1. Добавить покупателю два дополнительных поля - возраст и емейл (и в БД тоже).
2. Провалидировать все поля покупателя.
3. Подумать, какие нештатные ситуации могут возникать при работе обоих сервисов,
   создать для них соответствующие эксепшены.
4. Выбросить эти эксепшены в нужных местах, поймать в контроллерах и обработать при помощи адвайса.
 */

@Entity
@Table(name = "customer")
@Getter
public class JpaCustomer implements Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @Pattern(regexp = "[A-Z][a-z]{2,}")
    private String name;
    @Pattern(regexp = "[A-Z][a-z]{2,}")
    @Column(name = "age")
    @Min(value = 16)
    private int age;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "customer")
    private JpaCart cart;

    public JpaCustomer() {
    }

    public JpaCustomer(int id, String name, int age, String email, JpaCart cart) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.cart = cart;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getAge(){
        return age;
    }
    @Override
    public String getEmail(){
        return email;
    }


    @Override
    public Cart getCart() {
        return cart;
    }
}