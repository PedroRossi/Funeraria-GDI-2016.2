package repositories;

import factories.Database;
import models.Cliente;
import models.Motorista;
import models.Telefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedro on 29/11/16.
 */
public class MotoristaRepository implements Repository<Motorista> {

    @Override
    public boolean exists(Motorista motorista) throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM Motorista WHERE cpf_mot = ?;");
        statement.setString(1, motorista.getCpf());
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    @Override
    public void insert(Motorista motorista) throws SQLException {
        if (!exists(motorista)) {
            Connection conn = Database.getConnection();
            PreparedStatement insertPessoa = conn.prepareStatement(
                    "INSERT INTO Pessoa (cpf, prim_nome, nome_familia, data_nasc, rg) VALUES (?, ?, ?, ?, ?);"
            );
            insertPessoa.setString(1, motorista.getCpf());
            insertPessoa.setString(2, motorista.getPrimeiroNome());
            insertPessoa.setString(3, motorista.getNomeFamilia());
            insertPessoa.setDate(4, motorista.getNascimento());
            insertPessoa.setString(5, motorista.getRg());
            insertPessoa.executeUpdate();
            PreparedStatement insertMotorista = conn.prepareStatement(
                    "INSERT INTO Motorista (cpf_mot, cnh) VALUES (?, ?);"
            );
            insertMotorista.setString(1, motorista.getCpf());
            insertMotorista.setString(2, motorista.getCnh());
            insertMotorista.executeUpdate();
            for(Telefone telefone: motorista.getTelefones()) {
                PreparedStatement p = conn.prepareStatement(
                        "INSERT INTO Telefones (numero, cpf_pessoa) VALUES (?, ?);"
                );
                p.setString(1, telefone.getNumero());
                p.setString(2, motorista.getCpf());
                p.executeUpdate();
            }
        } else
            throw new SQLException("Motorista já existe!");
    }

    @Override
    public void update(Motorista motorista) throws SQLException {
        if (exists(motorista)) {
            Connection conn = Database.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE Pessoa SET prim_nome = ?, nome_familia = ?, data_nasc = ?, rg = ? WHERE cpf = ?;"
            );
            statement.setString(1, motorista.getPrimeiroNome());
            statement.setString(2, motorista.getNomeFamilia());
            statement.setDate(3, motorista.getNascimento());
            statement.setString(4, motorista.getRg());
            statement.setString(5, motorista.getCpf());
            statement.executeUpdate();
        } else
            throw new SQLException("Cliente não existe!");
    }

    @Override
    public void remove(Motorista motorista) throws SQLException {
        if (exists(motorista)) {
            Connection conn = Database.getConnection();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM Motorista WHERE cpf_mot = ?;");
            statement.setString(1, motorista.getCpf());
            statement.executeUpdate();
        } else
            throw new SQLException("Motorista não existe!");
    }

    @Override
    public List<Motorista> getAll() throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM motorista;");
        ResultSet rs = statement.executeQuery();
        List<Motorista> list = new ArrayList<>();
        while (rs.next())
            list.add(get(rs.getString(1)));
        return list;
    }

    @Override
    public Motorista get(String cpf) throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "SELECT P.CPF, P.PRIM_NOME, P.NOME_FAMILIA, P.DATA_NASC, P.RG, R.SALARIO, m.CNH FROM Pessoa p, Motorista m WHERE p.cpf = ? AND p.cpf = m.cpf_mot;"
        );
        statement.setString(1, cpf);
        PreparedStatement telefonesStatement = conn.prepareStatement(
                "SELECT * FROM TELEFONES WHERE CPF_PESSOA = ?;"
        );
        telefonesStatement.setString(1, cpf);
        ResultSet rs = statement.executeQuery();
        ResultSet telRs = telefonesStatement.executeQuery();
        List<Telefone> telefones = new ArrayList<>();
        while(telRs.next()) {
            telefones.add(new Telefone(telRs.getString(1)));
        }
        if (rs.next()) {
            return new Motorista(
                    cpf,
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getString(5),
                    telefones,
                    rs.getString(6)
            );
        } else throw new SQLException("Motorista Inexistente!");
    }
}
