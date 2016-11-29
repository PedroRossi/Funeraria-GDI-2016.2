package repositories;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pedro on 28/11/16.
 */
public interface Repository<E> {

    boolean exists(E e) throws SQLException;

    void insert(E e) throws SQLException;

    void update(E e) throws SQLException;

    void remove(E e) throws SQLException;

    List<E> getAll() throws SQLException;

    E get(String id) throws SQLException;

}
