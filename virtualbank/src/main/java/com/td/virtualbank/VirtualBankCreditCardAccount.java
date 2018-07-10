package com.td.virtualbank;

import java.util.List;

public class VirtualBankCreditCardAccount extends VirtualBankAccount {
    public String maskedNumber;
    public List<Card> cards = null;

    public String getMaskedNumber() {
        return maskedNumber;
    }

    public void setMaskedNumber(String maskedNumber) {
        this.maskedNumber = maskedNumber;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public class Card {

        public String id;
        public String securityCode;
        public String maskedNumber;
        public String customerId;
        public String accountId;
        public String nameOnCard;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSecurityCode() {
            return securityCode;
        }

        public void setSecurityCode(String securityCode) {
            this.securityCode = securityCode;
        }

        public String getMaskedNumber() {
            return maskedNumber;
        }

        public void setMaskedNumber(String maskedNumber) {
            this.maskedNumber = maskedNumber;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getNameOnCard() {
            return nameOnCard;
        }

        public void setNameOnCard(String nameOnCard) {
            this.nameOnCard = nameOnCard;
        }
    }

}
