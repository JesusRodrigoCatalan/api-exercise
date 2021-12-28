package jesus.rodrigo.models;

public class PetCategory {

    private long id;
    private String name;

    public PetCategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PetCategory(){
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
