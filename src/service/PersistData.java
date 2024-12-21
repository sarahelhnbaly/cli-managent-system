package service;

import models.Book;
import models.BookMember;
import models.Entity;
import models.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistData {

    /**
     *  Method for saving entities from our cli management system
     *  it collects the different entities books, members and history
     *  while appending to the list it shows up in all parts of the system
     *  due to lists being linked in state
     */
    public static void saveEntities(List<Book> books, List<Member> members, List<BookMember> history) {

        String membersPath = "members.csv";
        String booksPath = "books.csv";
        String bookMembersPath = "bookMembers.csv";

        try {
            writeFile(membersPath, "id,name\n", convertListToString(members));
            writeFile(booksPath, "id,title,author,count\n", convertListToString(books));
            writeFile(bookMembersPath, "bookId,memberId,returned\n", convertListToString(history));
        } catch (IOException ioException) {
            System.err.println("An error occurred while saving entities.");
            ioException.printStackTrace();
        }
    }


    /**
     * Encapsulated logic for writing the entities to the filepath provided
     * files are stored in csv format to provide easy access and retrieval of data
     * @param filePath Path for writing data to file
     * @param header The heading row for the csv file
     * @param content The content of each entity
     * @throws IOException error thrown when while trying to write to file
     */
    private static void writeFile(String filePath, String header, String content) throws IOException {
        File file = new File(filePath);
        try (FileWriter writer = new FileWriter(file)) { // Append mode if file exists
            writer.write(header);
            writer.write(content);
        }
    }

    /**
     * Converts List of Entities to string
     * @param entities the List entities we want to convert and compile to string
     * @return A String of all the entities in list compiled
     * @param <T> generic Entity to keep code dry
     */
    private static <T> String convertListToString(List<T> entities) {
        StringBuilder data = new StringBuilder();
        for (T entity : entities) {
            data.append(entity.toString());
        }
        return data.toString();
    }

    /**
     * This method is supposed to be called at the beginning of the system
     * initialization to load the persisted entities from the various files
     * in which they are stored in
     * @param books List of books instances
     * @param members List of members instances
     * @param history List of BookMember instances
     */
    public static void loadEntities(ArrayList<Book> books, ArrayList<Member> members, ArrayList<BookMember> history) {
        String membersPath = "members.csv";
        String booksPath = "books.csv";
        String bookMembersPath = "bookMembers.csv";
        readCSV(booksPath, books, new Book());
        readCSV(membersPath, members, new Member());
        readCSV(bookMembersPath, history, new BookMember());
        System.out.println("============== Data successfully retrieved ===================");
    }

    /**
     * Generic encapsulated method to read csv files, it is
     * used in the loadEntities method to read the various files and
     * mutate the values of the ArrayList to conform with what is in the
     * csv files
     * @param filePath Path for retrieving data from
     * @param entities Generic argument for appending data from file
     * @param prototype Prototype of Object or model that implements the Entity Interface
     * @param <T> Generic variable to identify the entity
     */
    private static <T extends Entity> void readCSV(String filePath, ArrayList<T> entities, T prototype) {
      // code block for checking if the files exist and creating them if they don't exist
       try {
           File file = new File(filePath);
           boolean isNewFile = file.createNewFile();
           if (isNewFile) {
               System.out.println("Storage file: " + filePath + " created successfully");
           }
       } catch (IOException ioException) {
           System.err.println("An error occurred while creating Storage file: " + filePath);
       }

       // code block for reading files from filepath
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            ArrayList<T> newEntities = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                T entity = (T) prototype.convertStringToEntity(line);
                newEntities.add(entity);
            }
            entities.addAll(newEntities);
        } catch (IOException e) {
            System.err.print("An error occurred while reading " + filePath + "\n");
        }
    }



}
