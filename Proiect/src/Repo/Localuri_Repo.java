package Repo;

import Localuri.Adresa;
import Localuri.Local;
import Localuri.Sofer;

import java.sql.*;
import java.util.*;

public class Localuri_Repo {

        //CRUD pe Localuri.Local in bd

        private final static String insertLocalSql = "INSERT INTO Localuri(denumire, timp_executie, timp_livrare, idAdresa) VALUES(?, ?, ?, ?)";

        private final static Connection connection = Bd.Configurare_Bd.getDatabaseConnection();

        public static void insert(Local local, Integer idAdresa) { // CREATE - insereaza campurile din ob Local in bd
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertLocalSql);
                preparedStatement.setString(1, local.getDenumire());
                preparedStatement.setTime(2, local.getTimpExecutieComanda());
                preparedStatement.setTime(3, local.getTimpLivrare());
                if(idAdresa == null){
                   preparedStatement.setNull(4, Types.INTEGER);

                }
                else {
                preparedStatement.setInt(4, idAdresa);}

                preparedStatement.executeUpdate();
            } catch (SQLException e)    {
                e.printStackTrace();
            }

        }

        public static void insert2(String denumire, Time timp_executie, Time timp_livrare, Integer idAdresa) { // CREATE - insereaza campurile pentru local in bd
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertLocalSql);
                preparedStatement.setString(1, denumire);
                preparedStatement.setTime(2, timp_executie);
                preparedStatement.setTime(3, timp_livrare);
                if(idAdresa == null){
                    preparedStatement.setNull(4, Types.INTEGER);

                }
                else {
                    preparedStatement.setInt(4, idAdresa);
                }
                preparedStatement.executeUpdate();
            } catch (SQLException e)    {
                e.printStackTrace();
            }

        }

        public static Optional<Local> getLocalbyId(int id) { //READ - afiseaza localul cu id -ul x
            String selectSql = "SELECT * FROM Localuri l WHERE l.id = ?";
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                return mapToLocal(resultSet);
            } catch (SQLException e){
                e.printStackTrace();
            }
            return Optional.empty();
        }

        private static Optional<Local> mapToLocal(ResultSet resultSet) throws SQLException {
            if(resultSet.next()){
                String nume = resultSet.getString(2);
                Time timp_livrare = resultSet.getTime(4);
                Time timp_executie = resultSet.getTime(3);
                Integer idAdresa = resultSet.getInt(5);

                Adresa_Repo adresa_repo = new Adresa_Repo();
                Optional<Adresa> adresa = adresa_repo.getAdresabyId(idAdresa);
                if(adresa.isPresent()){
                    Adresa adr = adresa.orElseGet(() -> adresa.get());
                    return Optional.of(new Local(adr,nume, timp_executie, timp_livrare));
                }
                else{
                    return  Optional.empty();
                }

            }
            return Optional.empty();
        }

        public static Optional<Local> getLocalbyName(String denumire){ //READ - afiseaza localul cu numele x
            String selectSql = "SELECT * FROM Localuri l WHERE l.denumire = ?";
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setString(1, denumire);
                ResultSet resultSet = preparedStatement.executeQuery();
                return mapToLocal(resultSet);
            } catch (SQLException e){
                e.printStackTrace();
            }
            return Optional.empty();

        }

        public static List<Local> get_localuri(){
            String selectSql = "SELECT * FROM Localuri";
            List<Local> locals = new ArrayList<Local>();

            try (Statement stmt = connection.createStatement()) {
                ResultSet resultSet = stmt.executeQuery(selectSql);
                while (resultSet.next()) {
                    String nume = resultSet.getString(2);
                    Time timp_livrare = resultSet.getTime(4);
                    Time timp_executie = resultSet.getTime(3);
                    Integer idAdresa = resultSet.getInt(5);

                    Adresa_Repo adresa_repo = new Adresa_Repo();
                    Optional<Adresa> adresa = adresa_repo.getAdresabyId(idAdresa);
                    if (adresa.isPresent()) {
                        Adresa adr = adresa.orElseGet(() -> adresa.get());
                        locals.add(new Local(adr, nume, timp_executie, timp_livrare));

                    }
                }
            }
            catch (SQLException e){
                e.printStackTrace();

            }
            return  locals;

        }

        public static void deleteLocal(String denumire){ //DELETE - sterge un sofer dupa nume;
            String selectSql = "DELETE FROM Localuri l WHERE l.denumire = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setString(1, denumire);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }

        public static void updateDenumireLocal(String denumire, String denumire_noua){ //UPDATE
            String selectSql = "UPDATE Localuri l SET l.denumire = ? WHERE l.denumire = ?";

            try {

                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setString(1, denumire_noua);
                preparedStatement.setString(2, denumire);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                System.out.println("Mai exista un local cu este nume!");

            }

        }


        public static void updateTimpExecutie(String denumire, Time tp){ //UPDATE
            String selectSql = "UPDATE Localuri l SET l.timp_executie = ? WHERE l.denumire = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setTime(1, tp);
                preparedStatement.setString(2, denumire);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void updateTimpLivrare(String denumire, Time tp){ //UPDATE
            String selectSql = "UPDATE Localuri l SET l.timp_livrare = ? WHERE l.denumire = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setTime(1, tp);
                preparedStatement.setString(2, denumire);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void updateAdresa(String denumire, Integer id){ //UPDATE
        String selectSql = "UPDATE Localuri l SET l.idAdresa = ? WHERE l.denumire = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, denumire);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Integer getId(String denumire){ //iau id -ul localului cu denumirea x
        String selectSql = "SELECT id FROM Localuri l WHERE l.denumire = ?";
        Integer id = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, denumire);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
            id = resultSet.getInt(1);}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;

    }

}


