package repostories;

import models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BookRepository {

    /**
     * Creates the new book based on the
     * various inputs supplied by the user of the system
     * @return A book object representing the book the user created
     */
    private static Book createBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Add Book ==========");
        System.out.print("Input book id: ");
        int id = scanner.nextInt();

        // consumes new line from next int
        scanner.nextLine();
        System.out.print("Input book name: ");
        String bookName = scanner.nextLine();

        System.out.print("Input book author: ");
        String bookAuthor = scanner.nextLine();

        System.out.print("Input number of copies: ");
        int bookCount = scanner.nextInt();

        return new Book(id, bookName, bookAuthor, bookCount);
    }

    /**
     * Validates the new book to make sure it
     * doesn't already exist in our System
     * @param book The newly created book
     * @param books The list of books in our system
     */
    private static void validateBook( Book book, List<Book> books){
        Scanner scanner = new Scanner(System.in);

        // validate that there isn't any book with similar id
        // if there is a book with same id ask user to change id
        ArrayList<Book> duplicateBooks = new ArrayList<>();
        for (Book currentBook : books) {
            if (currentBook.getId() == book.getId() || Objects.equals(currentBook.getTitle().toLowerCase(), book.getTitle().toLowerCase())) {
                duplicateBooks.add(currentBook);
            }
        }
        while (!duplicateBooks.isEmpty()) {
            ArrayList<Book> trashedBooks = new ArrayList<>();

            for (Book currentBook : duplicateBooks) {
                // checks if a book with the same id already exists
                // and then makes sure the user inputs the correct id
                if (currentBook.getId() == book.getId()) {
                    System.out.println("Book with id: " + book.getId() + " already exists");
                    System.out.print("Input new book id: ");
                    int id = scanner.nextInt();
                    if (id != book.getId()) {
                        trashedBooks.add(currentBook);
                        book.setId(id);
                    }
                }

                // checks if a book with the same title already exists
                // and then makes sure the user inputs the correct title
                if (Objects.equals(currentBook.getTitle().toLowerCase(), book.getTitle().toLowerCase())) {
                    scanner.nextLine();
                    System.out.println("Book with title: " + book.getTitle() + " already exists");
                    System.out.print("Input new book title: ");
                    String newTitle = scanner.nextLine();
                    if (!Objects.equals(newTitle, book.getTitle())) {
                        trashedBooks.add(currentBook);
                        book.setTitle(newTitle);
                    }
                }
            }
            boolean isSuccessful = duplicateBooks.removeAll(trashedBooks);
            System.out.println(isSuccessful);
        }
    }


    /**
     * Adds a new book to the ArrayList of books
     * @param books the list of books the new book should be appended to
     */
    public static void addBook( ArrayList<Book> books){

        Book book = createBook();

        validateBook(book, books);

        books.add(book);
        System.out.println("Book successfully added\n");
    }

    public static void borrowBook (ArrayList<Book> books){

    }

    public static void returnBook(ArrayList<Book> books){

    }

    public static void displayBooks (List<Book> books){
        System.out.println("============= Books ==============");
        for (Book book : books){
            String[] values = book.toString().split(",");
            System.out.println("Book id: " + values[0] + ", Book title: " + values[1] + ", Book author: " + values[2] + ", Book count: " + values[3]);
        };
        System.out.println("===================================\n");
    }
}
