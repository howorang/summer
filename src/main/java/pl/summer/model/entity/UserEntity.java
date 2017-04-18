package pl.summer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by Piotr Borczyk on 10.04.2017.
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {
    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private UserDetailsEntity userDetails;

    @OneToMany(mappedBy = "author")
    private List<EntryEntity> entries;
}
