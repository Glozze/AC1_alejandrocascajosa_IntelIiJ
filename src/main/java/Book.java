//Libreria de java
import java.util.ArrayList;

// En la clase Book es donde están las variables de los datos que, cuando el programa se ejecute,
// el usuario les dará un valor para cada libro creado.
public class Book {

    // Variables o datos de un libro.
    private final String title;
    private final String artist;
    private final int year;
    private final int stars;
    private int read;
    private final ArrayList<String> comas;

    // Constructor de la clase Book.
    public Book(String title, String artist, int year, int stars, ArrayList<String> comas){
        this.artist = artist;
        this.stars = stars;
        this.year = year;
        this.title = title;
        this.comas = comas;
    }

    // La función "updateRead" se encarga de marcar como leido un libro, o al menos enviar la
    // evidencia a la clase Main de que ese libro está marcado.
    public void updateRead(){
        read = 1;
    }

    // La función "getTitle" se encarga de proporcionar los datos de las variables de la clase book a otras clases.
    // esta función define los demás getters que existen en esta clase.
    public String getTitle(){
        return title;
    }
    public String getArtist(){
        return artist;
    }
    public int getYear(){
        return year;
    }
    public int getStars(){
        return stars;
    }
    public int getRead(){
        return read;
    }
    public ArrayList<String> getComas(){
        return new ArrayList<>(this.comas);
    }
}
