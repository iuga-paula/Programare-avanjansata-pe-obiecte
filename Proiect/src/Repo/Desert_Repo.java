package Repo;

import Meniu.Desert;

import java.sql.*;
import java.util.*;

public class Desert_Repo {
        //CRUD pe Meniu.Desert in bd

        private final static  String insertDesertSql = "INSERT INTO Deserturi(denumire, pret, vegan) VALUES(?, ?, ?)";

        private final static Connection connection = Bd.Configurare_Bd.getDatabaseConnection();

        public static void insert(Desert desert) { // CREATE - insereaza campurile din ob Desert in bd
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertDesertSql);
                preparedStatement.setString(1, desert.getDenumire());
                preparedStatement.setDouble(2, desert.getPret());
                preparedStatement.setBoolean(3, desert.getVegan());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public static void insert2(String denumire, Double pret, Boolean vegan) { // CREATE - insereaza campurile pentru desert in bd
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertDesertSql);
                preparedStatement.setString(1, denumire);
                preparedStatement.setDouble(2, pret);
                preparedStatement.setBoolean(3, vegan);
                preparedStatement.executeUpdate();
            } catch (SQLException e)    {
                e.printStackTrace();
            }

        }

        public static void adaugaDesertLocal(Integer idLocal, Integer idDesert) {//Adauga desert la un anumit local dat prin id => insereaza in tabela deserturi_local cheie desert cheie local

            // intai verific daca exista id-urile in tabelele mama
            String selectSql = "SELECT COUNT(*) FROM Localuri l WHERE l.id = ?";
            String selectSql2 = "SELECT COUNT(*) FROM Deserturi d WHERE d.id = ?";
            String insertSql = "INSERT INTO Deserturi_Local VALUES(?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setInt(1, idLocal);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                int nr = resultSet.getInt(1);
                if(nr < 1) { //daca nu a gasit id-ul are count 0
                    return;
                }

                preparedStatement = connection.prepareStatement(selectSql2);
                preparedStatement.setInt(1, idDesert);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                nr = resultSet.getInt(1);
                if(nr < 1) {
                    return;
                }

                try {
                    //daca am gasit id-urile
                    preparedStatement = connection.prepareStatement(insertSql);
                    preparedStatement.setInt(1, idLocal);
                    preparedStatement.setInt(2, idDesert);
                    preparedStatement.executeUpdate();
                }
                catch (SQLException ignored) { //daca s-a mai inserat o data ignor

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    public static void stergeDesertLocal(Integer idLocal, Integer idDesert) {
        String delete = "DELETE FROM Deserturi_Local where idLocal = ? and idProdus = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, idLocal);
            preparedStatement.setInt(2, idDesert);
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


        public static Optional<Desert> getDesertbyId(int id) { //READ - afiseaza desertul cu id -ul x
            String selectSql = "SELECT * FROM Deserturi d WHERE d.id = ?";
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                return mapToDesert(resultSet);
            } catch (SQLException e){
                e.printStackTrace();
            }
            return Optional.empty();
        }

        private static Optional<Desert> mapToDesert(ResultSet resultSet) throws SQLException {
            if(resultSet.next()){
                String nume = resultSet.getString(2);
                Double pret = resultSet.getDouble(3);
                Boolean vegan = resultSet.getBoolean(4);

                return Optional.of(new Desert(vegan, nume, pret));
            }
            else{
                return  Optional.empty();
            }
        }


        public static List<Desert> getDeserturi(){ // ia toate deserturile existente in toate localurile
            String selectSql = "SELECT * FROM Deserturi";
            List<Desert> bauturi = new ArrayList<>();

            try (Statement stmt = connection.createStatement()) {
                ResultSet resultSet = stmt.executeQuery(selectSql);
                while (resultSet.next()) {
                    String nume = resultSet.getString(2);
                    Double pret = resultSet.getDouble(3);
                    Boolean vegan = resultSet.getBoolean(4);

                    bauturi.add(new Desert(vegan, nume, pret));

                }
            }
            catch (SQLException e){
                e.printStackTrace();

            }
            return  bauturi;

        }

        public static Set<Desert> getDeserturiLocal(Integer idLocal) { // ia un set cu toate deserturile localului
            String selectSql = "SELECT idProdus FROM Deserturi_Local WHERE idLocal = ?";
            Set <Desert> bauturi = new HashSet<>();

            try{
                PreparedStatement stmt = connection.prepareStatement(selectSql);
                stmt.setInt(1, idLocal);
                ResultSet resultSet = stmt.executeQuery();

                while (resultSet.next()) {
                    //iau cate un id local
                    int idDesert = resultSet.getInt(1);

                    //iau desertul cu id -ul respectiv
                    Optional<Desert> opt = getDesertbyId(idDesert);
                    if(opt.isPresent())
                    {Desert bautura = opt.orElseGet(() -> opt.get()); // iau desertul din Optional
                    bauturi.add(bautura);}

                }
            }
            catch (SQLException e){
                e.printStackTrace();

            }

            return  bauturi;
        }


        public static void deleteDesert(Integer id){ //DELETE - sterge un desert dupa id;
            String selectSql = "DELETE FROM Deserturi d WHERE d.id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }

        }

        public static void updateDenumire(Integer id, String denumire_noua){ //UPDATE denumire
            String selectSql = "UPDATE Deserturi d SET d.denumire = ? WHERE d.id = ?";

            try {

                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setString(1, denumire_noua);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();

            }

        }


        public static void updatePret(Integer id, Double pretNou){ //UPDATE pret
            String selectSql = "UPDATE Deserturi d SET d.pret = ? WHERE d.id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setDouble(1, pretNou);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void updateVegan(Integer id, Boolean vegan){ //UPDATE alcool
            String selectSql = "UPDATE Deserturi d SET d.vegan = ? WHERE d.id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setBoolean(1, vegan);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static Integer getIdDesert(Desert desert){
            String selectSql = "SELECT id FROM Deserturi d where d.denumire = ? AND d.pret = ?";
            Integer id = null;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setString(1, desert.getDenumire());
                preparedStatement.setDouble(2, desert.getPret());
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return id;
        }

}
