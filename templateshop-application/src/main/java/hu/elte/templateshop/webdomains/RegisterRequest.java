package hu.elte.templateshop.webdomains;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegisterRequest {
    private String username;
    @Size(min=3,max=20, message="Email is required")
    @Email
    @NotEmpty()
    private String email;
    @Size(min=2,max=10, message="2 <= Password length <= 10")
    @NotEmpty()
    private String password;
    @Size(min=2,max=10, message="2 <= Confirmation Password <= 10")
    @NotEmpty()
    private String confirmPassword;


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
