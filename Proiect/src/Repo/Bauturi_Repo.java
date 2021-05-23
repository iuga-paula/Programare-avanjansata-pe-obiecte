package Repo;

import Meniu.Bauturi;

import java.sql.*;
import java.util.*;

public class Bauturi_Repo {
        //CRUD pe Meniu.Bauturi in bd

        private final static String insertBauturaSql = "INSERT INTO Bauturi(denumire, pret, alcool, carbogazificata) VALUES(?, ?, ?, ?)";

        private final static Connection connection = Bd.Configurare_Bd.getDatabaseConnection();

        public static void insert(Bauturi bautura) { // CREATE - insereaza campurile din ob Bautura in bd
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertBauturaSql);
                preparedStatement.setString(1, bautura.getDenumire());
                preparedStatement.setDouble(2, bautura.getPret());
                preparedStatement.setBoolean(3, bautura.getAlcool());
                preparedStatement.setBoolean(4, bautura.getCarbogazificate());
                preparedStatement.executeUpdate();
            } catch (SQLException e)    {
                e.printStackTrace();
            }

        }

        public static void insert2(String denumire, Double pret, Boolean alcool, Boolean carbogazificata) { // CREATE - insereaza campurile pentru bautura in bd
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertBauturaSql);
                preparedStatement.setString(1, denumire);
                preparedStatement.setDouble(2, pret);
                preparedStatement.setBoolean(3, alcool);
                preparedStatement.setBoolean(4, carbogazificata);
                preparedStatement.executeUpdate();
            } catch (SQLException e)    {
                e.printStackTrace();
            }

        }

        public static void adaugaBaturaLocal(Integer idLocal, Integer idBautura) {//Adauga batura la un anumit local dat prin id => insereaza in tabela bauturi_local cheie bautura cheie local

            // intai verific daca exista id-urile in tabelele mama
            String selectSql = "SELECT COUNT(*) FROM Localuri l WHERE l.id = ?";
            String selectSql2 = "SELECT COUNT(*) FROM Bauturi b WHERE b.id = ?";
            String insertSql = "INSERT INTO Bauturi_Local VALUES(?, ?)";
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
                preparedStatement.setInt(1, idBautura);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                nr = resultSet.getInt(1);
                if(nr < 1) {
                    return;
                }

                try {
                    //daca am gasit id-urile
                    System.out.println("ok");
                    preparedStatement = connection.prepareStatement(insertSql);
                    preparedStatement.setInt(1, idLocal);
                    preparedStatement.setInt(2, idBautura);
                    preparedStatement.executeUpdate();
                }
                catch (SQLException ignored) { //daca s-a mai inserat o data ignor

                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    public static void stergeBaturaLocal(Integer idLocal, Integer idBautura) {
            String delete = "DELETE FROM Bauturi_Local where idLocal = ? and idProdus = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1, idLocal);
            preparedStatement.setInt(2, idBautura);
            preparedStatement.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    public static Optional<Bauturi> getBauturabyId(int id) { //READ - afiseaza bautura cu id -ul x
            String selectSql = "SELECT * FROM Bauturi b WHERE b.id = ?";
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                return mapToBautura(resultSet);
            } catch (SQLException e){
                e.printStackTrace();
            }
            return Optional.empty();
        }

        private static Optional<Bauturi> mapToBautura(ResultSet resultSet) throws SQLException {
            if(resultSet.next()){
                String nume = resultSet.getString(2);
                Double pret = resultSet.getDouble(3);
                Boolean alcool = resultSet.getBoolean(4);
                Boolean carbo = resultSet.getBoolean(5);

                return Optional.of(new Bauturi(alcool, carbo, nume, pret));
                }
                else{
                    return  Optional.empty();
                }
        }


        public static List<Bauturi> getBauturi(){ // ia toate bauturile existente in toate localurile
            String selectSql = "SELECT * FROM Bauturi";
            List<Bauturi> bauturi = new ArrayList<>();

            try (Statement stmt = connection.createStatement()) {
                ResultSet resultSet = stmt.executeQuery(selectSql);
                while (resultSet.next()) {
                    String nume = resultSet.getString(2);
                    Double pret = resultSet.getDouble(3);
                    Boolean alcool = resultSet.getBoolean(4);
                    Boolean carbo = resultSet.getBoolean(5);

                    bauturi.add(new Bauturi(alcool, carbo, nume, pret));

                }
            }
            catch (SQLException e){
                e.printStackTrace();

            }
            return  bauturi;

        }

    public static Set<Bauturi> getBauturiLocal(Integer idLocal) { // ia un set cu toate baturile localului
        String selectSql = "SELECT idProdus FROM Bauturi_Local b WHERE b.idLocal = ?";
        Set <Bauturi> bauturi = new HashSet<>();

        try{
            PreparedStatement stmt = connection.prepareStatement(selectSql);
            stmt.setInt(1, idLocal);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                //iau cate un id local
                int idBautura = resultSet.getInt(1);

                //iau bautura cu id -ul respectiv
                Optional<Bauturi> opt = getBauturabyId(idBautura);
                if(opt.isPresent())
                {Bauturi bautura = opt.orElseGet(() -> opt.get()); // iau bautura din Optional
                bauturi.add(bautura);}

            }
        }
        catch (SQLException e){
            e.printStackTrace();

        }

        return  bauturi;
    }


        public static void deleteBautura(Integer id){ //DELETE - sterge o bautura dupa id;
            String selectSql = "DELETE FROM Bauturi b WHERE b.id = ?";
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
            String selectSql = "UPDATE Bauturi b SET b.denumire = ? WHERE b.id = ?";

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
            String selectSql = "UPDATE Bauturi b SET b.pret = ? WHERE b.id = ?";
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

        public static void updateAlcool(Integer id, Boolean alcool){ //UPDATE alcool
            String selectSql = "UPDATE Bauturi b SET b.alcool = ? WHERE b.id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setBoolean(1, alcool);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static void updateCarbo(Integer id, Boolean carbo){ //UPDATE carbogazificata
            String selectSql = "UPDATE Bauturi b SET b.carbogazificata = ? WHERE b.id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setBoolean(1, carbo);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        public static Integer getIdBautura(Bauturi bautura){
            String selectSql = "SELECT id FROM Bauturi b where b.denumire = ? AND b.pret = ?";
            Integer id = null;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
                preparedStatement.setString(1, bautura.getDenumire());
                preparedStatement.setDouble(2, bautura.getPret());
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next())
                    id = resultSet.getInt(1);
            }
            catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return id;
        }

}


