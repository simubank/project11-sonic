package com.td.virtualbank;

import java.util.List;

public abstract class VirtualBankAccount {

    public String id;
    public Double balance;
    public RelatedCustomers relatedCustomers;
    public String type;
    public String openDate;
    public String currency;
    public String number;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public RelatedCustomers getRelatedCustomers() {
        return relatedCustomers;
    }

    public void setRelatedCustomers(RelatedCustomers relatedCustomers) {
        this.relatedCustomers = relatedCustomers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public class RelatedCustomers {

        public List<Authorized> authorized = null;

        public List<Authorized> getAuthorized() {
            return authorized;
        }

        public void setAuthorized(List<Authorized> authorized) {
            this.authorized = authorized;
        }

        public class Authorized {

            public String customerId;
            public String type;

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}

