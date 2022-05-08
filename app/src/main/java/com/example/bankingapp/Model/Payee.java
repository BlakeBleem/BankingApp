package com.example.bankingapp.Model;


public class Payee {

    private final String payeeID;
    private final String payeeName;

    public Payee (String payeeID, String payeeName) {
        this.payeeID = payeeID;
        this.payeeName = payeeName;
    }

    public Payee (String payeeID, String payeeName, long dbId) {
        this(payeeID, payeeName);
    }

    public String getPayeeName() {
        return payeeName;
    }
    public String getPayeeID() { return payeeID; }

    public String toString() { return (payeeName + " (" + payeeID + ")"); }
}
