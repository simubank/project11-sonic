package com.td.virtualbank;

import java.util.List;

public class VirtualBankCustomer {

    public Addresses addresses;
    public Integer age;
    public String birthDate;
    public String gender;
    public String givenName;
    public String habitationStatus;
    public String id;
    public MaskedRelatedBankAccounts maskedRelatedBankAccounts;
    public MaskedRelatedCreditCardAccounts maskedRelatedCreditCardAccounts;
    public String otherName;
    public String primaryOccupation;
    public RelatedBankAccounts relatedBankAccounts;
    public RelatedCreditCardAccounts relatedCreditCardAccounts;
    public String relationshipStatus;
    public Schools schools;
    public String surname;
    public String type;

    public Addresses getAddresses() {
        return addresses;
    }

    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getHabitationStatus() {
        return habitationStatus;
    }

    public void setHabitationStatus(String habitationStatus) {
        this.habitationStatus = habitationStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MaskedRelatedBankAccounts getMaskedRelatedBankAccounts() {
        return maskedRelatedBankAccounts;
    }

    public void setMaskedRelatedBankAccounts(MaskedRelatedBankAccounts maskedRelatedBankAccounts) {
        this.maskedRelatedBankAccounts = maskedRelatedBankAccounts;
    }

    public MaskedRelatedCreditCardAccounts getMaskedRelatedCreditCardAccounts() {
        return maskedRelatedCreditCardAccounts;
    }

    public void setMaskedRelatedCreditCardAccounts(MaskedRelatedCreditCardAccounts maskedRelatedCreditCardAccounts) {
        this.maskedRelatedCreditCardAccounts = maskedRelatedCreditCardAccounts;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getPrimaryOccupation() {
        return primaryOccupation;
    }

    public void setPrimaryOccupation(String primaryOccupation) {
        this.primaryOccupation = primaryOccupation;
    }

    public RelatedBankAccounts getRelatedBankAccounts() {
        return relatedBankAccounts;
    }

    public void setRelatedBankAccounts(RelatedBankAccounts relatedBankAccounts) {
        this.relatedBankAccounts = relatedBankAccounts;
    }

    public RelatedCreditCardAccounts getRelatedCreditCardAccounts() {
        return relatedCreditCardAccounts;
    }

    public void setRelatedCreditCardAccounts(RelatedCreditCardAccounts relatedCreditCardAccounts) {
        this.relatedCreditCardAccounts = relatedCreditCardAccounts;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public Schools getSchools() {
        return schools;
    }

    public void setSchools(Schools schools) {
        this.schools = schools;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public class Addresses {

        public PrincipalResidence principalResidence;

        public PrincipalResidence getPrincipalResidence() {
            return principalResidence;
        }

        public void setPrincipalResidence(PrincipalResidence principalResidence) {
            this.principalResidence = principalResidence;
        }

        public class PrincipalResidence {

            public Integer addressType;

            public Double getLatitude() {
                return latitude;
            }

            public void setLatitude(Double latitude) {
                this.latitude = latitude;
            }

            public Double getLongitude() {
                return longitude;
            }

            public void setLongitude(Double longitude) {
                this.longitude = longitude;
            }

            public Double latitude;
            public Double longitude;
            public String municipality;
            public String postalCode;
            public String streetName;
            public String streetNumber;
            public String wardName;

            public Integer getAddressType() {
                return addressType;
            }

            public void setAddressType(Integer addressType) {
                this.addressType = addressType;
            }

            public String getMunicipality() {
                return municipality;
            }

            public void setMunicipality(String municipality) {
                this.municipality = municipality;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
            }

            public String getStreetName() {
                return streetName;
            }

            public void setStreetName(String streetName) {
                this.streetName = streetName;
            }

            public String getStreetNumber() {
                return streetNumber;
            }

            public void setStreetNumber(String streetNumber) {
                this.streetNumber = streetNumber;
            }

            public String getWardName() {
                return wardName;
            }

            public void setWardName(String wardName) {
                this.wardName = wardName;
            }
        }

    }

    public class MaskedRelatedBankAccounts {

        public List<Individual> individual = null;

        public List<Individual> getIndividual() {
            return individual;
        }

        public void setIndividual(List<Individual> individual) {
            this.individual = individual;
        }

        public class Individual {

            public String accountId;
            public String branchNumber;
            public String maskedAccountNumber;

            public String getAccountId() {
                return accountId;
            }

            public void setAccountId(String accountId) {
                this.accountId = accountId;
            }

            public String getBranchNumber() {
                return branchNumber;
            }

            public void setBranchNumber(String branchNumber) {
                this.branchNumber = branchNumber;
            }

            public String getMaskedAccountNumber() {
                return maskedAccountNumber;
            }

            public void setMaskedAccountNumber(String maskedAccountNumber) {
                this.maskedAccountNumber = maskedAccountNumber;
            }
        }
    }

    public class MaskedRelatedCreditCardAccounts {

        public List<Authorized> authorized = null;

        public List<Authorized> getAuthorized() {
            return authorized;
        }

        public void setAuthorized(List<Authorized> authorized) {
            this.authorized = authorized;
        }

        public class Authorized {

            public String accountId;
            public String accountType;
            public String maskedAccountNumber;

            public String getAccountId() {
                return accountId;
            }

            public void setAccountId(String accountId) {
                this.accountId = accountId;
            }

            public String getAccountType() {
                return accountType;
            }

            public void setAccountType(String accountType) {
                this.accountType = accountType;
            }

            public String getMaskedAccountNumber() {
                return maskedAccountNumber;
            }

            public void setMaskedAccountNumber(String maskedAccountNumber) {
                this.maskedAccountNumber = maskedAccountNumber;
            }
        }
    }

    public class RelatedBankAccounts {

        public List<Individual_> individual = null;

        public List<Individual_> getIndividual() {
            return individual;
        }

        public void setIndividual(List<Individual_> individual) {
            this.individual = individual;
        }

        public class Individual_ {

            public String accountId;
            public String accountNumber;
            public String branchNumber;

            public String getAccountId() {
                return accountId;
            }

            public void setAccountId(String accountId) {
                this.accountId = accountId;
            }

            public String getAccountNumber() {
                return accountNumber;
            }

            public void setAccountNumber(String accountNumber) {
                this.accountNumber = accountNumber;
            }

            public String getBranchNumber() {
                return branchNumber;
            }

            public void setBranchNumber(String branchNumber) {
                this.branchNumber = branchNumber;
            }
        }

    }

    public class RelatedCreditCardAccounts {

        public List<Authorized_> authorized = null;

        public List<Authorized_> getAuthorized() {
            return authorized;
        }

        public void setAuthorized(List<Authorized_> authorized) {
            this.authorized = authorized;
        }

        public class Authorized_ {

            public String accountId;
            public String accountNumber;
            public String accountType;

            public String getAccountId() {
                return accountId;
            }

            public void setAccountId(String accountId) {
                this.accountId = accountId;
            }

            public String getAccountNumber() {
                return accountNumber;
            }

            public void setAccountNumber(String accountNumber) {
                this.accountNumber = accountNumber;
            }

            public String getAccountType() {
                return accountType;
            }

            public void setAccountType(String accountType) {
                this.accountType = accountType;
            }
        }
    }
    public class Schools {


    }
}
