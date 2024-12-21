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
                if (currentMember.getId() == member.getId()) {
                    System.out.println("Member with id: " + member.getId() + " already exists");
                    System.out.print("Input new member id: ");
                    int id = scanner.nextInt();
                    if (id != member.getId()) {
                        trashedMembers.add(currentMember);
                        member.setId(id);
                    }
                }

                // checks if a member with the same name already exists
                // and then makes sure the user inputs the correct name
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
            boolean isSuccessful = duplicateMembers.removeAll(trashedMembers);
            System.out.println("Edit successful: " + isSuccessful);
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
}
