package hu.elte.templateshop.webdomains;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginRequest {

    @Size(min=3,max=20, message="Email is required")
    @Email
    @NotEmpty()
    private String email;
    @Size(min=2,max=10, message="2 <= Password length <= 10")
    @NotEmpty()
    private String password;

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

}
