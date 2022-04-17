/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javafx.collections.ObservableList;

/**
 *
 * @author Skander
 */
public interface Iservice<T> {

    public void ajouter(T t);

    public void modifier(T t);

    public void supprimer(int id);

    public ObservableList<T> recuperer();

}
