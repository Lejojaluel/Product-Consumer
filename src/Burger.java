/**
 * Burger Object Class
 *
 * @author Leroy Valencia
 */
public class Burger {
    private String id;
    private BurgerType type;

    public Burger(String id, BurgerType type) {
        this.id = id;
        this.type = type;
    }

    public String toString() {
        return "Burger has id: " + id + " this type " + type;
    }

    //Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setType(BurgerType type) {
        this.type = type;
    }

    //Getters
    public BurgerType getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}