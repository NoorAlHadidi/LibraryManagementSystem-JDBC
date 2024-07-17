import Model.*;
import Service.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        BookService bookServ = new BookService();
        AuthorService authorServ = new AuthorService();
        CustomerService custServ = new CustomerService();
        //checking availability and adding of book and author
        System.out.println("Enter the title of the book you'd like to add:");
        String bookTitle;
        while(true) {
            bookTitle = scanner.nextLine();
            if(bookServ.findBook(bookTitle).isEmpty()) {
                System.out.println("Enter the book's author's first and last name:");
                String bookAuthor = scanner.nextLine();
                String[] authorDetails = bookAuthor.split(" ");
                if(authorServ.findAuthor(authorDetails[0], authorDetails[1]).isEmpty()) {
                    Author author = new Author(0, authorDetails[0], authorDetails[1]);
                    authorServ.addAuthor(author);
                }
                int bookAuthorID = (authorServ.findAuthor(authorDetails[0], authorDetails[1]).get()).getID();
                System.out.println("Enter the book's genre:");
                String bookGenre = scanner.nextLine();
                System.out.println("Enter the book's publish date:");
                String bookRel = scanner.nextLine();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date bookRelease = new java.sql.Date(formatter.parse(bookRel).getTime());
                Book newBook = new Book(0, bookTitle, bookAuthorID, bookGenre, bookRelease);
                bookServ.addBook(newBook);
                break;
            }
            System.out.println("Book is already added, enter another title:");
        }
        //removing a book
        System.out.println("Enter the title of the book you'd like to remove:");
        String bookRemove;
        while(true) {
            bookRemove = scanner.nextLine();
            if(bookServ.findBook(bookRemove).isPresent()) {
                bookServ.removeBook((bookServ.findBook(bookRemove)).get());
                break;
            }
            System.out.println("Book does not exist, enter another title:");
        }
        //displaying all books
        System.out.println("Displaying all books");
        bookServ.displayBooks();
        //retrieving books by a certain author
        System.out.println("Enter the first and last name of the desired author:");
        String retAuthor;
        while(true) {
            retAuthor = scanner.nextLine();
            String[] retAuthorDetails = retAuthor.split(" ");
            if(authorServ.findAuthor(retAuthorDetails[0], retAuthorDetails[1]).isPresent()) {
                bookServ.retrieveBook((authorServ.findAuthor(retAuthorDetails[0], retAuthorDetails[1])).get());
                break;
            }
            System.out.println("Author does not exist, enter correct author name:");
        }
    }
}
