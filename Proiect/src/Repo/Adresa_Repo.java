package Repo;

import Localuri.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Adresa_Repo {
    //CRUD pe Localuri.Adresa in bd

    private final static Connection connection = Bd.Configurare_Bd.getDatabaseConnection();
    private final static String insertSoferSql = "INSERT INTO Adrese(strada, nr, bloc, scara, apartament, localitate, judet, sector, codPostal) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static void insert(Adresa adresa) { // CREATE - insereaza campurile din ob adresa in bd

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSoferSql);
            preparedStatement.setString(1, adresa.getStrada());
            preparedStatement.setInt(2, adresa.getNr());
            if(adresa.getBloc() == null){
                preparedStatement.setNull(3, Types.INTEGER);

            }
            else{
                preparedStatement.setInt(3, adresa.getBloc());}

            if(adresa.getScara() == null){
                preparedStatement.setNull(4, Types.VARCHAR);

            }
            else{
                preparedStatement.setString(4, adresa.getScara());
            }
            if (adresa.getApartament() == null){
                preparedStatement.setNull(5, Types.INTEGER);
            }
            else{
                preparedStatement.setInt(5, adresa.getApartament());
            }
            preparedStatement.setString(6, adresa.getLocalitate());
            preparedStatement.setString(7, adresa.getJudet());
            if(adresa.getSector() == null){
                preparedStatement.setNull(8, Types.INTEGER);
            }
            else {
                preparedStatement.setInt(8, adresa.getSector());
            }
            preparedStatement.setString(9, adresa.getCodPostal());
            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            e.printStackTrace();
        }

    }

    public static void insert2(String strada,Integer nr, Integer bloc, String scara, Integer apartament, String localitate, String judet, Integer sector, String codPostal) { // CREATE - insereaza campurile pentru sofer in bd
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSoferSql);
            preparedStatement.setString(1, strada);
            preparedStatement.setInt(2, nr);
            if(bloc == null){
                preparedStatement.setNull(3, Types.INTEGER);

            }
            else{
            preparedStatement.setInt(3, bloc);}

            if(scara == null){
                preparedStatement.setNull(4, Types.VARCHAR);

            }
            else{
            preparedStatement.setString(4, scara);
            }
            if (apartament == null){
                preparedStatement.setNull(5, Types.INTEGER);
            }
            else{
            preparedStatement.setInt(5, apartament);
            }
            preparedStatement.setString(6, localitate);
            preparedStatement.setString(7, judet);
            if(sector == null){
                preparedStatement.setNull(8, Types.INTEGER);
            }
            else {
                preparedStatement.setInt(8, sector);
            }

            preparedStatement.setString(9, codPostal);

            preparedStatement.executeUpdate();
        } catch (SQLException e)    {
            e.printStackTrace();
        }

    }

    public static Optional<Adresa> getAdresabyId(int id) { //READ - afiseaza adresa cu id -ul x
        String selectSql = "SELECT * FROM Adrese a WHERE a.id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAdresa(resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static Optional<Adresa> mapToAdresa(ResultSet resultSet) throws SQLException {
        if(resultSet.next()){
            String str = resultSet.getString(2);
             Integer nr = resultSet.getInt(3);
            Integer bloc = resultSet.getInt(4);
            String scara = resultSet.getString(5);
            Integer apartament = resultSet.getInt(6);
            String localitate = resultSet.getString(7);
            String judet = resultSet.getString(8);
            Integer sector = resultSet.getInt(9);
            String codPostal = resultSet.getString(10);
            return Optional.of(new Adresa(str, nr,bloc, scara, apartament, localitate, judet, sector, codPostal));
        }
        return Optional.empty();
    }



    public static List<Adresa> get_adrese(){
        String selectSql = "SELECT * FROM Adrese";
        List <Adresa> adrese = new ArrayList<Adresa>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                String str = resultSet.getString(2);
                Integer nr = resultSet.getInt(3);
                Integer bloc = resultSet.getInt(4);
                String scara = resultSet.getString(5);
                Integer apartament = resultSet.getInt(6);
                String localitate = resultSet.getString(7);
                String judet = resultSet.getString(8);
                Integer sector = resultSet.getInt(9);
                String codPostal = resultSet.getString(10);
                adrese.add(new Adresa(str, nr,bloc, scara, apartament, localitate, judet, sector, codPostal));


            }
        }
        catch (SQLException e){
            e.printStackTrace();

        }
        return  adrese;

    }

    public static void deleteAdresa(Integer id){ //DELETE - sterge o adresa dupa nume;
        String selectSql = "DELETE FROM Adrese a WHERE a.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void updateStrada(Integer id, String adresa_noua){ //UPDATE
        String selectSql = "UPDATE Adrese a SET a.strada = ? WHERE a.id = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, adresa_noua);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void updateNr(Integer id, Integer nr_nou){ //UPDATE
        String selectSql = "UPDATE Adrese a SET a.nr = ? WHERE a.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1,nr_nou);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void updateBloc(Integer id, Integer bloc_nou){ //UPDATE
        String selectSql = "UPDATE Adrese a SET a.bloc = ? WHERE a.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, bloc_nou);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateScara(Integer id, String scara){ //UPDATE
        String selectSql = "UPDATE Adrese a SET a.scara = ? WHERE a.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, scara);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void updateApartament(Integer id, Integer ap){ //UPDATE
        String selectSql = "UPDATE Adrese a SET a.apartament = ? WHERE a.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, ap);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void updateCodPostal(Integer id, String cod){ //UPDATE
        String selectSql = "UPDATE Adrese a SET a.codPostal = ? WHERE a.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, cod);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static Integer getIdMax(){
        String selectSql = "SELECT MAX(id) from Adrese";
        Integer id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id  = resultSet.getInt(1);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    public static List<Integer> getIds(){
        String selectSql = "SELECT id from Adrese";
        List<Integer> ids = new ArrayList<Integer>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                ids.add(resultSet.getInt(1));
            }
            }
        catch (SQLException e){
            e.printStackTrace();
        }
        return ids;
    }

    public static Integer getIdAdresa(Adresa adresa){
        Integer id = null;
        String selectSql = "SELECT id FROM Adrese a WHERE a.strada = ? AND a.nr = ? AND a.codPostal = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1,adresa.getStrada());
            preparedStatement.setInt(2, adresa.getNr());
            preparedStatement.setString(3, adresa.getCodPostal());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return id;
    }
}
