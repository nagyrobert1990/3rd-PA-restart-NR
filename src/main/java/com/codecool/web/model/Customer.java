package com.codecool.web.model;

public class Customer extends User {

    private final String companyName;
    private final String contactName;

    public Customer(String id, String company_name, String contact_name) {
        super(id);
        this.companyName = company_name;
        this.contactName = contact_name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactName() {
        return contactName;
    }
}
