package ru.ibs.intern.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ibs.intern.entity.Area;

@Repository
public interface AreasRepo extends JpaRepository<Area, Long> {

    Area findByAreaName(String name);
}
