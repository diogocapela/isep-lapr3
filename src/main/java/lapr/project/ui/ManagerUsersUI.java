package lapr.project.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lapr.project.controller.BaseController;
import lapr.project.model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerUsersUI implements Initializable {

    private BaseController controller;

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> col1;
    @FXML
    private TableColumn<User, String> col2;
    @FXML
    private TableColumn<User, String> col3;
    @FXML
    private TableColumn<User, String> col4;
    @FXML
    private TableColumn<User, String> col5;
    @FXML
    private TableColumn<User, String> col6;
    @FXML
    private TableColumn<User, String> col7;
    @FXML
    private TableColumn<User, String> col8;
    @FXML
    private TextField idUser;
    @FXML
    private TextField email;
    @FXML
    private TextField hashedSaltedPassword;
    @FXML
    private TextField creditCardNumber;
    @FXML
    private TextField height;
    @FXML
    private TextField weight;
    @FXML
    private TextField creditPoints;
    @FXML
    private TextField isAdmin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new BaseController();
        updateView();
    }

    private void updateView() {
        // Update table
        col1.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        col2.setCellValueFactory(new PropertyValueFactory<>("email"));
        col3.setCellValueFactory(new PropertyValueFactory<>("hashedSaltedPassword"));
        col4.setCellValueFactory(new PropertyValueFactory<>("creditCardNumber"));
        col5.setCellValueFactory(new PropertyValueFactory<>("height"));
        col6.setCellValueFactory(new PropertyValueFactory<>("weight"));
        col7.setCellValueFactory(new PropertyValueFactory<>("creditPoints"));
        col8.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
        table.setItems(FXCollections.observableArrayList(controller.getAllUsers()));
        // Clear input fields
        idUser.clear();
        email.clear();
        hashedSaltedPassword.clear();
        creditCardNumber.clear();
        height.clear();
        weight.clear();
        creditPoints.clear();
        isAdmin.clear();
    }

    @FXML
    private void handleAddUser() {
        try {
            User user = new User();
            user.setIdUser(Integer.parseInt(idUser.getText()));
            user.setEmail(email.getText());
            user.setHashedSaltedPassword(hashedSaltedPassword.getText());
            user.setCreditCardNumber(creditCardNumber.getText());
            user.setHeight(Double.parseDouble(height.getText()));
            user.setWeight(Double.parseDouble(weight.getText()));
            user.setCreditPoints(Integer.parseInt(creditPoints.getText()));
            user.setIsAdmin(Boolean.parseBoolean(isAdmin.getText()));
            controller.addUser(user);
            FXUtils.openAlertSuccess("User added successfully!");
            updateView();
        } catch (IllegalArgumentException e) {
            FXUtils.openAlertError(e.getMessage());
        }
    }

    @FXML
    private void handleRemoveUser() {
        User selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            controller.removeUserById(selected.getIdUser());
            FXUtils.openAlertSuccess("User removed successfully!");
            updateView();
        } else {
            FXUtils.openAlertError("You need to select a user first!");
        }
    }

    @FXML
    private void handleDeactivateUser() {
        throw new UnsupportedOperationException();
    }

    @FXML
    private void handleActivateUser() {
        throw new UnsupportedOperationException();
    }


}
