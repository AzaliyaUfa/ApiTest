package ru.ibs.intern.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ibs.intern.entity.Vacancy;

import java.util.List;

@Repository
public interface VacanciesRepo extends CrudRepository<Vacancy, Long> {

    List<Vacancy> findVacanciesByAreaName(String areaName);


}
