import models.Book;
import models.Ledger;
import models.Member;
import service.LibraryManager;
import service.Menu;
import service.PersistData;

import java.util.*;


public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> steps = new ArrayList<>();

        // Entities
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Member> members = new ArrayList<>();
        Ledger ledger = new Ledger();

        // loads Entities from files
        PersistData.loadEntities(books, members, ledger.getHistory());

        // exits when the user selects 0 - for exiting
        while(!steps.contains(0)){
            try {
                int choice = Menu.displayMenu();
                steps.add(choice);
                switch (choice){
                    case 0:
                        break;
                    case 1:
                        LibraryManager.addEntity(steps, books, members);
                        break;
                    case 2:
                        LibraryManager.showAllEntities( books, members, ledger.getHistory());
                        break;
                    case 3:
                        LibraryManager.performInteraction(steps, books, members, ledger.getHistory());
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            } catch (InputMismatchException mismatchException) {
                System.out.println("Invalid input, please try again");
            }
        }
        // saves All entities to file
        PersistData.saveEntities(books, members, ledger.getHistory());
    }
}