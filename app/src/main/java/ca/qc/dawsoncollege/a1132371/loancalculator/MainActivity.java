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
    EditText inputLoan;
    EditText inputYears;
    EditText inputInterest;
    TextView resultMonthly;
    TextView resultTotal;
    TextView resultInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get a handle on the text fields
        inputLoan = (EditText) findViewById(R.id.loanAmount);
        inputYears = (EditText) findViewById(R.id.numberOfYears);
        inputInterest = (EditText) findViewById(R.id.yearlyInterestRate);
        resultMonthly = (TextView) findViewById(R.id.monthlyResult);
        resultTotal = (TextView) findViewById(R.id.paymentResult);
        resultInterest = (TextView) findViewById(R.id.interestResult);
    } //onCreate()

    public void calculate(View v) {
        if (validate()) {
            loanAmount = Double.parseDouble(inputLoan.getText().toString());
            numberOfYears = Integer.parseInt(inputYears.getText().toString());
            yearlyInterestRate = Double.parseDouble(inputInterest.getText().toString());

            resultMonthly.setText(Double.toString(getMonthlyPayment()));
            resultTotal.setText(Double.toString(getTotalCostOfLoan()));
            resultInterest.setText(Double.toString(getTotalInterest()));
        } else {
            resultMonthly.setText(R.string.no_number_error);
            resultTotal.setText("");
            resultInterest.setText("");
        }
    }

    public void clear(View v) {
        inputLoan.setText("");
        inputYears.setText("");
        inputInterest.setText("");
        resultMonthly.setText(R.string.result_hint);
        resultTotal.setText("");
        resultInterest.setText("");
    } //clearFields()

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

    private boolean validate() {
        try {
            loanAmount = Double.parseDouble(inputLoan.getText().toString());
            numberOfYears = Integer.parseInt(inputYears.getText().toString());
            yearlyInterestRate = Double.parseDouble(inputInterest.getText().toString());
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
