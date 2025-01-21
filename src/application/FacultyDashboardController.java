package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class FacultyDashboardController {

    @FXML
    private Button viewAssignedCoursesBtn;

    @FXML
    private Button markAttendanceBtn;

    @FXML
    private Button uploadScoresBtn;

    @FXML
    public void viewAssignedCourses() {
        // Handle action to view assigned courses
        System.out.println("Viewing assigned courses...");
        // Add code to fetch and display courses from the database
    }

    @FXML
    public void markAttendance() {
        // Handle action to mark attendance
        System.out.println("Marking attendance...");
        // Add code to open an attendance marking screen or form
    }

    @FXML
    public void uploadScores() {
        // Handle action to upload test scores, assignments, and results
        System.out.println("Uploading scores...");
        // Add code to upload scores or show a file selection dialog
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Faculty Dashboard");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

