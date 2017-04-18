package pl.summer.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by howor on 15.04.2017.
 */

@Data
public class UserRegistrationDto {
    private String username;
    private String password;
    private String matchingPassword;
}
