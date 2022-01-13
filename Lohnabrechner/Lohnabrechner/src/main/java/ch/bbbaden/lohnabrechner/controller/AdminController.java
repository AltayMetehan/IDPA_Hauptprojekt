/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import ch.bbbaden.lohnabrechner.model.User;
import ch.bbbaden.lohnabrechner.model.UserDAO;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jdom2.JDOMException;

/**
 *
 * @author miksh
 */
@Named(value = "adminController")
@ViewScoped
public class AdminController implements Serializable {

    private String name, password;
    private int arbeitnehmerChoice, arbeitgeberChoice;

    private HashMap<String, Integer> allArbeitnehmer = new HashMap<>(); // String = Name des Users. Integer = ID des Users
    private HashMap<String, Integer> allArbeitgeber = new HashMap<>(); // String = Name des Users. Integer = ID des Users
    
    private User user;
    private UserDAO udao;

    public String submitCreateUser() throws JDOMException, IOException {
        //ToDo  
        //Liest die "Name" und "Passwort" Eingaben um einen neuen User in der Datenbank zu erstellen

        //datenbank.create(name, password);
        udao.create(name, password);
        return "Admin.xhtml";
    }

    public String deleteUser() {
        if (arbeitnehmerChoice!= 0) {
            //ToDo
            //Löscht den Eintrag mit dem entsprechenden Index aus der Arbeitnehmer Datenbank
            udao.delete(user);
        }

        if (arbeitgeberChoice!= 0) {

            //ToDo
            //Löscht den Eintrag mit dem entsprechenden Index aus der Arbeitgeber Datenbank
            udao.delete(user);
        }
        return "Admin.xhtml";
    }

    private void refreshArbeitnehmer() {
        List<User> userList;
        userList = new ArrayList<>();
        
        allArbeitnehmer.put("-", 0);
        //ToDo. Demo Umsetzung
        //Soll die Hashmaps mit Namen als key und IDs als value von Arbeitgebern und Nehmern füllen
        //WICHTIG: Das erste Feld des arraylists MUSS ein leeres Feld sein um "keine Wahl" als Option zu haben.

        //Hilfestellung bei for-each-Loops https://www.geeksforgeeks.org/for-each-loop-in-java/
        for (User allUsers : userList){
            allArbeitnehmer.put(allUsers.getName(), allUsers.getId());
        }
    }

    private void refreshArbeitgeber() {
        List<User> userList;
        userList = new ArrayList<>();
        
        allArbeitgeber.put("-", 0);

        //ToDo. Demo Umsetzung
        //Soll die Hashmaps mit Namen als key und IDs als value von Arbeitgebern und Nehmern füllen
        //WICHTIG: Das erste Feld des arraylists MUSS ein leeres Feld sein um "keine Wahl" als Option zu haben.
        
        //Hilfestellung bei for-each-Loops https://www.geeksforgeeks.org/for-each-loop-in-java/
        for (User allUsers : userList){
            allArbeitgeber.put(allUsers.getName(), allUsers.getId());
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getArbeitnehmerChoice() {
        return arbeitnehmerChoice;
    }

    public void setArbeitnehmerChoice(int arbeitnehmerChoice) {
        this.arbeitnehmerChoice = arbeitnehmerChoice;
    }

    public int getArbeitgeberChoice() {
        return arbeitgeberChoice;
    }

    public void setArbeitgeberChoice(int arbeitgeberChoice) {
        this.arbeitgeberChoice = arbeitgeberChoice;
    }

    public HashMap<String, Integer> getAllArbeitnehmer() {
        return allArbeitnehmer;
    }

    public void setAllArbeitnehmer(HashMap<String, Integer> allArbeitnehmer) {
        this.allArbeitnehmer = allArbeitnehmer;
    }

    public HashMap<String, Integer> getAllArbeitgeber() {
        return allArbeitgeber;
    }

    public void setAllArbeitgeber(HashMap<String, Integer> allArbeitgeber) {
        this.allArbeitgeber = allArbeitgeber;
    }

    public UserDAO getUdao() {
        return udao;
    }

    public void setUdao(UserDAO udao) {
        this.udao = udao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
