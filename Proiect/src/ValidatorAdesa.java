import java.util.Objects;

public class ValidatorAdesa {

    public Boolean Validare(Adresa adresa){
        if(CodPostal(adresa.getCodPostal()) && LocalitateSector(adresa.getLocalitate(),adresa.getJudet(), adresa.getSector()) && NrBloc(adresa.getNr(), adresa.getBloc(),adresa.getApartament())){
            System.out.println("Adresa corecta");
            return true;
        }
        else{
            System.out.println("Adresa incorecta!");
            return false;
        }


    }

    private Boolean LocalitateSector(String localitate, String judet, Integer sector){
        if(sector > 6)
            return false;

        if(Objects.equals(localitate,"Bucuresti") && !Objects.equals(judet,"Bucuresti"))//(judet != "Bucuresti" || judet != ""))
            return false;

       // if(sector == null && Objects.equals(localitate,"Bucuresti"))
         //   return  false;

        if(localitate.isEmpty() || judet.isEmpty() || localitate.isBlank() || judet.isBlank())
            return false;


        return judet.matches("^[A-Z][a-z]+");
    }

    private Boolean NrBloc(Integer nr, Integer bl, Integer ap){
        if(nr == null && bl == null) {
            return false;

        }

        if(bl != null && ap == null) {
            return false;
        }

        return true;
    }

    private Boolean CodPostal(String cod){
        if(cod.matches("^[0-9]{6}"))
             return true;
        return false;
    }
}
