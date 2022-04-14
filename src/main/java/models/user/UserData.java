package models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import utils.annotation.Id;

@Data
@AllArgsConstructor
public class UserData {
    @Id
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
