package repositories;

import factories.Database;
import models.Cliente;
import models.Funcionario;
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
public class FuncionarioRepository implements Repository<Funcionario> {

    @Override
    public boolean exists(Funcionario funcionario) throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM Funcionario WHERE cpf_func = ?;");
        statement.setString(1, funcionario.getCpf());
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    @Override
    public void insert(Funcionario funcionario) throws SQLException {
        if (!exists(funcionario)) {
            Connection conn = Database.getConnection();
            PreparedStatement insertPessoa = conn.prepareStatement(
                    "INSERT INTO Pessoa (cpf, prim_nome, nome_familia, data_nasc, rg) VALUES (?, ?, ?, ?, ?);"
            );
            insertPessoa.setString(1, funcionario.getCpf());
            insertPessoa.setString(2, funcionario.getPrimeiroNome());
            insertPessoa.setString(3, funcionario.getNomeFamilia());
            insertPessoa.setDate(4, funcionario.getNascimento());
            insertPessoa.setString(5, funcionario.getRg());
            insertPessoa.executeUpdate();
            PreparedStatement insertFuncionario = conn.prepareStatement(
                    "INSERT INTO Funcionario (cpf_func, cpf_supv) VALUES (?, ?);"
            );
            insertFuncionario.setString(1, funcionario.getCpf());
            insertFuncionario.setString(2, funcionario.getCpfSupervisor());
            insertFuncionario.executeUpdate();
            for(Telefone telefone: funcionario.getTelefones()) {
                PreparedStatement p = conn.prepareStatement(
                        "INSERT INTO Telefones (numero, cpf_pessoa) VALUES (?, ?);"
                );
                p.setString(1, telefone.getNumero());
                p.setString(2, funcionario.getCpf());
                p.executeUpdate();
            }
        } else
            throw new SQLException("Funcionário já existe!");
    }

    @Override
    public void update(Funcionario funcionario) throws SQLException {
        if (exists(funcionario)) {
            Connection conn = Database.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "UPDATE"
            );
            statement.executeUpdate();
        } else
            throw new SQLException("Funcionário não existe!");
    }

    @Override
    public void remove(Funcionario funcionario) throws SQLException {
        if (exists(funcionario)) {
            Connection conn = Database.getConnection();
            PreparedStatement statement = conn.prepareStatement("DELETE FROM Funcionario WHERE cpf_func = ?;");
            statement.setString(1, funcionario.getCpf());
            statement.executeUpdate();
        } else
            throw new SQLException("Funcionário não existe!");
    }

    @Override
    public List<Funcionario> getAll() throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM Funcionario;");
        ResultSet rs = statement.executeQuery();
        List<Funcionario> list = new ArrayList<>();
        while (rs.next())
            list.add(get(rs.getString(1)));
        return list;
    }

    @Override
    public Funcionario get(String cpf) throws SQLException {
        Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement(
                "SELECT P.CPF, P.PRIM_NOME, P.NOME_FAMILIA, P.DATA_NASC, P.RG, R.SALARIO, R.FUNCAO, F.CPF_SUPV FROM PESSOA p, FUNCIONARIO f, REMUNERACAO R, CARGO C WHERE C.CPF_FUNC = F.CPF_FUNC AND C.FUNCAO = R.FUNCAO AND C.CPF_FUNC = ?;"
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
            return new Funcionario(
                    cpf,
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getString(5),
                    telefones,
                    rs.getDouble(6),
                    rs.getString(7),
                    rs.getString(8)
            );
        } else throw new SQLException("Funcionário Inexistente!");
    }
}


