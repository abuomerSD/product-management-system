/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Product;

/**
 * FXML Controller class
 *
 * @author asdf
 */
public class SystemController implements Initializable {

    @FXML
    private TextField txtNameAdd;
    @FXML
    private TextField txtPriceAdd;
    @FXML
    private TableView<Product> tbProduct;
    @FXML
    private TableColumn<Product, String> colID;
    @FXML
    private TableColumn<Product, String> colName;
    @FXML
    private TableColumn<Product, Double> colPrice;
    @FXML
    private TextField txtIdUpdate;
    @FXML
    private TextField txtNameUpdate;
    @FXML
    private TextField txtPriceUpdate;

    // list to hold all products data
    ObservableList<Product> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableItems();
    }

    // set the Table items from the database
    private void setTableItems() {

        colID.setCellValueFactory(new PropertyValueFactory("id"));
        colName.setCellValueFactory(new PropertyValueFactory("pname"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        getAllProducts();
        tbProduct.setItems(data);
    }

    @FXML
    private void addProduct() {
        try {
            Product product = new Product();
            product.setPname(txtNameAdd.getText());
            product.setPrice(Double.parseDouble(txtPriceAdd.getText()));

            URL url = new URL("http://127.0.0.1:5000/api/products");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoInput(true);
            con.setDoOutput(true);

            Gson gson = new Gson();
            String json = gson.toJson(product);
            con.connect();
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.write(json.getBytes());
            os.close();
            int responseCode = con.getResponseCode();

            txtNameAdd.clear();
            txtPriceAdd.clear();
            txtNameAdd.requestFocus();

            setTableItems();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @FXML
    private void updateProduct() {
        try {
            String id = txtIdUpdate.getText();
            Product product = new Product();
            product.setPname(txtNameUpdate.getText());
            product.setPrice(Double.parseDouble(txtPriceUpdate.getText()));

            URL url = new URL("http://127.0.0.1:5000/api/products/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoInput(true);
            con.setDoOutput(true);

            Gson gson = new Gson();
            String json = gson.toJson(product);
            con.connect();
            DataOutputStream os = new DataOutputStream(con.getOutputStream());
            os.write(json.getBytes());
            os.close();
            int responseCode = con.getResponseCode();

            txtIdUpdate.clear();
            txtNameUpdate.clear();
            txtPriceUpdate.clear();

            setTableItems();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    @FXML
    private void deleteProduct() {
        try {
            String id = txtIdUpdate.getText();
            Product product = new Product();
            product.setPname(txtNameUpdate.getText());
            product.setPrice(Double.parseDouble(txtPriceUpdate.getText()));

            URL url = new URL("http://127.0.0.1:5000/api/products/" + id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            int responseCode = con.getResponseCode();

            txtIdUpdate.clear();
            txtNameUpdate.clear();
            txtPriceUpdate.clear();

            setTableItems();
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    // set the selected product values at the textfields
    @FXML
    private void copyProductID() {
        Product product = tbProduct.getSelectionModel().getSelectedItem();
        txtIdUpdate.setText(product.getId());
        txtNameUpdate.setText(product.getPname());
        txtPriceUpdate.setText(String.valueOf(product.getPrice()));
    }

    private void getAllProducts() {

        // clearing the products list to fill with the new values
        data.clear();

        try {

            // sending GET request

            URL url = new URL("http://127.0.0.1:5000/api/products/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            int status = con.getResponseCode();
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                // System.out.println(content);

                // parsing the json array which returned in response body
                Gson gson = new Gson();
                Product[] products = gson.fromJson(content.toString(), Product[].class);

                for (int i = 0; i < products.length; i++) {
                    Product product = new Product(products[i].getId(),
                            products[i].getPname(),
                            products[i].getPrice());

                    data.add(product);
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(ex.getMessage());
                alert.show();
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
