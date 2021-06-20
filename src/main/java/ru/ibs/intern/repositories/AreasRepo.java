package ru.ibs.intern.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ibs.intern.entity.Area;

@Repository
public interface AreasRepo extends CrudRepository<Area, Long> {
}
