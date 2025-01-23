package repostories;

import models.Book;
import models.BookMember;
import models.Member;
import service.Menu;

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

    /**
     * Ensures no book with same title exist since all book titles are to be unique
     * @param title new title of book supplied by the user
     * @param books books in the library
     * @return either null or a Book instance
     */
    public static Book validateBookTitle(String title, List<Book> books){
        for (Book currentBook : books) {
            if (Objects.equals(title.toLowerCase(), currentBook.getTitle().toLowerCase())){
                System.out.println("Title already exists");
                return currentBook;
            }
        }
        return null;
    }

    /**
     * Updates the books title to a new title based on the user
     * @param book book to be updated
     * @param books books in library
     */
    public static void updateBookTitle(Book book, List<Book> books){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Update Book title: " + book.getTitle() + " ===========");
        System.out.print("Input new book Title: ");
        String newTitle = scanner.nextLine();
        Book duplicateBook = validateBookTitle(newTitle, books);

        if (duplicateBook != null){
            while (duplicateBook != null){
                System.out.print("Input new book title: ");
                newTitle = scanner.nextLine();
                duplicateBook = validateBookTitle(newTitle, books);
            }
        }
        book.setTitle(newTitle);
        System.out.println("Book successfully updated\n");
    }

    /**
     * Validates the book id to ensure the user doesn't put duplicate id
     * @param id the newId supplied by the user
     * @param books the list of books in the library to use for validation
     * @return It returns null or the current book if a duplicate book is found
     */
    public static Book validateBookId(int id, List<Book> books){
        for (Book currentBook : books) {
            if (id == currentBook.getId()) {
                System.out.println("Id already exists");
                return currentBook;
            }
        }
        return null;
    }

    /**
     * Updates the book author to the author the user inputs
     * @param book book to be updated
     */
    public static void updateBookAuthor(Book book){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Update Book Author: " + book.getAuthor() + " ===========");
        System.out.print("Input new book Author: ");
        String newAuthor = scanner.nextLine();
        book.setAuthor(newAuthor);
        System.out.println("Book successfully updated\n");
    }

    /**
     * Updates the book id based on input from
     * user and also checks if there is a book with the same id so as not to have duplicate ids
     * @param book The book to be updated
     * @param books books in library to validate the id of the book selected
     */
    public static void updateBookId(Book book, List<Book> books){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Update Book Id: " + book.getId() + " ===========");
        System.out.print("Input new book id: ");
        int newId = scanner.nextInt();
        Book duplicateBook = validateBookId(newId, books);

        if (duplicateBook != null){
            while (duplicateBook != null){
                System.out.print("Input new book Id: ");
                newId = scanner.nextInt();
                duplicateBook = validateBookId(newId, books);
            }
        }
        book.setId(newId);
        System.out.println("Book successfully updated\n");
    }

    /**
     * Updates book count when user wants to update the amount of available copies of a book
     * @param book book to update copies count
     */
    public static void updateBookCount(Book book){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Update Book count ===========");
        System.out.print("Input number of available copies: ");
        int newCount = scanner.nextInt();
        book.setAvailableCopies(newCount);
        System.out.println("Book successfully updated\n");
    }

    /**
     * Updates the book in library system
     * @param steps used for tracking user input
     * @param books books in library
     */
    public static void updateBook(List<Integer> steps, List<Book> books){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input book id: ");
        int bookId = scanner.nextInt();
        Book selectedBook = null;
        for (Book book : books){
            if (book.getId() == bookId) {
                selectedBook = book;
            }
        }
        if (selectedBook == null) {
            System.out.println("Book not found");
            return;
        }
        int choice = Menu.displayUpdateBookMenu(bookId);
        steps.add(choice);
        scanner.nextLine();
        switch (choice){
            case 0:
                break;
            case 1:
                updateBookTitle(selectedBook, books);
                break;
            case 2:
                updateBookAuthor(selectedBook);
                break;
            case 3:
                updateBookId(selectedBook, books);
                break;
            case 4:
                updateBookCount(selectedBook);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    /**
     * Method for implementation fo borrowing books from the library system
     * @param books arraylist of reference to books instances for easy updating
     * @param members arraylist of reference to members who are in the library system that might borrow from library
     * @param history arraylist of reference to bookMembers instances for easy
     *                updating for members who borrow from library
     */
    public static void borrowBook (ArrayList<Book> books, ArrayList<Member> members, ArrayList<BookMember> history){
        Scanner scanner = new Scanner(System.in);
        System.out.println("============= Borrow Book =============");
        System.out.print("Input member id: ");
        int memberId = scanner.nextInt();
        Member selectedMember = null;
        for (Member member : members) {
            if (member.getId() == memberId){
                selectedMember = member;
            }
        }
        if (selectedMember != null){
            scanner.nextLine();
            System.out.println("Input id of book,");
            System.out.print("which " + selectedMember.getName() + " wants to borrow: ");
            int bookId = scanner.nextInt();
            Book selectedBook = null;
            for (Book book : books) {
                if (book.getId() == bookId){
                    if (!book.isAvailable()){
                        System.out.println("That book is not available");
                    } else {
                        selectedBook = book;
                    }
                }
            }
            if (selectedBook != null){
                BookMember bookMember = new BookMember(selectedBook.getId(), selectedMember.getId(), false);
                if (history.contains(bookMember)){
                    System.out.println("Member already borrowed particular book without returning,\n" +
                            "Please return current book before borrowing again!!!");
                } else {
                    selectedBook.borrow();
                    history.add(bookMember);
                    System.out.println("Member added to ledger!!!");
                }
            }
        } else {
            System.out.println("Member not found!, please try again later.");
        }

    }

    /**
     * Method responsible for users returning books to book library system
     * @param books list of books in the library
     * @param history array list of books linked to members who borrowed those books
     */
    public static void returnBook(ArrayList<Book> books, ArrayList<BookMember> history){
        Scanner scanner = new Scanner(System.in);
        System.out.println("============= Return Book =============");
        System.out.print("Input id of member that wants to return book: ");
        int memberId = scanner.nextInt();
        // empty list of books borrowed by member
        ArrayList<BookMember> selectedRecords = new ArrayList<>();
        for (BookMember bookMember : history) {
            if (bookMember.getMemberId() == memberId){
                selectedRecords.add(bookMember);
            }
        }
        if (selectedRecords.isEmpty()){
            System.out.println("Member with id: " + memberId + " hasn't borrowed any book yet");
        } else {
            // if member has several books which have been borrowed display them
            // so the librarian can see the book id that needs to be returned
            System.out.println("Which book wants to be returned:");
            for (BookMember bookMember : selectedRecords) {
                Book selectedBook = null;
                for (Book book : books) {
                    if (book.getId() == bookMember.getBookId()){
                        selectedBook = book;
                    }
                }
                if (selectedBook != null){
                    System.out.println("Book id: " + selectedBook.getId() + ", Book title: " + selectedBook.getTitle());
                }
            }
            System.out.print("Input book id: ");
            int bookId = scanner.nextInt();
            boolean returned = false;
            // updates library records to reflect that the user
            // has returned the book if book id inputted was valid
            for (BookMember record : selectedRecords) {
                if (record.getBookId() == bookId){
                    history.remove(record);
                    for (Book book : books) {
                        if (book.getId() == bookId){
                            book.returnBook();
                            System.out.println("Book returned successfully");
                            returned = true;
                        }
                    }
                }
            }
            if (!returned){
                System.out.println("Book with bookId: " + bookId + " has not been borrowed by member.");
            }
        }
    }

    /**
     * Displays the books in readable format for user
     * @param books list of books in library system
     */
    public static void displayBooks (List<Book> books){
        System.out.println("============= Books ==============");
        for (Book book : books){
            String[] values = book.toString().split(",");
            System.out.println("Book id: " + values[0] + ", Book title: " + values[1] + ", Book author: " + values[2] + ", Book count: " + values[3]);
        }
        System.out.println("===================================\n");
    }

    /**
     * Deletes book from the library if it exists
     * else it tells the user the book doesn't exist
     * @param books books to be searched for the book to be deleted
     */
    public static void deleteBook(List<Book> books) {
        System.out.println("========== Delete Book ===========");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input id of book to be deleted: ");
        int bookId = scanner.nextInt();
        boolean bookDeleted = books.removeIf(book -> book.getId() == bookId );
        if (bookDeleted){
            System.out.println("Book has been deleted\n");
        } else {
            System.out.println("Book with id: " + bookId + " was not found please try again later!\n");
        }
    }
}
