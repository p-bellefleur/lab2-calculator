package ca.qc.dawsoncollege.a1132371.loancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private double loanAmount;
    private int numberOfYears;
    private double yearlyInterestRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //

    } //onCreate()

    public void calculate() {

    }

    public void clear() {

    }

    private double getMonthlyPayment() {
        double monthlyPayment;
        double monthlyInterestRate;
        int numberOfPayments;
        if (numberOfYears != 0 && yearlyInterestRate != 0)
        {
            //calculate the monthly payment
            monthlyInterestRate = yearlyInterestRate / 1200;
            numberOfPayments = numberOfYears * 12;

            monthlyPayment =
                    (loanAmount * monthlyInterestRate) /
                            (1 - (1 / Math.pow ((1 + monthlyInterestRate), numberOfPayments)));

            monthlyPayment = Math.round (monthlyPayment * 100) / 100.0;
        }
        else
            monthlyPayment = 0;
        return monthlyPayment;
    }

    private double getTotalCostOfLoan() {
        return getMonthlyPayment() * numberOfYears * 12;
    }

    private double getTotalInterest ()
    {
        return getTotalCostOfLoan () - loanAmount;
    }
}
