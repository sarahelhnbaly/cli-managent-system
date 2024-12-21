package service;

import models.Book;
import models.BookMember;
import models.Member;
import repostories.BookRepository;
import repostories.MemberRepository;

import java.util.ArrayList;
import java.util.List;

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

    public static void showAllEntities(List<Integer> steps, List<Book> books, List<Member> members, List<BookMember> history) {
        BookRepository.displayBooks(books);
        MemberRepository.displayMember(members);
        System.out.println(history);
    }

    private static void updateEntities(List<Integer> steps,  List<Book> books, List<Member> members, List<BookMember> history){
        int choice = Menu.displayUpdateEntityMenu();
        steps.add(choice);
        switch (choice) {
            case 0, 4:
                break;
            case 1:
                MemberRepository.updateMember(members);
                break;
            case 2:
                BookRepository.updateBook(books);
                break;
            case 3:
                System.out.println("Not yet implemented");
                break;
            default:
                System.out.println("Invalid choice, returning to home menu");
                break;
        }
    }
    private static void deleteEntities(){

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
                deleteEntities();
                break;
            case 3:
                BookRepository.borrowBook(books);
                break;
            case 4:
                BookRepository.returnBook(books);
                break;
            default:
                System.out.println("Invalid choice, returning to home menu");
                break;
        }
    }
}
