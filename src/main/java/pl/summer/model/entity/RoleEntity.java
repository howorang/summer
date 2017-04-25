package pl.summer.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Set;

/**
 * Created by Piotr Borczyk on 25.04.2017.
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleEntity extends BaseEntity {

    private String roleName;

    @Singular
    @ElementCollection
    private Set<String> privileges;
}
