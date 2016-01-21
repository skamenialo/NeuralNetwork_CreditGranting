package com.skamenialo.creditgranting;

import java.io.IOException;
import java.util.List;

public class Client {

    private static final int INCOME_PER_PERSON = 700,
            MIN_INCOME = 1300,
            RETIREMENT_AGE = 65,
            MIN_TIME_EMPLOY = 12,
            MIN_CURRENT_LOAN = 100,
            MAX_CURRENT_LOAN = 5000,
            MIN_NEW_LOAN = 500,
            MAX_NEW_LOAN = 10000;
    private boolean mGranted;
    private int mAge,
            mChildren,
            mEmployment;//miesiace do końca pracy
    private double mIncome,
            mAmountActiveLoan,
            mAmountNewLoan;
    private boolean mLoan;

    public double x[];//wartosci znormalizowane
    public int d[];//wartosci oczekiwane

    public Client(boolean granted, int age, int children, double income, int employment, boolean loan, double amountActiveLoan, double amountNewLoan) {
        this.mGranted = granted;
        this.mAge = age;
        this.mChildren = children;
        this.mIncome = income;
        this.mEmployment = employment;
        this.mLoan = loan;
        this.mAmountActiveLoan = amountActiveLoan;
        this.mAmountNewLoan = amountNewLoan;
    }

    public Client(int age, int children, double income, int employment, boolean loan, double amountActiveLoan, double amountNewLoan) {
        this(false, age, children, income, employment, loan, amountActiveLoan, amountNewLoan);
    }

    public void setX(List<Client> clients) {
        this.x = new double[]{
            (mGranted ? 1.0 : 0.0),
            mAge,
            mChildren,
            mIncome,
            mEmployment,
            (mLoan ? 1.0 : 0.0),
            mAmountActiveLoan,
            mAmountNewLoan
        };
        double max[] = getMaxX(clients);
        for (int i = 0; i < x.length; i++) {
            x[i] = Math.round(x[i] / max[i]);
            System.out.print(x[i] + "|");
        }
    }

    public static double[] getMaxX(List<Client> clients) {
        double max[] = new double[]{1, 0, 0, 0, 0, 1, 0, 0};
        for (Client c : clients) {
            if (c.mAge > max[1]) {
                max[1] = c.mAge;
            }
            if (c.mChildren > max[2]) {
                max[2] = c.mChildren;
            }
            if (c.mIncome > max[3]) {
                max[3] = c.mIncome;
            }
            if (c.mEmployment > max[4]) {
                max[4] = c.mEmployment;
            }
            if (c.mAmountActiveLoan > max[6]) {
                max[6] = c.mAmountActiveLoan;
            }
            if (c.mAmountNewLoan > max[7]) {
                max[7] = c.mAmountNewLoan;
            }
        }
        return max;
    }

    public int[] getD() {
        return new int[]{
            (mGranted ? 1 : 0),
            (mAge < 75 ? 1 : 0),
            (mIncome / (mChildren + 2)) >= INCOME_PER_PERSON ? 1 : (mChildren < 2 ? 1 : 0),
            (mIncome >= MIN_INCOME ? 1 : 0),
            (mAge < RETIREMENT_AGE ? (mEmployment > MIN_TIME_EMPLOY ? 1 : 0) : (mEmployment > 120 ? 1 : 0)),
            (mLoan ? 1 : 0),
            (mAmountActiveLoan <= 1000 ? 1 : 0),
            (mAmountNewLoan <= 1500 ? 1 : 0)
        };
    }

    public String isGrantedToString() {
        return (mGranted ? "Udzielono" : "Nie udzielono") + " -  " + toString();
    }

    @Override
    public String toString() {
        return "Wiek: " + mAge
                + ", Dzieci: " + mChildren
                + ", Zarobki: " + mIncome
                + ", Pozostałe miesiące dochodu: " + mEmployment
                + ", Czy spłaca kredyt: " + (mLoan ? "tak" : "nie")
                + ", Kwota spłacanego kredytu: " + mAmountActiveLoan
                + ", Kwota kredytu: " + mAmountNewLoan;
    }

    public static Client getRandomClient() throws IOException {
        int age = RandomNumber.nextInt(18, 80);
        double income = RandomNumber.nextMoney(0.0, 5000.0);
        boolean loan = (income > 1000.0 ? RandomNumber.nextInt(0, 1) == 0 : false);

        Client client = new Client(
                age,
                RandomNumber.nextInt(0, 3),
                Math.round(income),
                (age < RETIREMENT_AGE ? RandomNumber.nextInt(0, 36) : 90 - age),
                loan,
                (loan ? Math.round(RandomNumber.nextDouble(MIN_CURRENT_LOAN, MAX_CURRENT_LOAN)) : 0.0),
                Math.round(RandomNumber.nextMoney(MIN_NEW_LOAN, MAX_NEW_LOAN)));
        System.out.println(client.toString());
        System.out.print("Przyznano kredyt (t/n)? ");
        char isGranted;
        do {
            isGranted = (char) System.in.read();
        } while ((isGranted != 't' && isGranted != 'n'));
        client.setGranted(isGranted == 't');
        //TODO add random client
        return client;
    }

    public boolean isGranted() {
        return mGranted;
    }

    public int getAge() {
        return mAge;
    }

    public int getChildren() {
        return mChildren;
    }

    public int getEmployment() {
        return mEmployment;
    }

    public double getIncome() {
        return mIncome;
    }

    public double getAmountActiveLoan() {
        return mAmountActiveLoan;
    }

    public double getAmountNewLoan() {
        return mAmountNewLoan;
    }

    public boolean isLoan() {
        return mLoan;
    }

    public void setGranted(boolean value) {
        mGranted = value;
    }
}
