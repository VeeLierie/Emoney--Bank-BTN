package id.co.hanoman.emoney.model;

import javax.validation.constraints.NotNull;

public class TrxInquiry {

    @NotNull
    String transactionId;

    @NotNull
    String paymentId1;

    @NotNull
    String amount;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentId1() {
        return paymentId1;
    }

    public void setPaymentId1(String paymentId1) {
        this.paymentId1 = paymentId1;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
