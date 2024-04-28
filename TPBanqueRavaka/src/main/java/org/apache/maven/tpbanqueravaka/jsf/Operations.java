/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.apache.maven.tpbanqueravaka.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import org.apache.maven.tpbanqueravaka.entity.CompteBancaire;
import org.apache.maven.tpbanqueravaka.entity.OperationBancaire;
import org.apache.maven.tpbanqueravaka.service.GestionnaireCompte;

/**
 *
 * @author user
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    private Long idCompte;
    private CompteBancaire compte;
    private List<OperationBancaire> operations;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public Operations() {
    }

    @PostConstruct
    public void init() {
        if (idCompte != null) {
            compte = gestionnaireCompte.getCompteById(idCompte);
            operations = compte.getOperations();
        }
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public List<OperationBancaire> getOperations() {
        return operations;
    }
}
