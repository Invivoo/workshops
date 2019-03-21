package com.invivoo.workshop.cleancode.legacy.service;

import com.invivoo.workshop.cleancode.legacy.entity.Account;
import com.invivoo.workshop.cleancode.legacy.entity.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountServiceTest {

    private List<Account> accounts;

    @BeforeEach
    void setUp() throws IOException {
        this.accounts = getAccountWithLoans();
    }

    @Test
    public void should_return_636850910_when_calling_most_present_amount_with_file_account() {
        // given accounts
        AccountService accountService = new AccountService();

        // when
        int mostPresentAmountsBad = accountService.getMost(accounts);

        // then
        assertEquals(636850910, mostPresentAmountsBad);
    }

    @Test
    public void should_return_10010_when_calling_partitionEmpruntByMontant_and_getting_size_for_amount_more_than_50000() {
        // given accounts
        AccountService accountService = new AccountService();

        // when
        Map<Boolean, List<Loan>> booleanListMap = accountService.partitionEmpruntByAmount(accounts, 50000);

        // then
        assertEquals(10010, booleanListMap.get(true).size());
    }

    @Test
    public void should_return_10010_when_calling_getRangeBetweenLowestAndHighestYearOfAccountCreation() {
        // given accounts
        AccountService accountService = new AccountService();

        // when
        int range = accountService.getRangeBetweenLowestAndHighestYearOfAccountCreation(accounts);

        // then
        assertEquals(49, range);
    }

    private static List<Account> getAccountWithLoans() throws IOException {
        List<Account> accounts = parseAccounts();
        List<Loan> loans = parseEmprunts();

        accounts.forEach(account -> account.setLoans(getAccountLoans(loans, account)));

        return accounts;
    }

    private static Set<Loan> getAccountLoans(List<Loan> loans, Account account) {
        return loans.stream()
                    .filter(emp -> emp.getIdCompte() == account.getId())
                    .collect(Collectors.toSet());
    }

    private static List<Account> parseAccounts() throws IOException {
        return Files.lines(Paths.get("files/comptes.txt"))
                    .map(AccountServiceTest::getAccountFromLine)
                    .collect(Collectors.toList());
    }

    private static List<Loan> parseEmprunts() throws IOException {
        return Files.lines(Paths.get("files/emprunts.txt"))
                    .map(AccountServiceTest::getEmpruntFromLine)
                    .collect(Collectors.toList());
    }

    private static Account getAccountFromLine(String line) {
        String[] compte = line.split(";");

        return new Account(Integer.valueOf(compte[0]),
                           compte[1],
                           Integer.valueOf(compte[2]));
    }

    private static Loan getEmpruntFromLine(String line) {
        String[] emprunt = line.split(";");

        return new Loan(Integer.valueOf(emprunt[0]),
                        Integer.valueOf(emprunt[1]),
                        Integer.valueOf(emprunt[2]),
                        Integer.valueOf(emprunt[3]));
    }

}