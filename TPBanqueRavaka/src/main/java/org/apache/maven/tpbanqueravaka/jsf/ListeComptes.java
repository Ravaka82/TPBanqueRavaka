/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.apache.maven.tpbanqueravaka.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import org.apache.maven.tpbanqueravaka.entity.CompteBancaire;
import org.apache.maven.tpbanqueravaka.service.GestionnaireCompte;

/**
 *
 * @author user
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {
        return gestionnaireCompte.getAllComptes();
    }
}
