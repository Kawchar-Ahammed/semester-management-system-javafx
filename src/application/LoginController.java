package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    public TextField emailField;
    public PasswordField passwordField;

    public void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please fill in both email and password.");
            return;
        }

        try {
            User user = authenticateUser(email, password);
            if (user != null) {
                loadDashboard(user);
            } else {
                showAlert("Error", "Invalid email or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error", "Database error occurred.");
        }
    }

    private User authenticateUser(String email, String password) throws SQLException {
        String query = "SELECT UserID, Email, PasswordHash, RoleName FROM Users INNER JOIN Roles ON Users.RoleID = Roles.RoleID WHERE Email = ? AND PasswordHash = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password); // You can add hashing later if needed

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("UserID"), resultSet.getString("Email"), resultSet.getString("PasswordHash"), resultSet.getString("RoleName"));
            }
        }
        return null;
    }

    private void loadDashboard(User user) {
        String fxmlFile = getDashboardFXML(user.getRole());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setTitle(user.getRole() + " Dashboard");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the dashboard.");
        }
    }

    private String getDashboardFXML(String role) {
        switch (role.toUpperCase()) {
            case "ADMINISTRATOR": return "admin_dashboard.fxml";
            case "FACULTY": return "faculty_dashboard.fxml";
            case "STUDENT": return "student_dashboard.fxml";
            case "STAFF": return "staff_dashboard.fxml";
            default: throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
