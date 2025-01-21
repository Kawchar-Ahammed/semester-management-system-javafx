package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AdminDashboardController {

    @FXML
    private StackPane mainContent; // Container to load different views (FXML files)

    @FXML
    public void handleLogout() {
    	try {
            Stage currentStage = (Stage) mainContent.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(loader.load());
            currentStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
            currentStage.setTitle("Semester Management System");
        } catch (Exception e) {
            showError("Failed to load login screen: " + e.getMessage());
        }
    }
   

    // Loads the Manage Users page
    @FXML
    public void loadManageUsers() {
        loadView("ManageUsers.fxml");
        System.out.println("After Inside the loadView Method");
    }

    // Loads the Manage Courses page
    @FXML
    public void loadManageCourses() {
        loadView("ManageCourses.fxml");
    }

    // Loads the Manage Departments page
    @FXML
    public void loadManageDepartments() {
        loadView("ManageDepartments.fxml");
    }

    // Loads the Manage Sessions page
    @FXML
    public void loadManageSessions() {
        loadView("ManageSessions.fxml");
    }

    // Loads the View Reports page
    @FXML
    public void loadViewReports() {
        loadView("ViewReports.fxml");
    }

    // Method to load the specified FXML file into the mainContent StackPane
    private void loadView(String fxmlFile) {
        try {
        	System.out.println("Loading view: " + fxmlFile);
            // Load the FXML file and add it to the mainContent container

            StackPane view = FXMLLoader.load(getClass().getResource(fxmlFile));
            System.out.println("Attempting to load: " + getClass().getResource("/application/" + fxmlFile));
//        	StackPane view = FXMLLoader.load(getClass().getResource("/application/ManageUsers.fxml"));
            mainContent.getChildren().clear(); // Clear the existing content
            mainContent.getChildren().add(view); // Add the new view (FXML content)
        } catch (Exception e) {
            showError("Failed to load the view: " + e.getMessage()); // Show error if loading fails
        }
    }


    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait(); 
    }
}
