package com.invivoo.workshop.cleancode.legacy.service;


import com.invivoo.workshop.cleancode.legacy.entity.Account;
import com.invivoo.workshop.cleancode.legacy.entity.Loan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class AccountService {

    /**
     * Il faut retourner l'intervalle de temps entre l'année de première création de compte et
     * le dernier compte créé.
     *
     * @param accounts
     *
     * @return
     */
    int getRangeBetweenLowestAndHighestYearOfAccountCreation(List<Account> accounts) {
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(Account account : accounts) {
            int anneeOuverture = account.getAnneeOuverture();

            if(anneeOuverture > max) {
                max = anneeOuverture;
            } else if (anneeOuverture < min) {
                min = anneeOuverture;
            }
         }

        return max - min;
    }


    /**
     * Il faut partionner les emprunts en 2 listes, la première avec les emprunts ayants un montant inférieure
     * au montant en paramètre la seconde avec les emprunts avec les montants supérieurs.
     *
     * @param accounts
     *
     * @return
     */
    Map<Boolean, List<Loan>> partitionEmpruntByAmount(List<Account> accounts, int montant) {
        Map<Boolean, List<Loan>> result = new HashMap<>();

        for (Account account : accounts) {
            for (Loan loan : account.getLoans()) {
                int montantEmp = loan.getMontant();
                if (!result.containsKey(montantEmp > montant)) {
                    List<Loan> list = new ArrayList<>();
                    list.add(loan);
                    result.put(montantEmp > montant, list);
                } else {
                    List<Loan> list = result.get(montantEmp > montant);
                    list.add(loan);
                }
            }
        }

        return result;
    }


    /**
     * Retourner le montant le plus présent sur les emprunts des 20 dernières années
     * // si plusieurs montant retourner celui qui a le montant le plus élevé
     *
     * @param input
     *
     * @return
     */
    Integer getMost(List<Account> input) {

        Map<Integer, Long> amountsCounting = new HashMap<>();

        for (Account account : input) {
            Set<Loan> loans = account.getLoans();

            for (Loan loan : loans) {
                if (loan.getAnneeRealisation() > LocalDate.now().minusYears(20).getYear()) {
                    if (amountsCounting.containsKey(loan.getMontant())) {
                        amountsCounting.put(loan.getMontant(), amountsCounting.get(loan.getMontant()) + 1);
                    } else {
                        amountsCounting.put(loan.getMontant(), 1L);
                    }
                }
            }
        }

        int result = 0;

        long maxValueInMap = (Collections.max(amountsCounting.values()));

        for (Map.Entry<Integer, Long> entry : amountsCounting.entrySet()) {

            if (entry.getValue() == maxValueInMap) {
                result = entry.getKey() > result ? entry.getKey() : result;
            }
        }

        return result;
    }

}