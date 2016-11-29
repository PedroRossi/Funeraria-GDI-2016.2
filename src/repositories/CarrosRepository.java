package repositories;

import models.Carro;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pedro on 29/11/16.
 */
public class CarrosRepository implements Repository<Carro> {

    @Override
    public boolean exists(Carro carro) throws SQLException {
        return false;
    }

    @Override
    public void insert(Carro carro) throws SQLException {

    }

    @Override
    public void update(Carro carro) throws SQLException {

    }

    @Override
    public void remove(Carro carro) throws SQLException {

    }

    @Override
    public List<Carro> getAll() throws SQLException {
        return null;
    }

    @Override
    public Carro get(String id) throws SQLException {
        return null;
    }
}
