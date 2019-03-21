package com.invivoo.workshop.cleancode.legacy.entity;

import java.util.Objects;

public class Loan {

    int idCompte;
    int id;
    int montant;
    int anneeRealisation;

    public Loan(int idCompte, int id, int montant, int anneeRealisation) {
        this.idCompte = idCompte;
        this.id = id;
        this.montant = montant;
        this.anneeRealisation = anneeRealisation;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getAnneeRealisation() {
        return anneeRealisation;
    }

    public void setAnneeRealisation(int anneeRealisation) {
        this.anneeRealisation = anneeRealisation;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
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
        Loan loan = (Loan) o;
        return idCompte == loan.idCompte &&
               id == loan.id &&
               montant == loan.montant &&
               anneeRealisation == loan.anneeRealisation;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCompte, id, montant, anneeRealisation);
    }
}
