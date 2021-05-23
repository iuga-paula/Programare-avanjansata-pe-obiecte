package Repo;

import Localuri.Sofer;

import java.sql.*;
import java.util.*;

public class Soferi_Repo {

    //CRUD pe Localuri.Sofer in bd

    private final static String insertSoferSql = "INSERT INTO Soferi(nume, prenume, tipContract, salariu, telefon) VALUES(?, ?, ?, ?, ?)";

    private final static Connection connection = Bd.Configurare_Bd.getDatabaseConnection();

    public static void insert(Sofer sofer) { // CREATE - insereaza campurile din ob sofer in bd
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSoferSql);
            preparedStatement.setString(1, sofer.getNume());
            preparedStatement.setString(2, sofer.getPrenume());
            preparedStatement.setString(3, sofer.getTipContract());
            preparedStatement.setDouble(4, sofer.getSalariu());
            preparedStatement.setString(5, sofer.getNrTelefon());
            preparedStatement.executeUpdate();
        } catch (SQLException ignore)    { //mai exista sofer cu acest nume

        }

    }

    public static void insert2(String nume, String prenume, String tipContract, Double salariu, String telefon) { // CREATE - insereaza campurile pentru sofer in bd
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSoferSql);
            preparedStatement.setString(1, nume);
            preparedStatement.setString(2, prenume);
            preparedStatement.setString(3, tipContract);
            preparedStatement.setDouble(4, salariu);
            preparedStatement.setString(5,telefon);
            preparedStatement.executeUpdate();
        } catch (SQLException ignore)    {//mai exista sofer cu acest nume

        }

    }

    public static Optional<Sofer> getSoferbyId(int id) { //READ - afiseaza soferul cu id -ul x
        String selectSql = "SELECT * FROM Soferi s WHERE s.id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToSofer(resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static Optional<Sofer> mapToSofer(ResultSet resultSet) throws SQLException {
        if(resultSet.next()){
            String nume = resultSet.getString(2);
            String prenume = resultSet.getString(3);
            String tipContract = resultSet.getString(4);
            double salariu = resultSet.getDouble(5);
            String telefon = resultSet.getString(6);
            return Optional.of(new Sofer(nume, prenume, tipContract, salariu, telefon));
        }
        return Optional.empty();
    }

    public static Optional<Sofer> getSoferbyName(String nume, String prenume){ //READ - afiseaza soferul/soferii cu numele x si prenumele y
        String selectSql = "SELECT * FROM Soferi s WHERE s.nume = ? and s.prenume = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, nume);
            preparedStatement.setString(2, prenume);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToSofer(resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();

    }

    public static Set<Sofer> get_soferi(){
        String selectSql = "SELECT * FROM Soferi";
        Set<Sofer> soferi = new HashSet<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                String nume = resultSet.getString(2);
                String prenume = resultSet.getString(3);
                String tipContract = resultSet.getString(4);
                double salariu = resultSet.getDouble(5);
                String telefon = resultSet.getString(6);
                soferi.add(new Sofer(nume, prenume, tipContract, salariu, telefon));

            }
        }
        catch (SQLException e){
            e.printStackTrace();

        }
        return  soferi;

    }

    public static void deleteSofer(String nume, String prenume){ //DELETE - sterge un sofer dupa nume;
        String selectSql = "DELETE FROM Soferi s WHERE s.nume = ? and s.prenume = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, nume);
            preparedStatement.setString(2, prenume);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void updateNumeSofer(String nume, String prenume, String nume_nou){ //UPDATE
        String selectSql = "UPDATE Soferi s SET s.nume = ? WHERE s.nume = ? and s.prenume = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, nume_nou);
            preparedStatement.setString(2, nume);
            preparedStatement.setString(3, prenume);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void updatePrenumeSofer(String nume, String prenume, String prenume_nou){ //UPDATE
        String selectSql = "UPDATE Soferi s SET s.prenume = ? WHERE s.nume = ? and s.prenume = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, prenume_nou);
            preparedStatement.setString(2, nume);
            preparedStatement.setString(3, prenume);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void updateSalariuSofer(String nume, String prenume, Double salariu_nou){ //UPDATE
        String selectSql = "UPDATE Soferi s SET s.salariu = ? WHERE s.nume = ? and s.prenume = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setDouble(1, salariu_nou);
            preparedStatement.setString(2, nume);
            preparedStatement.setString(3, prenume);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateContractSofer(String nume, String prenume, String contract_nou){ //UPDATE
        String selectSql = "UPDATE Soferi s SET s.tipContract = ? WHERE s.nume = ? and s.prenume = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, contract_nou);
            preparedStatement.setString(2, nume);
            preparedStatement.setString(3, prenume);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void updateTelefonSofer(String nume, String prenume, String telefon_nou){ //UPDATE
        String selectSql = "UPDATE Soferi s SET s.telefon = ? WHERE s.nume = ? and s.prenume = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, telefon_nou);
            preparedStatement.setString(2, nume);
            preparedStatement.setString(3, prenume);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static Integer getidSofer(String nume, String prenume){
        String selectSql = "SELECT id FROM Soferi s WHERE s.nume = ? AND s.prenume = ?";
        Integer id = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, nume);
            preparedStatement.setString(2, prenume);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
            id = resultSet.getInt(1);}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;
    }
}
