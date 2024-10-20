package ao.com.costs.costswebapi.enums;

public enum AuthProvider {
    GOOGLE("GOOGLE"),
    GITHUB("GITHUB");

    private String authProvider;

    private AuthProvider(String AuthProvider){
        this.authProvider = AuthProvider;
    }
}
