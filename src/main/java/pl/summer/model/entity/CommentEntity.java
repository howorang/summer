package pl.summer.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Piotr Borczyk on 18.06.2017.
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity extends BaseEntity {

    @ManyToOne
    private UserEntity author;

    private String content;

    @ManyToOne
    private EntryEntity entry;

}
