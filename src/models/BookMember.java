package models;

public class BookMember implements Entity {
    private int bookId;
    private int memberId;
    private boolean returned;

    public BookMember(int bookId, int memberId, boolean returned) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.returned = returned;
    }
    public BookMember() {
        // for prototyping
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        BookMember that = (BookMember) o;
        return bookId == that.bookId && memberId == that.memberId && returned == that.returned;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + memberId;
        result = 31 * result + Boolean.hashCode(returned);
        return result;
    }

    @Override
    public String toString() {
        return bookId + "," + memberId + "," + returned + "\n";
    }

    @Override
    public BookMember convertStringToEntity(String data) {
        String[] fields = data.replace("\n", "").split(",");
        return new BookMember(
                Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Boolean.parseBoolean(fields[2])
        );
    }
}
