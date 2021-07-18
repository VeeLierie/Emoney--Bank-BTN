package id.co.hanoman.emoney.model;

import javax.validation.constraints.NotNull;

public class TrxConfrimApplet {

    @NotNull
    String timestamp;

    @NotNull
    String session;

    @NotNull
    String cardNumber;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
