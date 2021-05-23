package Repo;

import Meniu.Desert;
import Meniu.FelPrincipal;

import java.sql.*;
import java.util.*;

public class Felurip_Repo {
        //CRUD pe Meniu.FelPrincipal in bd

        private final static String insertDesertSql = "INSERT INTO FeluriP(denumire, pret, garnitura, salata) VALUES(?, ?, ?, ?)";

        private final static Connection connection = Bd.Configurare_Bd.getDatabaseConnection();

        public static void insert(FelPrincipal fp) { // CREATE - insereaza campurile din ob FelPrincial in bd
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertDesertSql);
                preparedStatement.setString(1, fp.getDenumire());
                preparedStatement.setDouble(2, fp.getPret());
                preparedStatement.setString(3, fp.getGarnitura());
                preparedStatement.setString(4, fp.getSalata());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public static void insert2(String denumire, Double pret,  String garnitura, String salata) { // CREATE - insereaza campurile pentru del principal in bd
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertDesertSql);
                preparedStatement.setString(1, denumire);
                preparedStatement.setDouble(2, pret);
                preparedStatement.setString(3, garnitura);
                preparedStatement.setString(4, salata);
                preparedStatement.executeUpdate();
            } catch (SQLException e)    {
                e.printStackTrace();
            }

        }

        public static void adaugaFelpLocal(Integer idLocal, Integer idFelp) {//Adauga fel principal la un anumit local dat prin id => insereaza in tabela felurip_local cheie fel p cheie local

            // intai verific daca exista id-urile in tabelele mama
            String selectSql = "SELECT COUNT(*) FROM Localuri l WHERE l.id = ?";
            String selectSql2 = "SELECT COUNT(*) FROM Felurip f WHERE f.id = ?";
            String insertSql = "INSERT INTO Felurip_Local VALUES(?, ?)";
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
                preparedStatement.setInt(1, idFelp);
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
                    preparedStatement.setInt(2, idFelp);
                    preparedStatement.executeUpdate();
                }
                catch (SQLException ignored) { //daca s-a mai inserat o data ignor

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    public static void stergeFelpLocal(Integer idLocal, Integer idFelp) {
        String delete = "DELETE FROM Felurip_Local where idLocal = ? and idProdus = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, idLocal);
            preparedStatement.setInt(2, idFelp);
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


        public static Optional<FelPrincipal> getFelpbyId(int id) { //READ - afiseaza felul principal cu id -ul x
            String selectSql = "SELECT * FROM Felurip f WHERE f.id = ?";
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                return mapToFelP(resultSet);
            } catch (SQLException e){
                e.printStackTrace();
            }
            return Optional.empty();
        }

        private static Optional<FelPrincipal> mapToFelP(ResultSet resultSet) throws SQLException {
            if(resultSet.next()){
                String nume = resultSet.getString(2);
                Double pret = resultSet.getDouble(3);
                String garnitura = resultSet.getString(4);
                String salata = resultSet.getString(5);


                return Optional.of(new FelPrincipal(garnitura, salata, nume, pret));
            }
            else{
                return  Optional.empty();
            }
        }


        public static List<FelPrincipal> getFelurip(){ // ia toate felurile p existente in toate localurile
            String selectSql = "SELECT * FROM Felurip";
            List<FelPrincipal> bauturi = new ArrayList<>();

            try (Statement stmt = connection.createStatement()) {
                ResultSet resultSet = stmt.executeQuery(selectSql);
                while (resultSet.next()) {
                    String nume = resultSet.getString(2);
                    Double pret = resultSet.getDouble(3);
                    String garnitura = resultSet.getString(4);
                    String salata = resultSet.getString(5);

                    bauturi.add(new FelPrincipal(garnitura, salata, nume, pret));

                }
            }
            catch (SQLException e){
                e.printStackTrace();

            }
            return  bauturi;

        }

        public static Set<FelPrincipal> getFeluripLocal(Integer idLocal) { // ia un set cu toate felurile p ale localului cu id ul x
            String selectSql = "SELECT idProdus FROM Felurip_Local WHERE idLocal = ?";
            Set <FelPrincipal> bauturi = new HashSet<>();

            try{
                PreparedStatement stmt = connection.prepareStatement(selectSql);
                stmt.setInt(1, idLocal);
                ResultSet resultSet = stmt.executeQuery();

                while (resultSet.next()) {
                    //iau cate un id local
                    int idFelp = resultSet.getInt(1);

                    //iau desertul cu id -ul respectiv
                    Optional<FelPrincipal> opt = getFelpbyId(idFelp);
                    if(opt.isPresent())
                    {FelPrincipal felPrincipal = opt.orElseGet(() -> opt.get()); // iau desertul din Optional
                    bauturi.add(felPrincipal);}

                }
            }
            catch (SQLException e){
                e.printStackTrace();

            }

            return  bauturi;
        }


        public static void deleteFelP(Integer id){ //DELETE - sterge un fel p dupa id;
            String selectSql = "DELETE FROM Felurip f WHERE f.id = ?";
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
            String selectSql = "UPDATE Felurip f SET f.denumire = ? WHERE f.id = ?";

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
            String selectSql = "UPDATE Felurip f SET f.pret = ? WHERE f.id = ?";
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

        public static void updateGarnitura(Integer id, String garnituraNoua){ //UPDATE garnitura
            String selectSql = "UPDATE Felurip f SET f.garnitura = ? WHERE f.id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setString(1, garnituraNoua);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void updateSalata(Integer id, String salataNoua){ //UPDATE salata
            String selectSql = "UPDATE Salata s SET s.salata = ? WHERE s.id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setString(1, salataNoua);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

    public static Integer getIdFelp(FelPrincipal felp){
        String selectSql = "SELECT id FROM Felurip f where f.denumire = ? AND f.pret = ?";
        Integer id = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, felp.getDenumire());
            preparedStatement.setDouble(2, felp.getPret());
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

