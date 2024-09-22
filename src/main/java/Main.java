
// Librerias de java.
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

// He centrado la gran mayoría de procedimientos en la clase Main.
public class Main {
    // La función "menu" se encarga de que sea más fácil mostrarle al usuario las opciones dadas en el menu.
    public static String menu(){
        System.out.println("What would you like to do?\n    1. Add a book\n    2. Mark as read\n    3. List all read books\n    4. Search by theme\n    5. Exit\n\n Choose an option: ");
        return scan.nextLine();
    }

    //ArrayList general donde se guardan los datos del los libros.
    public static ArrayList<Book> books = new ArrayList<>();
    public static Scanner scan = new Scanner(System.in);

    // Main, donde se ejecuta el código.
    public static void main(String []args){
        int menu_option;
        System.out.println("Welcome to your reading list!\n");

        //Este "do while" selecciona la opción de menu que el usuario ha introducido, en el caso de que elija la quinta
        // opción el programa mostrará un mensaje de salida y saldrá del programa.
        do {
            menu_option = Integer.parseInt(menu());
            switch (menu_option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    markAsRead();
                    break;
                case 3:
                    listReadBooks();
                    break;
                case 4:
                    searchByTheme();
                    break;
            }

        }while(menu_option != 5);
        System.out.println("Closing your personal book list...");
    }

    // La función "addBook" se encarga de pedir al usuario los datos principales para añadir esos mismos datos
    // en un listado donde se almacenarán todos los libros. Tiene excepciones en el año y la popularidad.
    public static void addBook(){
        System.out.println("Title: ");
        String title = scan.nextLine();
        System.out.println("Author: ");
        String artist = scan.nextLine();
        System.out.println("Year of publication: ");
        int year = askForYear();
        System.out.println("Popularity: ");
        int stars = askForRate();
        System.out.println("Themes [x,y,...,z] : ");
        ArrayList<String> comas = askForComas();
        books.add(new Book(title, artist, year, stars, comas));
        System.out.println("Book added successfully!");
    }

    // La función "askForComas" se encarga de guardar los datos introducidos en theme de forma que no lea las comas
    // como si fuese información importante.
    private static ArrayList<String> askForComas() {
        String comasList = scan.nextLine();
        String[] separatedTags = comasList.split(",");
        return new ArrayList<>(Arrays.asList(separatedTags));
    }

    // La función "askForRate" se encarga de crear excepciones en caso de que el dato de popularidad introducido no sea
    // el esperado en el código, dando la opción de volver a introducir ese dato de la forma correcta.
    private static int askForRate() {
        int stars;
        while(true){
            try{
                stars = Integer.parseInt(scan.nextLine());
                if(stars >= 1 && stars <= 5){
                    return stars;
                }else{
                    System.out.println("    The popularity must be an integer between 1 and 5.");
                    System.out.println("    Enter a valid popularity: ");
                }


            }catch (NumberFormatException e){
                System.out.println("    Invalid format for popularity, it must be an integer.");
                System.out.println("    Enter a valid rate: ");
            }
        }
    }

    // La funcion "askForYear" tiene exactamente el mismo propósito que "askForRate".
    private static int askForYear() {
        int year;
        while(true){
            try{
                year = Integer.parseInt(scan.nextLine());
                if(0 < year){
                    return year;
                }else{
                    System.out.println("    Year must be positive.");
                    System.out.println("    Enter a valid year: ");
                }
            }
            catch (NumberFormatException e){
                System.out.println("    Invalid format.");
                System.out.println("    Enter a valid year: ");
            }
        }
    }

    // La función "markAsRead" se encarga de pedir al usuario que libro quiere marcar como leido. También tiene
    // integrada la función de "findBookAndMarkAsRead" de la línea 121.
    private static void markAsRead(){
        System.out.println("Enter title: ");
        String titleBook = scan.nextLine();
        findBookAndMarkAsRead(titleBook);

    }

    // la función "findBookAndMarkAsRead" se encarga de recoger el dato que ha introducido el usuario en "markAsRead" y
    // con el dato de formato String, busca en el listado el mismo libro, y si es igual marca ese libro como leido.
    private static void findBookAndMarkAsRead(String titleBook){
        for (Book book : books) {
            if (book.getTitle().equals(titleBook)) {
                if (book.getRead() == 0) {
                    System.out.println("The book '" + titleBook + "' marked as read!\n");
                    book.updateRead();
                    return;
                } else {
                    System.out.println("The book '" + titleBook + "'is already marked as read.\n");
                }
            } else {
                System.out.println("The book '" + titleBook + "' does not exist in your list.\n");
            }
        }
    }

    // La función "listReadBooks" se encarga de hacer un listado con todos los libros que el usuario haya
    // seleccionado de antemano como leidos.
    private static void listReadBooks() {
        if (howManyReadBooks()){
            for (Book book : books) {
                System.out.printf("- %s by %s (%d)%n", book.getTitle(), book.getArtist(), book.getYear());
            }
        }else{
            System.out.println("ERROR, no read books, read more...\n");
        }

    }

    // La función booleana de "howManyReadBooks" se encarga de devolver un dato booleano que será destinado en la
    // función "listReadBooks", para poder saber si hay algun libro marcado como leido,
    // de lo contrario notificará al usuario.
    private static boolean howManyReadBooks(){
        for (Book book : books) {
            return book.getRead() > 0;

        }
        return false;

    }

    // La función "searchByTheme" se encarga de pedir al usuario un tema para después hacer un listado de
    // todos los libros que tinen ese mismo tema. En el caso de no haber ese tema para ningún libro del listado,
    // el usuario será notificado.
    private static void searchByTheme(){
        System.out.println("Enter the theme you want to search for: ");
        String theme = scan.nextLine();

        ArrayList<Book> themeBooks = new ArrayList<>();

        for (Book book : books){
            if (book.getComas().contains(theme)){
                themeBooks.add(book);

            }
        }

        if (themeBooks.isEmpty()){
            System.out.println("There are no books with theme '"+theme+"'.\n");

        }else{
            System.out.println("Books with '"+theme+"':");
            for (Book book : themeBooks){
                System.out.printf("-%s by %s (%d) - (%d)\n", book.getTitle(), book.getArtist(), book.getYear(), book.getStars());
            }
            System.out.println("\n");
        }

    }

}


