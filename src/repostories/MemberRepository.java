package repostories;

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

    private static Member updateMemberId(int id, List<Member> members){
        Scanner scanner = new Scanner(System.in);
        for (Member member: members){
            if (member.getId() == id){
                System.out.println("Press enter to use current value");
                System.out.print("Input new member id: ");
                String value = scanner.nextLine();
                try {
                    int newId = validateMemberId(Integer.parseInt(value), members);
                    member.setId(newId);
                    System.out.println("Member with id: " + id + " updated to: " + newId);
                } catch (NumberFormatException _) {}
                return member;
            }
        }
        System.out.println("No member with id: " + id + " was found please try again later!");
        return null;
    }

    private static String validateMemberName(String name, List<Member> members){
        Scanner scanner = new Scanner(System.in);

        // validate that there isn't any member with similar name
        // if there is a member with same name ask user to change name
        ArrayList<Member> duplicateMembers = new ArrayList<>();
        for (Member currentMember : members) {
            if (Objects.equals(currentMember.getName(), name)) {
                duplicateMembers.add(currentMember);
            }
        }
        while (!duplicateMembers.isEmpty()) {
            ArrayList<Member> trashedMembers = new ArrayList<>();

            for (Member currentMember : duplicateMembers) {
                // checks if a member with the same name already exists
                // and then makes sure the user inputs the correct name
                System.out.println("Member with name: " + name + " already exists");
                System.out.print("Input new member name: ");
                String newName = scanner.nextLine();
                if (!Objects.equals(newName, name)) {
                    trashedMembers.add(currentMember);
                    return newName;
                }
            }
            duplicateMembers.removeAll(trashedMembers);
        }
        return name;
    }

    private static Member updateMemberName(String name, List<Member> members){
        Scanner scanner = new Scanner(System.in);
        for (Member member: members){
            if (Objects.equals(member.getName(), name)){
                System.out.println("Press enter to use current value");
                System.out.print("Input new member name: ");
                String value = scanner.nextLine();
                if (!value.isEmpty()){
                    String newName = validateMemberName(value, members);
                    member.setName(newName);
                    System.out.println("Member with name: " + name + " updated to: " + newName);
                }
                return member;
            }
        }
        System.out.println("No member with name: " + name + " was found please try again later!");
        return null;
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
            Member member = updateMemberId(id, members);
            if (member != null){
                updateMemberName(member.getName(), members);
            }
        } else if (name != null) {
            Member member = updateMemberName(name, members);
            if (member != null){
                updateMemberId(member.getId(), members);
            }
        }
    }

    public static void deleteMember(List<Member> members) {
        System.out.println("========== Delete Member ===========");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input id of member to be deleted: ");
        int memberId = scanner.nextInt();
        boolean memberDeleted = members.removeIf(member -> member.getId() == memberId );
        if (memberDeleted){
            System.out.println("Member has been deleted\n");
        } else {
            System.out.println("Member with id: " + memberId + " was not found please try again later!\n");
        }
    }
}
