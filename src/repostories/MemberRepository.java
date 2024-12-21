package repostories;

import models.Book;
import models.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class MemberRepository {

    private static Member createMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("========== Add Member ==========");
        System.out.print("Input member id: ");
        int id = scanner.nextInt();

        // consumes new line from next int
        scanner.nextLine();
        System.out.print("Input member name: ");
        String name = scanner.nextLine();

        return new Member(id, name);
    }

    private static void validateMember(Member member, List<Member> members){
        Scanner scanner = new Scanner(System.in);

        // validate that there isn't any member with similar id
        // if there is a member with same id ask user to change id
        ArrayList<Member> duplicateMembers = new ArrayList<>();
        for (Member currentMember : members) {
            if (currentMember.getId() == member.getId() || Objects.equals(currentMember.getName().toLowerCase(), member.getName().toLowerCase())) {
                duplicateMembers.add(currentMember);
            }
        }
        while (!duplicateMembers.isEmpty()) {
            ArrayList<Member> trashedMembers = new ArrayList<>();

            for (Member currentMember : duplicateMembers) {
                // checks if a member with the same id already exists
                // and then makes sure the user inputs the correct id
                verifyId(member, scanner, trashedMembers, currentMember);

                // checks if a member with the same name already exists
                // and then makes sure the user inputs the correct name
                verifyName(member, currentMember, scanner, trashedMembers);
            }
            boolean isSuccessful = duplicateMembers.removeAll(trashedMembers);
            System.out.println("Edit successful: " + isSuccessful);
        }
    }

    private static void verifyId(Member member, Scanner scanner, ArrayList<Member> trashedMembers, Member currentMember) {
        if (currentMember.getId() == member.getId()) {
            System.out.println("Member with id: " + member.getId() + " already exists");
            System.out.print("Input new member id: ");
            int id = scanner.nextInt();
            if (id != member.getId()) {
                trashedMembers.add(currentMember);
                member.setId(id);
            }
        }
    }

    private static void verifyName(Member member, Member currentMember, Scanner scanner, ArrayList<Member> trashedMembers) {
        if (Objects.equals(currentMember.getName().toLowerCase(), member.getName().toLowerCase())) {
            scanner.nextLine();
            System.out.println("Member with title: " + member.getName() + " already exists");
            System.out.print("Input new member name: ");
            String newName = scanner.nextLine();
            if (!Objects.equals(newName, member.getName().toLowerCase())) {
                trashedMembers.add(currentMember);
                member.setName(newName);
            }
        }
    }


    public static void addMember(List<Member> members) {
        Member member = createMember();

        validateMember(member, members);

        members.add(member);
        System.out.println("Member successfully added\n");
    }


    public static void displayMember (List<Member> members){
        System.out.println("============= Members ==============");
        for (Member member : members) {
            String[] fields = member.toString().split(",");
            System.out.println("Member id: " + fields[0] + ", name: " + fields[1]);
        }
        System.out.println("===================================\n");
    }

    private static int validateMemberId(int id, List<Member> members ){
        Scanner scanner = new Scanner(System.in);

        // validate that there isn't any member with similar id
        // if there is a member with same id ask user to change id
        ArrayList<Member> duplicateMembers = new ArrayList<>();
        for (Member currentMember : members) {
            if (currentMember.getId() == id) {
                duplicateMembers.add(currentMember);
            }
        }
        while (!duplicateMembers.isEmpty()) {
            ArrayList<Member> trashedMembers = new ArrayList<>();

            for (Member currentMember : duplicateMembers) {
                // checks if a member with the same id already exists
                // and then makes sure the user inputs the correct id
                System.out.println("Member with id: " + id + " already exists");
                System.out.print("Input new member id: ");
                int newId = scanner.nextInt();
                if (newId != id) {
                    trashedMembers.add(currentMember);
                    return newId;
                }
            }
            duplicateMembers.removeAll(trashedMembers);
        }
        return id;
    }

    private static void updateMemberId(int id, List<Member> members){
        Scanner scanner = new Scanner(System.in);
        boolean updated = false;
        for (Member member: members){
            if (member.getId() == id){
                System.out.println("Press enter to use current value");
                System.out.print("Input new member id: ");
                String value = scanner.nextLine();
                try {
                    int newId = validateMemberId(Integer.parseInt(value), members);
                    member.setId(newId);
                    updated = true;
                    System.out.println("Member with id: " + id + " updated to: " + newId);
                } catch (NumberFormatException _) {}
            }
        }
        if (!updated){
            System.out.println("No member with id: "  + id + " was found please try again later!");
        }
    }

    private static void updateMemberName(String name, List<Member> members){

    }


    public static void updateMember(List<Member> members) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("============== Update Member ===============");
        System.out.print("Input the id or name of the member: ");
        String value = scanner.nextLine();
        Integer id = null;
        String name = null;
        try {
            id = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            name = value;
        }
        if (id != null){
            updateMemberId(id, members);
        } else if (name != null) {
            updateMemberName(name, members);
        }
    }
}
