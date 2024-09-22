package ao.com.costs.costswebapi.enums;

public enum Category {
    DEVELOPMENT("DEVELOPMENT"),
    INFRASTRUCTURE("INFRASTRUCTURE"),
    PLANNING("PLANNING");

    private String category;

    private Category(String category){
        this.category = category;
    }
}
