package pl.summer.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.summer.model.entity.CommentEntity;

/**
 * Created by Piotr Borczyk on 20.06.2017.
 */

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long>{

    CommentEntity findById(long id);

}
