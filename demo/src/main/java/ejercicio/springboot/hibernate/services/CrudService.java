package ejercicio.springboot.hibernate.services;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

    T create(T entity);

    T update(T entity);

    T get(ID entity);

    boolean delete(ID entity);

    List<T> list();
}
