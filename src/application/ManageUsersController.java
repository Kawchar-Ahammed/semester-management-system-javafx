package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class ManageUsersController {

    @FXML
    private ComboBox<String> roleFilterComboBox;

    @FXML
    private TableView<UserFX> usersTableView;

    @FXML
    private TableColumn<UserFX, Integer> userIdColumn;

    @FXML
    private TableColumn<UserFX, String> emailColumn;

    @FXML
    private TableColumn<UserFX, String> roleColumn;

    @FXML
    private TableColumn<UserFX, String> statusColumn;

    @FXML
    private TableColumn<UserFX, Void> actionsColumn;

    @FXML
    private Button addUserButton;
    
    @FXML
    private Button refreshButton;

    private ObservableList<UserFX> usersList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize ComboBox with roles
        roleFilterComboBox.setItems(FXCollections.observableArrayList("Faculty", "Staff", "Student"));

        // Bind columns to UserFX properties
        userIdColumn.setCellValueFactory(cellData -> cellData.getValue().userIdProperty().asObject());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        roleColumn.setCellValueFactory(cellData -> cellData.getValue().roleProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        // Configure actions column
        configureActionsColumn();

        // Load initial data
        loadUsers();
    }

    private void configureActionsColumn() {
        actionsColumn.setCellFactory(column -> new TableCell<UserFX, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                editButton.setOnAction(event -> handleEdit(getTableRow().getItem()));
                deleteButton.setOnAction(event -> handleDelete(getTableRow().getItem()));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow().getItem() == null) {
                    setGraphic(null);
                } else {
                    HBox actionBox = new HBox(10, editButton, deleteButton);
                    setGraphic(actionBox);
                }
            }
        });
    }

    private void loadUsers() {
        String selectedRole = roleFilterComboBox.getSelectionModel().getSelectedItem();
        String query = "SELECT u.UserID, u.Email, r.RoleName, u.IsActive FROM Users u " +
                       "JOIN Roles r ON u.RoleID = r.RoleID " +
                       "WHERE r.RoleName = ?";

        try (PreparedStatement stmt = DatabaseConnection.getConnection().prepareStatement(query)) {
            stmt.setString(1, selectedRole);
            ResultSet rs = stmt.executeQuery();

            usersList.clear();
            while (rs.next()) {
                usersList.add(new UserFX(
                    rs.getInt("UserID"),
                    rs.getString("Email"),
                    rs.getString("RoleName"),
                    rs.getBoolean("IsActive") ? "Active" : "Inactive"
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        usersTableView.setItems(usersList);
    }

    private void handleEdit(UserFX user) {
        System.out.println("Edit user: " + user);
        // Logic to edit the user
    }

    private void handleDelete(UserFX user) {
        System.out.println("Delete user: " + user);
        // Logic to delete the user
    }

    @FXML
    private void handleAddUser() {
        // Logic to add a new user
    }
}
