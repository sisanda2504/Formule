package za.ac.cput.dto.users;

public class LoginResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public LoginResponse(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters (no setters if you want it immutable)
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
}
