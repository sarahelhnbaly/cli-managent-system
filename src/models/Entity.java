package models;

/**
 * Entity to be used for classifying all models in our system
 * to make them easy to store in files
 */
public interface Entity {
    /**
     * Converts all models in our library system from string to their respective entity
     * @param data loaded from a csv file
     * @return the respective entity i.e. Member, History, BookMember
     */
    public Entity convertStringToEntity(String data);
}
