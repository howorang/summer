package pl.summer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;
import java.util.Set;

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
    @JoinColumn(name = "USER_INFO_ID")
    private UserInfoEntity userInfo;

    @OneToMany(mappedBy = "author")
    private List<EntryEntity> entries;

    @Singular
    @ManyToMany
    private List<RoleEntity> roles;

    @Singular
    @ElementCollection
    private Set<Long> upvotedEntries;

}