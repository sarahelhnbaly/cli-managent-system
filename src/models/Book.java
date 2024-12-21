package models;

public class Book implements Entity {
    private int id;
    private String title;
    private String author;
    private int availableCopies;

    public Book(int id, String title, String author, int availableCopies) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Book(){
        // prototype for book
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public boolean isAvailable(){
        return availableCopies > 0;
    }
    public void borrow(){
        availableCopies--;
    }
    public void returnBook(){
        availableCopies++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return id + "," + title + "," + author + "," + availableCopies + "\n" ;
    }

    @Override
    public Book convertStringToEntity(String data) {
        String[] fields = data.replace("\n", "").split(",");
        return new Book(
                Integer.parseInt(fields[0]),
                fields[1],
                fields[2],
                Integer.parseInt(fields[3])
        );
    }
}
