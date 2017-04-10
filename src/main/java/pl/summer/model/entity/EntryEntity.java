package pl.summer.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by Piotr Borczyk on 10.04.2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
public class EntryEntity extends BaseEntity {

    private String title;

    private String content;

    @ManyToOne
    private UserEntity author;

    @ElementCollection
    private List<String> hashTags;

}
