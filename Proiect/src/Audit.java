import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class Audit {

    private static Audit instance;
    private final String caleFisier;

    private Audit(String caleFisier){

        this.caleFisier = caleFisier;
        File file = new File(caleFisier);

        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {

            String[] header = {"Nume_actiune", "timestamp"};
            writer.writeNext(header);


        } catch (IOException e) {
            System.out.println("Nu s-a gasit fisierul CVS de loggin!");
        }

    }

    public static Audit getInstance(String caleFisier) {
        if (instance == null) {
            instance = new Audit(caleFisier);
        }
        return instance;
    }

    public void scrieFisierLoggin(){

        try (CSVWriter writer = new CSVWriter(new FileWriter(caleFisier, true))) {
            String nameofCurrMethod = new Throwable().getStackTrace()[1].getMethodName();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String[] data = {nameofCurrMethod, String.valueOf(timestamp)};

            writer.writeNext(data);



        } catch (IOException e) {
            System.out.println("Nu s-a gasit fisierul CVS de loggin!");
        }



    }
}
