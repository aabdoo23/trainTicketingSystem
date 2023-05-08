public class CreditCard {
    private int CVV;
    private String cardNumber;
    private String holderName,expDate;
    CreditCard(String cardNumber,int cvv,String expDate,String holderName){
        this.cardNumber=cardNumber;
        this.CVV=cvv;
        this.expDate=expDate;
        this.holderName=holderName;
    }

    public CreditCard() {

    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setCVV(int CVV) {
        this.CVV = CVV;
    }
    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public int getCVV() {
        return CVV;
    }
    public String  getExpDate() {
        return expDate;
    }
    public String getHolderName() {
        return holderName;
    }

    @Override
    public String toString() {
        return "CreditCard" +
                "\n, cardNumber=" + cardNumber +
                "\n, CVV=" + CVV +
                "\n, expDate=" + expDate +
                "\n, holderName=" + holderName +
                '\n';
    }
    public String discreteToString() {
        return "CreditCard" +
                "\n, cardNumber=" + cardNumber +
                "\n, expDate=" + expDate +
                '\n';
    }
}
