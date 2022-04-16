/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author ghail
 */
public class User {
    private int id;
    private int carte_id;
    private String email;
    private Role roles;
    private String password;
    private String cin;
    private String nom;
    private String prenom;
    private String photo;
    private boolean is_verified;
    private String code;

    public User() {
    }

    public User(int carte_id, String email, Role roles, String password, String cin, String nom, String prenom, String photo, boolean is_verified, String code) {
        this.carte_id = carte_id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.is_verified = is_verified;
        this.code = code;
    }

    public User(int id, int carte_id, String email, Role roles, String password, String cin, String nom, String prenom, String photo, boolean is_verified, String code) {
        this.id = id;
        this.carte_id = carte_id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.is_verified = is_verified;
        this.code = code;
    }

    public User(String email, Role roles, String password, String cin, String nom, String prenom, String photo, boolean is_verified, String code) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.photo = photo;
        this.is_verified = is_verified;
        this.code = code;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarte_id() {
        return carte_id;
    }

    public void setCarte_id(int carte_id) {
        this.carte_id = carte_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", carte_id=" + carte_id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", photo=" + photo + ", is_verified=" + is_verified + ", code=" + code + '}'+"\n";
    }
   
    
    
}
