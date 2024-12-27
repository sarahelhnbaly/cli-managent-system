package service;

import java.util.Scanner;

public class Menu {
    public static int displayMenu(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("========= Library System Management ============");
        System.out.println("1. Add Entity");
        System.out.println("2. Show All Entities");
        System.out.println("3. Perform Interaction");
        System.out.println("0. Exit");
        System.out.print("Input operation: ");
        return scanner.nextInt();
    }

    public static int displayAddEntityMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Add Entity ===========");
        System.out.println("1. Add Book");
        System.out.println("2. Add Member");
        System.out.println("3. Go To Main Menu");
        System.out.println("0. Exit");
        System.out.print("Input operation: ");
        return scanner.nextInt();
    }

    public static int displayPerformInteractionMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Perform Interaction ===========");
        System.out.println("1. Update Entity");
        System.out.println("2. Delete Entity");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Go To Main Menu");
        System.out.println("0. Exit");
        System.out.print("Input operation: ");
        return scanner.nextInt();
    }

    public static int displayUpdateEntityMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Update Entity ===========");
        System.out.println("1. Update Member");
        System.out.println("2. Update Book");
        System.out.println("3. Update Lending History");
        System.out.println("4. Go To Main Menu");
        System.out.println("0. Exit");
        System.out.print("Input operation: ");
        return scanner.nextInt();
    }
    public static int displayDeleteEntityMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Delete Entity ===========");
        System.out.println("1. Delete Member");
        System.out.println("2. Delete Book");
        System.out.println("3. Delete Lending Instance from History");
        System.out.println("4. Go To Main Menu");
        System.out.println("0. Exit");
        System.out.print("Input operation: ");
        return scanner.nextInt();
    }
    public static int displayUpdateBookMenu(int id){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Update Book: " + id + " ===========");
        System.out.println("1. Update book title");
        System.out.println("2. Update book author");
        System.out.println("3. Update book id");
        System.out.println("4. Update book count");
        System.out.println("0. Exit");
        System.out.print("Input operation: ");
        return scanner.nextInt();
    }


}
