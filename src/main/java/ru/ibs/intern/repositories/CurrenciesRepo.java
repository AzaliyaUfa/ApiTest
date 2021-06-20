package ru.ibs.intern.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ibs.intern.entity.Currency;

import java.util.Map;

@Repository
public interface CurrenciesRepo extends CrudRepository<Currency, Long> {

}
