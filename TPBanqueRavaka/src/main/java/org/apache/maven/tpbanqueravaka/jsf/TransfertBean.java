/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.apache.maven.tpbanqueravaka.jsf;


import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import org.apache.maven.tpbanqueravaka.entity.CompteBancaire;
import org.apache.maven.tpbanqueravaka.service.GestionnaireCompte;

@Named(value = "transfertBean")
@ViewScoped
public class TransfertBean implements Serializable {

    private String idSource;
    private String idDestination;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public TransfertBean() {
    }

    // Getters and setters

    public String getIdSource() {
        return idSource;
    }

    public void setIdSource(String idSource) {
        this.idSource = idSource;
    }

    public String getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(String idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    // Méthode pour effectuer le transfert
    public void transferer() {
        CompteBancaire source = gestionnaireCompte.getCompteById(idSource);
        CompteBancaire destination = gestionnaireCompte.getCompteById(idDestination);
        gestionnaireCompte.transfererArgent(source, destination, montant);
    }
}
