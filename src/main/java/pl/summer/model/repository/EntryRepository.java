package pl.summer.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.summer.model.entity.EntryEntity;

/**
 * Created by howor on 14.04.2017.
 */

@Repository
public interface EntryRepository extends JpaRepository<EntryEntity, Long>, QueryDslPredicateExecutor<EntryEntity> {
}
