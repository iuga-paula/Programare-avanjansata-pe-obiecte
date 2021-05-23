package Gestiuni;


import Bd.Configurare_Bd;

import Localuri.Adresa;
import Localuri.Local;
import Localuri.Sofer;
import Meniu.Bauturi;
import Meniu.Desert;
import Meniu.FelPrincipal;
import Repo.*;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class ManagerBd {

    //iau fiecare id din local => creez ob local si apoi il caut in tabelele baturi_local etc
    // si inserez produsul la lista produselor din local
    private final static Connection connection = Bd.Configurare_Bd.getDatabaseConnection();
    private static Configurare_Bd DatabaseConfiguration;

    public static List<Local> adaugaLocaluriBD(){ //adaug localurile si produsele aferente din baza de date
        List<Local> localuri = Localuri_Repo.get_localuri();
        for (Local local: localuri) {
            int id = Localuri_Repo.getId(local.getDenumire());
            //ii caut produsele
            Set<Bauturi> bauturi = Bauturi_Repo.getBauturiLocal(id);
            Set<Desert> deserturi = Desert_Repo.getDeserturiLocal(id);
            Set<FelPrincipal> felPrincipale = Felurip_Repo.getFeluripLocal(id);

            for (Bauturi batura:bauturi) {
                local.addProdus(batura);

            }
            for (Desert desert:deserturi) {
                local.addProdus(desert);

            }
            for (FelPrincipal felPrincipal:felPrincipale) {
                local.addProdus(felPrincipal);

            }

        }
        return localuri;

    }

    public static void adaugaComanda(String username, Date data,  Double total, Double discount, Integer idLocal, Integer idSofer){
        String insertComanda = "INSERT INTO Comenzi(username, data_comanda, total, discount, idLocal, idSofer) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertComanda);
            preparedStatement.setString(1, username);
            preparedStatement.setDate(2, (java.sql.Date) data);
            preparedStatement.setDouble(3, total);
            preparedStatement.setDouble(4, discount);
            preparedStatement.setInt(5, idLocal);
            if(idSofer == null){
                preparedStatement.setNull(6, Types.INTEGER);

            }
            else{
            preparedStatement.setInt(6, idSofer);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void stergeComanda(Integer id){
        String deleteComanda = "DELETE FROM Comenzi c where c.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteComanda);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static  int nrComenziUser(String username){
        //vad cate comenzi a mai facut acest user pt a-i da puncte de reducere;
        //un pct valoreaza 0,08 lei

        String selectSql = "SELECT COUNT(*) FROM Comenzi c where c.username = ?";
        int nrComenzi = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            nrComenzi = resultSet.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  nrComenzi;

    }



    public static void main(String[] args) {
        try {

            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            if (!connection.isClosed()) {
                System.out.println("Conexiune ok");
            }
        }
        catch (SQLException e){
            e.printStackTrace();

        }
        /*SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");//pt data
        Date date = new Date();
        java.sql.Date new_date =new java.sql.Date(date.getTime());
        adaugaComanda("eu", new_date, 12.67, 0.01, 1, null);
        System.out.println(nrComenziUser("eu") + ", " + nrComenziUser("eu") * 0.08);
        stergeComanda(1);

        /*FelPrincipal fp = new FelPrincipal("", "", "Penne al forno", 32.01);
        Felurip_Repo.insert(fp);
        Felurip_Repo.insert2("Pizza calzone", 25.60, "", "");
        Integer id = Felurip_Repo.getIdFelp(fp);
        if(id != null) {
            Felurip_Repo.updatePret(id, 26.04);
            Felurip_Repo.updateDenumire(id, "Calzone");
            Felurip_Repo.adaugaFelpLocal(6,id);

        }
        Felurip_Repo.deleteFelP(9);
        Felurip_Repo.deleteFelP(10);
        Felurip_Repo.stergeFelpLocal(9,6);
        Felurip_Repo.stergeFelpLocal(8,6);
        Felurip_Repo.adaugaFelpLocal(6,8);
        List<FelPrincipal> feluri = Felurip_Repo.getFelurip();
        for(FelPrincipal f:feluri){
            System.out.println(f);
        }

        Set<FelPrincipal> feluri2 = Felurip_Repo.getFeluripLocal(Localuri_Repo.getId("Alla Don Antonio"));
        for(FelPrincipal f:feluri){
            System.out.println(f);
        }
        System.out.println();
        System.out.println(feluri2);




        /*Desert d = new Desert(Boolean.TRUE, "Greta garben", 10.59);
        Desert_Repo.insert(d);
        Desert_Repo.insert2("Tiramisuu", 12.40, Boolean.FALSE);
        Integer id = Desert_Repo.getIdDesert(d);
        if(id != null){
            Desert_Repo.updateDenumire(id, "Greta Garben");
            Desert_Repo.updateVegan(id, Boolean.FALSE);
            Desert_Repo.updatePret(id, 10.50);
            Desert_Repo.adaugaDesertLocal(6,id);
        }
        //Desert_Repo.adaugaDesertLocal(6, 6);
        Desert_Repo.stergeDesertLocal(7,6);
        Desert_Repo.adaugaDesertLocal(6,7);
        Set<Desert> deserts = Desert_Repo.getDeserturiLocal(6);
        System.out.println(deserts);


        /*Bauturi b = new Bauturi(Boolean.FALSE, Boolean.TRUE, "Limonada", 13.00);
        Bauturi_Repo.insert(b);
        Bauturi_Repo.insert2("Sifon", 10.00, Boolean.TRUE, Boolean.TRUE);
        Integer id = Bauturi_Repo.getIdBautura(b);
        if(id != null)
        {Bauturi_Repo.adaugaBaturaLocal(6, id);
        Bauturi_Repo.updateDenumire(id, "Limonada cu menta");
        Bauturi_Repo.updatePret(id, 13.55);}

        Bauturi_Repo.deleteBautura(8);
        Bauturi_Repo.adaugaBaturaLocal(6,9);
        List<Bauturi> bauturi = Bauturi_Repo.getBauturi();
        Set<Bauturi> bauturi2 = Bauturi_Repo.getBauturiLocal(6);
        for (Bauturi bauturi1 : bauturi){
            System.out.println(bauturi1);
        }
        System.out.println();
        for(Bauturi bauturi1:bauturi2){
            System.out.println(bauturi1);
        }
        Bauturi b = new Bauturi(Boolean.TRUE, Boolean.TRUE, "Limonada", 13.50);
        //Bauturi_Repo.insert(b);
        //Bauturi_Repo.deleteBautura(10);
        Integer id = Bauturi_Repo.getIdBautura(b);
        System.out.println(id);
        Bauturi_Repo.updatePret(id, 13.50);
        Bauturi_Repo.updateDenumire(id, "Limonada cu miere");
        Bauturi_Repo.updateAlcool(id, Boolean.FALSE);
        Bauturi_Repo.updateCarbo(id,Boolean.FALSE);
        Bauturi_Repo.adaugaBaturaLocal(6,11);



        Localuri_Repo.insert(new Local(new Adresa(), "D.O.R",
                new Time(10000), new Time(3000)), null);
        Localuri_Repo.insert2("Alla Don Antonio", new Time(10000), new Time(3000), null);
        System.out.println(Localuri_Repo.getLocalbyId(2));
        System.out.println(Localuri_Repo.getLocalbyName("D.O.R"));

        List<Local> locals = Localuri_Repo.get_localuri();
        for (Local local:locals){
            System.out.println(local);
        }

        Localuri_Repo.updateAdresa("Alla Don Antonio", 7);
        Localuri_Repo.updateDenumireLocal("D.O.R", "DOR");
        Localuri_Repo.updateTimpExecutie("D.O.R",new Time(12332));
        Localuri_Repo.updateTimpLivrare("D.O.R",new Time(30000));
        Localuri_Repo.deleteLocal("DOR");



        /*List<Adresa> adrese = Adresa_Repo.get_adrese();
        for (Adresa adresa:adrese){
            System.out.println(adresa);
        }

        Optional<Adresa> adr = Adresa_Repo.getAdresabyId(1);
        if(adr.isPresent()){
            System.out.println(adr);
        }

        List<Integer> ids = Adresa_Repo.getIds();
        System.out.println(ids.toString());

        System.out.println(Adresa_Repo.getIdMax());

        Adresa_Repo.updateApartament(3, 1);
        Adresa_Repo.updateScara(4,"C");
        Adresa_Repo.updateNr(1,11);
        Adresa_Repo.updateCodPostal(1,"302901");



        Adresa_Repo.insert2("Vlahuta", 22, null, null, null, "Bucuresti", "Bucuresti", 3, "1234566");
        Adresa_Repo.insert(new Adresa("Diana", 19, null, null, null, "Brasov", "Brasov", null, "1234039"));
        Adresa_Repo.deleteAdresa(Adresa_Repo.getIdMax());
        Adresa_Repo.updateStrada(7, "Vlahutei");

        System.out.println("--------------------------");




        Set<Sofer> soferi = Soferi_Repo.get_soferi();
        for(Sofer sofer:soferi){
            System.out.println(sofer);

        }

        Optional<Sofer> op = Soferi_Repo.getSoferbyId(1);
        if(op.isPresent()){
            System.out.println(op);
        }
        else{
            System.out.println("Nu e soferul cu id 1");
        }
        Optional<Sofer> op2 = Soferi_Repo.getSoferbyId(100);
        if(op2.isPresent()){
            System.out.println(op2);
        }
        else {
            System.out.println("Nu e soferul cu id 100");
        }



        Soferi_Repo.updateNumeSofer("Dumitriu", "Iustin","Dumitru");
        Soferi_Repo.updatePrenumeSofer("Dumitru", "Iustin", "Iustinian");
        Soferi_Repo.updateContractSofer("Ieftimie", "Antonio", "pactica");
        Soferi_Repo.updateSalariuSofer("Ieftimie", "Antonio", 1500.78);
        Soferi_Repo.updateTelefonSofer("Georgescu", "Alex Andrei", "0773707915");

        Optional<Sofer> op3 = Soferi_Repo.getSoferbyName("Dumitru", "Iustin");
        if(op3.isPresent()){
            System.out.println(op3);
        }
        else {
            System.out.println("Nu exista sofer cu acest nume");
        }

        Soferi_Repo.deleteSofer("Irimia", "Marian");


        Soferi_Repo.insert(new Sofer("Irimia", "Marian", "full-time", 3000.00, "1234567890"));
        Soferi_Repo.insert(new Sofer("Alexoi", "David", "part-time", 2000.00, "223948028"));
        Soferi_Repo.deleteSofer("Alexoi", "David");
        Soferi_Repo.insert2("Popa", "Cosmin-Andrei", "full-time",1560.90, "0771345467");*/





    }
}
