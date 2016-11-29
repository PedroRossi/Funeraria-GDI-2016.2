package repositories;

import models.Cliente;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pedro on 29/11/16.
 */
public class RepositoryTest {

    public static void main(String[] args) {

        List<Cliente> clientes = new ArrayList<Cliente>();
        ClientesRepository repositorio = new ClientesRepository();
        try {
            clientes = repositorio.getAll();
            System.out.println("EXECUTANDO LISTAGEM TOTAL");
            for(Cliente cliente: clientes) {
                System.out.println(cliente.getPrimeiroNome() + " " + cliente.getCpf());
            }
            System.out.println("EXECUTANDO INSERÇÃO");
            Date data = new Date(System.currentTimeMillis());
            Cliente a = new Cliente("12345678900", "Jovanildo", "Soares", data, "47586900", new ArrayList<>());
            repositorio.insert(a);
            System.out.println("EXECUTANDO BUSCA POR UM SOMENTE");
            Cliente c = repositorio.get("12345678900");
            System.out.println(c.getPrimeiroNome() + " " + c.getCpf());
            System.out.println("EXECUTANDO ATUALIZAÇÃO");
            c.setNomeFamilia("Orivaldo");
            repositorio.update(c);
            c = repositorio.get("12345678900");
            System.out.println(c.getPrimeiroNome() + " " + c.getNomeFamilia());
            System.out.println("EXECUTANDO REMOÇÃO");
            repositorio.remove(c);
            clientes = repositorio.getAll();
            for(Cliente cliente: clientes) {
                System.out.println(cliente.getPrimeiroNome() + " " + cliente.getCpf());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
