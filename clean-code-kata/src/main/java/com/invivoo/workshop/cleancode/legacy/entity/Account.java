package com.invivoo.workshop.cleancode.legacy.entity;

import java.util.Objects;
import java.util.Set;

public class Account {

    private int id;
    private String nom;
    private int anneeOuverture;

    private Set<Loan> loans;

    public Account(int id, String nom, int anneeOuverture) {
        this.id = id;
        this.nom = nom;
        this.anneeOuverture = anneeOuverture;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnneeOuverture() {
        return anneeOuverture;
    }

    public void setAnneeOuverture(int anneeOuverture) {
        this.anneeOuverture = anneeOuverture;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return id == account.id &&
               anneeOuverture == account.anneeOuverture &&
               Objects.equals(nom, account.nom) &&
               Objects.equals(loans, account.loans);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nom, anneeOuverture, loans);
    }
}
