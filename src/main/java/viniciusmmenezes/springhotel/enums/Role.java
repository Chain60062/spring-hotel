package viniciusmmenezes.springhotel.enums;

public enum Role {
    ADMIN("ADMIN"), USER("USER");

    private String role;

    private Role(String role){
        this.role = role;
    }
}
