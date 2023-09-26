/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Product;
import org.json.*;

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
    
    ObservableList<Product> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableItems();
    }    

    private void setTableItems() {
        Product product1 = new Product("eerekjjrr", "Product1", 100);
        Product product2 = new Product("eerekjssr", "Product2", 200);
        Product product3 = new Product("eerekjttr", "Product3", 300);
        
        colID.setCellValueFactory(new PropertyValueFactory("id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        
        data.add(product1);
        data.add(product2);
        data.add(product3);
        tbProduct.setItems(data);
    }
    
    @FXML
    private void addProduct(){
        getAllProducts();
    }
    
    @FXML
    private void updateProduct(){
        
    }
    
    @FXML
    private void deleteProduct(){
        
    }
    
    private void copyProductID(){
        
    }
    
    private void getAllProducts(){
        try{
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
//                System.out.println(content);
//                JSONObject obj = new JSONObject(jsonString);
                JSONArray jsonArray = content;
                System.out.println(jsonArray);

            }
                
        
        }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
    }
    
    
}
