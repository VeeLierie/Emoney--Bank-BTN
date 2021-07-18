package id.co.hanoman.emoney.model;

import javax.validation.constraints.NotNull;

public class TrxAllApplet {

    @NotNull
    String dateTime;

    @NotNull
    String cardAttribute;

    @NotNull
    String amount;

    @NotNull
    String lastBalance;

    @NotNull
    String approvalCode;

    @NotNull
    String inquiryId;

    @NotNull
    String cardInfo;

    @NotNull
    String cardUUID;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCardAttribute() {
        return cardAttribute;
    }

    public void setCardAttribute(String cardAttribute) {
        this.cardAttribute = cardAttribute;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLastBalance() {
        return lastBalance;
    }

    public void setLastBalance(String lastBalance) {
        this.lastBalance = lastBalance;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(String inquiryId) {
        this.inquiryId = inquiryId;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    public String getCardUUID() {
        return cardUUID;
    }

    public void setCardUUID(String cardUUID) {
        this.cardUUID = cardUUID;
    }
}
