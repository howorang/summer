package pl.summer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Created by Piotr Borczyk on 10.04.2017.
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserInfoEntity extends BaseEntity {

    private String description;

    @Lob
    private byte[] avatar;
}
