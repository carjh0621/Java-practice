package ticket.interfaces;

import java.util.List;
import java.util.Optional;

public interface Repository <T, ID>{
    void save(T entity);

    // null 대신 Optional
    Optional<T> findById(ID id);

    List<T> findAll();

    void delete(ID id);
}
