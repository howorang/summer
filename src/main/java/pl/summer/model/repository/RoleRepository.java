package pl.summer.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.summer.model.entity.RoleEntity;

/**
 * Created by Piotr Borczyk on 25.04.2017.
 */

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
}
