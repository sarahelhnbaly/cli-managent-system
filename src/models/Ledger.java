package models;

import java.util.ArrayList;

public class Ledger {
    private final ArrayList<BookMember> history;

    public Ledger(ArrayList<BookMember> history ) {
        this.history = history;
    }

    public Ledger() {
        this.history = new ArrayList<>();
    }

    public ArrayList<BookMember> getHistory() {
        return history;
    }

    public void addMemberHistory(BookMember member) {
        this.history.add(member);
    }
    public void removeMemberHistory(BookMember member) {
        history.removeIf(bookMember -> bookMember.equals(member));
    }

    @Override
    public String toString() {
        return "Ledger{" +
                "history=" + history.toString() +
                '}';
    }
}
