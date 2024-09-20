package ao.com.costs.costswebapi.enums;

public enum Category {
    DEVELOPMENT("Development"),
    INFRASTRUCTURE("Infrastructure"),
    PLANNING("Planning");

    private String category;

    private Category(String category){
        this.category = category;
    }
}
