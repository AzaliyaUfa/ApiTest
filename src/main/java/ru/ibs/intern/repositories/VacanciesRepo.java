package ru.ibs.intern.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ibs.intern.entity.Area;
import ru.ibs.intern.entity.Vacancy;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

@Repository
public interface VacanciesRepo extends CrudRepository<Vacancy, Long> {

}
