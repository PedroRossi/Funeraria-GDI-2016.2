package repositories;

import models.Falecido;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pedro on 29/11/16.
 */
public class FalecidosRepository implements Repository<Falecido> {
    @Override
    public boolean exists(Falecido falecido) throws SQLException {
        return false;
    }

    @Override
    public void insert(Falecido falecido) throws SQLException {

    }

    @Override
    public void update(Falecido falecido) throws SQLException {

    }

    @Override
    public void remove(Falecido falecido) throws SQLException {

    }

    @Override
    public List<Falecido> getAll() throws SQLException {
        return null;
    }

    @Override
    public Falecido get(String id) throws SQLException {
        return null;
    }
}
