package pl.summer.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by Piotr Borczyk on 10.04.2017.
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    private String login;

    private String password;

    @OneToOne
    private UserDetailsEntity userDetails;
}
