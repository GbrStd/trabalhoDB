package org.example.db;

import org.example.model.Arrival;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ArrivalDao {

    private static ArrivalDao instance = null;

    private ArrivalDao() {
    }

    public static synchronized ArrivalDao getInstance() {
        if (instance == null) {
            instance = new ArrivalDao();
        }
        return instance;
    }

    public Optional<Arrival> findById(int id) {
        try (Connection connection = MySQLConnect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tb_chegadas WHERE id = ?");

            statement.setInt(1, id);

            final ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next())
                return Optional.empty();

            Arrival arrival = new Arrival();
            arrival.setContinente(resultSet.getString("continente"));
            arrival.setCodContinente(resultSet.getInt("cod_continente"));
            arrival.setPais(resultSet.getString("pais"));
            arrival.setCodPais(resultSet.getInt("cod_pais"));
            arrival.setUf(resultSet.getString("uf"));
            arrival.setCodUf(resultSet.getInt("cod_uf"));
            arrival.setVia(resultSet.getString("via"));
            arrival.setCodVia(resultSet.getInt("cod_via"));
            arrival.setAno(resultSet.getInt("ano"));
            arrival.setMes(resultSet.getString("mes"));
            arrival.setCodMes(resultSet.getInt("cod_mes"));
            arrival.setChegadas(resultSet.getInt("chegadas"));

            return Optional.of(arrival);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean insert(Arrival arrival) {
        try (Connection connection = MySQLConnect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tb_chegadas (continente, cod_continente, pais, cod_pais, uf, cod_uf, via, cod_via, ano, mes, cod_mes, chegadas) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            statement.setString(1, arrival.getContinente());
            statement.setInt(2, arrival.getCodContinente());
            statement.setString(3, arrival.getPais());
            statement.setInt(4, arrival.getCodPais());
            statement.setString(5, arrival.getUf());
            statement.setInt(6, arrival.getCodUf());
            statement.setString(7, arrival.getVia());
            statement.setInt(8, arrival.getCodVia());
            statement.setInt(9, arrival.getAno());
            statement.setString(10, arrival.getMes());
            statement.setInt(11, arrival.getCodMes());
            statement.setInt(12, arrival.getChegadas());

            statement.execute();

            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean insert(Arrival[] arrivals) {
        try (Connection connection = MySQLConnect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tb_chegadas (continente, cod_continente, pais, cod_pais, uf, cod_uf, via, cod_via, ano, mes, cod_mes, chegadas) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            for (Arrival arrival : arrivals) {

                System.out.println("Inserindo " + arrival);

                statement.setString(1, arrival.getContinente());
                statement.setInt(2, arrival.getCodContinente());
                statement.setString(3, arrival.getPais());
                statement.setInt(4, arrival.getCodPais());
                statement.setString(5, arrival.getUf());
                statement.setInt(6, arrival.getCodUf());
                statement.setString(7, arrival.getVia());
                statement.setInt(8, arrival.getCodVia());
                statement.setInt(9, arrival.getAno());
                statement.setString(10, arrival.getMes());
                statement.setInt(11, arrival.getCodMes());
                statement.setInt(12, arrival.getChegadas());

                statement.executeUpdate();
            }

            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public boolean update(int id, Arrival arrival) {
        try (Connection connection = MySQLConnect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE tb_chegadas SET continente = ?, cod_continente = ?, pais = ?, cod_pais = ?, uf = ?, cod_uf = ?, via = ?, cod_via = ?, ano = ?, mes = ?, cod_mes = ?, chegadas = ? WHERE id = ?");

            statement.setString(1, arrival.getContinente());
            statement.setInt(2, arrival.getCodContinente());
            statement.setString(3, arrival.getPais());
            statement.setInt(4, arrival.getCodPais());
            statement.setString(5, arrival.getUf());
            statement.setInt(6, arrival.getCodUf());
            statement.setString(7, arrival.getVia());
            statement.setInt(8, arrival.getCodVia());
            statement.setInt(9, arrival.getAno());
            statement.setString(10, arrival.getMes());
            statement.setInt(11, arrival.getCodMes());
            statement.setInt(12, arrival.getChegadas());
            statement.setInt(13, id);

            statement.execute();

            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {
        try (Connection connection = MySQLConnect.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tb_chegadas WHERE id = ?");

            statement.setInt(1, id);

            statement.execute();

            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}