
package application;

import javafx.beans.property.*;

public class UserFX {

    private final IntegerProperty userId;
    private final StringProperty email;
    private final StringProperty role;
    private final StringProperty status;

    public UserFX(int userId, String email, String role, String status) {
        this.userId = new SimpleIntegerProperty(userId);
        this.email = new SimpleStringProperty(email);
        this.role = new SimpleStringProperty(role);
        this.status = new SimpleStringProperty(status);
    }

    // Getter and Setter for userId
    public int getUserId() {
        return userId.get();
    }

    public void setUserId(int userId) {
        this.userId.set(userId);
    }

    public IntegerProperty userIdProperty() {
        return userId;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    // Getter and Setter for role
    public String getRole() {
        return role.get();
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public StringProperty roleProperty() {
        return role;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty() {
        return status;
    }
}
