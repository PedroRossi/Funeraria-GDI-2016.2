package repositories;

import factories.Database;
import models.Cliente;
import models.Nome;
import models.Telefone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedro on 28/11/16.
 */
public class ClientesRepository implements Repository<Cliente> {

    @Override
    public boolean exists(Cliente cliente) throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM Cliente WHERE cpf_cliente = ?");
        statement.setString(1, cliente.getCpf());
        ResultSet resultSet = statement.executeQuery();
        boolean exists = resultSet.next();
        conn.commit();
        resultSet.close();
        statement.close();
        return exists;
    }

    @Override
    public void insert(Cliente cliente) throws SQLException {
        if (!exists(cliente)) {
            Connection conn = Database.getConnection();
            PreparedStatement insertPessoa = conn.prepareStatement(
                    "INSERT INTO Pessoa (cpf, prim_nome, nome_familia, data_nasc, rg) VALUES (?, ?, ?, ?, ?)"
            );
            insertPessoa.setString(1, cliente.getCpf());
            insertPessoa.setString(2, cliente.getPrimeiroNome());
            insertPessoa.setString(3, cliente.getNomeFamilia());
            insertPessoa.setDate(4, cliente.getNascimento());
            insertPessoa.setString(5, cliente.getRg());
            insertPessoa.executeUpdate();
            conn.commit();
            insertPessoa.close();
            PreparedStatement insertCliente = conn.prepareStatement(
                    "INSERT INTO Cliente (cpf_cliente) VALUES (?)"
            );
            insertCliente.setString(1, cliente.getCpf());
            insertCliente.executeUpdate();
            conn.commit();
            insertCliente.close();
            for(Telefone telefone: cliente.getTelefones()) {
                PreparedStatement p = conn.prepareStatement(
                        "INSERT INTO Telefones (numero, cpf_pessoa) VALUES (?, ?)"
                );
                p.setString(1, telefone.getNumero());
                p.setString(2, cliente.getCpf());
                p.executeUpdate();
                conn.commit();
                p.close();
            }
        } else {
            throw new SQLException("Cliente já existe!");
        }
    }

    @Override
    public void update(Cliente cliente) throws SQLException {
        if (exists(cliente)) {
            Connection conn = Database.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE Pessoa SET prim_nome = ?, nome_familia = ?, data_nasc = ?, rg = ? WHERE cpf = ?"
            );
            statement.setString(1, cliente.getPrimeiroNome());
            statement.setString(2, cliente.getNomeFamilia());
            statement.setDate(3, cliente.getNascimento());
            statement.setString(4, cliente.getRg());
            statement.setString(5, cliente.getCpf());
            statement.executeUpdate();
            conn.commit();
            statement.close();
        } else
            throw new SQLException("Cliente não existe!");
    }

    @Override
    public void remove(Cliente cliente) throws SQLException {
        if (exists(cliente)) {
            Connection conn = Database.getConnection();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM CLIENTE WHERE CPF_CLIENTE = ?");
            statement.setString(1, cliente.getCpf());
            statement.executeUpdate();
            conn.commit();
            statement.close();
        } else
            throw new SQLException("Cliente não existe!");
    }

    @Override
    public List<Cliente> getAll() throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM CLIENTE");
        ResultSet rs = statement.executeQuery();
        List<Cliente> list = new ArrayList<>();
        while (rs.next()) list.add(get(rs.getString(1)));
        return list;
    }

    @Override
    public Cliente get(String cpf) throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "SELECT * FROM Pessoa p, Cliente c WHERE p.cpf = ? AND p.cpf = c.cpf_cliente"
        );
        statement.setString(1, cpf);
        PreparedStatement telefonesStatement = conn.prepareStatement(
                "SELECT * FROM TELEFONES WHERE CPF_PESSOA = ?"
        );
        telefonesStatement.setString(1, cpf);
        ResultSet rs = statement.executeQuery();
        ResultSet telRs = telefonesStatement.executeQuery();
        List<Telefone> telefones = new ArrayList<>();
        while(telRs.next()) {
            telefones.add(new Telefone(telRs.getString(1)));
        }
        if (rs.next()) {
            Cliente c = new Cliente(cpf,rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),telefones);
            statement.close();
            telefonesStatement.close();
            return c;
        } else throw new SQLException("Cliente Inexistente!");
    }
}
