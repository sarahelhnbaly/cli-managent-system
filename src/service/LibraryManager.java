package service;

import models.Book;
import models.BookMember;
import models.Member;
import repostories.BookRepository;
import repostories.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
}
