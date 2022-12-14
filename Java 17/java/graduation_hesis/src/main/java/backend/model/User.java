package backend.model;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String userName;
    private String email;
    private String password;
    private String phone;
    private Address address;
    private Order order;

    public User(String userName, String email, String password, String phone, Address address) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
}
