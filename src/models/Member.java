package models;

public class Member implements Entity {
    private int id;
    private String name;

    public Member(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member(){
        // prototype
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "," + name + "\n";
    }

    @Override
    public Member convertStringToEntity(String data) {
        String[] fields = data.replace("\n", "").split(",");
        return new Member(
                Integer.parseInt(fields[0]),
                fields[1]
        );
    }
}
