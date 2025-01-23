package service;

import models.Book;
import models.BookMember;
import models.Member;
import repostories.BookRepository;
import repostories.MemberRepository;

import java.util.*;

public class LibraryManager {
    public static void addEntity(List<Integer> steps, ArrayList<Book> books, ArrayList<Member> members){
        int choice = Menu.displayAddEntityMenu();
        steps.add(choice);
        switch (choice) {
            case 0, 3:
                break;
            case 1:
                BookRepository.addBook(books);
                break;
            case 2:
                MemberRepository.addMember(members);
                break;
            default:
                System.out.println("Invalid choice, returning to home menu");
                break;
        }

    }

    private static void displayLibraryLedger(List<BookMember> history){
        System.out.println("====== Library Book Borrowers =====");
        for (BookMember bookMember : history) {
            System.out.println(bookMember.displayModel());
        }
        System.out.println("===================================\n");
    }

    /**
     * Deletes the instance of member who borrowed book from the library
     * @param history list of all members and the borrowed books.
     */
    private static void deleteInstanceInHistory(List<BookMember> history){
        System.out.println("========== Delete Lending Instance ===========");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input id of member who borrowed book: ");
        int memberId = scanner.nextInt();

        System.out.print("Input id of book which was borrowed: ");
        int bookId = scanner.nextInt();

        boolean instanceDeleted = history.removeIf(bookMember -> bookMember.getBookId() == bookId && bookMember.getMemberId() == memberId );
        if (instanceDeleted){
            System.out.println("Borrowing instance deleted\n");
        } else {
            System.out.println("Instance with member id: " + memberId +  " and book id: " + bookId +" was not found please try again later!\n");
        }
    }

    /**
     * Displays all entities in the system
     * @param books books in the library
     * @param members List of members registered in the library system
     * @param history List of members who borrowed books and the books they borrowed
     */
    public static void showAllEntities( List<Book> books, List<Member> members, List<BookMember> history) {
        BookRepository.displayBooks(books);
        MemberRepository.displayMember(members);
        displayLibraryLedger(history);
    }

    private static void updateEntities(List<Integer> steps,  List<Book> books, List<Member> members, List<BookMember> history){
        int choice = Menu.displayUpdateEntityMenu();
        steps.add(choice);
        switch (choice) {
            case 0, 3:
                break;
            case 1:
                MemberRepository.updateMember(members);
                break;
            case 2:
                BookRepository.updateBook(steps, books);
                break;
            default:
                System.out.println("Invalid choice, returning to home menu");
                break;
        }
    }
    private static void deleteEntities(List<Integer> steps,  List<Book> books, List<Member> members, List<BookMember> history) {
        int choice = Menu.displayDeleteEntityMenu();
        steps.add(choice);
        switch (choice) {
            case 0, 4:
                break;
            case 1:
                MemberRepository.deleteMember(members);
                break;
            case 2:
                BookRepository.deleteBook(books);
                break;
            case 3:
                deleteInstanceInHistory(history);
                break;
            default:
                System.out.println("Invalid choice, returning to home menu");
                break;
        }
    }

    public static void performInteraction(List<Integer> steps, ArrayList<Book> books, ArrayList<Member> members, ArrayList<BookMember> history){
        int choice = Menu.displayPerformInteractionMenu();
        steps.add(choice);
        switch (choice) {
            case 0, 5:
                break;
            case 1:
                updateEntities(steps, books, members, history);
                break;
            case 2:
                deleteEntities(steps, books, members, history);
                break;
            case 3:
                BookRepository.borrowBook(books, members, history);
                break;
            case 4:
                BookRepository.returnBook(books, history);
                break;
            default:
                System.out.println("Invalid choice, returning to home menu");
                break;
        }
    }

    public static void report(ArrayList<BookMember> history, ArrayList<Book> books) {
        Map.Entry<Integer, Integer> highest = getMostBorrowedBookId(history);
        Book highestBorrowedBook = null;
        for (Book book : books) {
            if (highest != null && highest.getKey() == book.getId()){
                highestBorrowedBook = book;
            }
        }

        if (highestBorrowedBook != null){
            System.out.println("Highest borrowed book is: " + highestBorrowedBook.getTitle());
        } else {
            System.out.println("No book has been borrowed");
        }
    }

    private static Map.Entry<Integer, Integer> getMostBorrowedBookId(ArrayList<BookMember> history) {
        Map<Integer, Integer> metrics = new HashMap<>();
        for (BookMember bookMember : history) {
            int bookId = bookMember.getBookId();
            Integer bookCount = metrics.get(bookId);
            int count = bookCount == null ? 0 : bookCount + 1;
            metrics.put(bookId, count);
        }

        Map.Entry<Integer, Integer> highest = null;

        for (Map.Entry<Integer, Integer> subSet : metrics.entrySet()){
            if (highest == null){
                highest = subSet;
            } else if (highest.getValue() < subSet.getValue()){
                highest = subSet;
            }
        }
        return highest;
    }
}
