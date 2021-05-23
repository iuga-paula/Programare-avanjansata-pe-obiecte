package CSV;

import Localuri.Adresa;
import Localuri.Local;
import Meniu.Bauturi;
import Meniu.Desert;
import Meniu.FelPrincipal;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;


public class CitireDate {

    private static CitireDate instance;

    private CitireDate() {

    }

    public static CitireDate getInstance() {
        if (instance == null) {
            instance = new CitireDate();
        }
        return instance;
    }

    public TreeSet<Local> citesteDate() {

        TreeSet<Local> localuri = new TreeSet<>();
        try {


            CSVReader reader1 = new CSVReaderBuilder(new FileReader("D:\\documente\\An2_sem2\\ProgrAvansOb\\Proiect\\Adrese.csv")).withSkipLines(1)
                    .build();

            CSVReader reader2 = new CSVReaderBuilder(new FileReader("D:\\documente\\An2_sem2\\ProgrAvansOb\\Proiect\\Localuri.csv")).withSkipLines(1)
                    .build();

            CSVReader readerb = new CSVReaderBuilder(new FileReader("D:\\documente\\An2_sem2\\ProgrAvansOb\\Proiect\\Bauturi.csv")).withSkipLines(1)
                    .build();

            CSVReader readerd = new CSVReaderBuilder(new FileReader("D:\\documente\\An2_sem2\\ProgrAvansOb\\Proiect\\Deserturi.csv")).withSkipLines(1)
                    .build();

            CSVReader readerf = new CSVReaderBuilder(new FileReader("D:\\documente\\An2_sem2\\ProgrAvansOb\\Proiect\\FelPrincipal.csv")).withSkipLines(1)
                    .build();

            List<String[]> toateAdresele = reader1.readAll(); //citesc toate datele
             List<String[]> toateLocalurile = reader2.readAll();

             int contor = 0;

            for (String[] linie : toateLocalurile) {
                //String strada, Integer nr, Integer bloc, String scara, Integer apartament, String localitate, String judet, Integer sector, String codPostal
                Integer bloc = null;
                String scara = null;
                Integer apartament = null;
                Integer sector = null;
                if(!toateAdresele.get(contor)[2].trim().equals("")){
                    bloc = Integer.parseInt(toateAdresele.get(contor)[2]);
                }

                if(!toateAdresele.get(contor)[3].trim().equals("")){
                    scara = toateAdresele.get(contor)[3];
                }

                if(!toateAdresele.get(contor)[4].equals("")){
                    apartament = Integer.parseInt(toateAdresele.get(contor)[4]);
                }

                Adresa adresa = new Adresa(toateAdresele.get(contor)[0], Integer.parseInt(toateAdresele.get(contor)[1]),
                            bloc, scara, apartament, toateAdresele.get(contor)[5], toateAdresele.get(contor)[6], Integer.parseInt(toateAdresele.get(contor)[7]), toateAdresele.get(contor)[8]);
                //System.out.println(adresa);
                Local l =  new Local(adresa, linie[0], new Time(Long.parseLong(linie[1])), new Time(Long.parseLong(linie[2])));
                //System.out.println(l);
                localuri.add(l);
                contor += 1;

                }
            //am term de citit localurile, citesc acum pe rand baturile, deserturile, mancarea si vad la ce local le adaug
            List<String[]> bauturi = readerb.readAll();
            List<String[]> deserturi = readerd.readAll();
            List<String[]> felurip = readerf.readAll();

            //Boolean alcool, Boolean carbogazificate, String denumire, Double pret
            for(String[] linie : bauturi){
                Bauturi bautura = new Bauturi(Boolean.parseBoolean(linie[0]), Boolean.parseBoolean(linie[1]), linie[2], Double.parseDouble(linie[3]));
                //System.out.println(bautura);
                List<String> denumiriLocaluri = Arrays.asList(linie[4].split(","));

                for(Local l : localuri){
                    if(denumiriLocaluri.contains(l.getDenumire())){
                        l.addProdus(bautura);
                    }
                }
            }

            for(String[] linie : deserturi){
                Desert desert = new Desert(Boolean.parseBoolean(linie[0]), linie[1], Double.parseDouble(linie[2]));
                List<String> denumiriLocaluri = Arrays.asList(linie[3].split(","));

                for(Local l : localuri){
                    if(denumiriLocaluri.contains(l.getDenumire())){
                        l.addProdus(desert);
                    }
                }
            }

            for(String[] linie : felurip){

                FelPrincipal felPrincipal = new FelPrincipal(linie[0], linie[1], linie[2], Double.parseDouble(linie[3]));
                List<String> denumiriLocaluri = Arrays.asList(linie[4].split(","));

                for(Local l : localuri){
                    if(denumiriLocaluri.contains(l.getDenumire())){
                        l.addProdus(felPrincipal);
                    }
                }

            }


//                for(Localuri.Local l : localuri){
//                    l.afiseazaProduse();
//                    System.out.println();
//                }

            }
        catch (IOException | CsvException e) {
            e.printStackTrace();
            System.out.println("Nu s-a gasit fisier intrare");
        }

        return localuri;

    }
}
