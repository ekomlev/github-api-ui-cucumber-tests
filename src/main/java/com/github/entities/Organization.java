package com.github.entities;

public class Organization {
    String organizationName;
    String organizationBillingEmail;
    boolean organizationFreePlan;

    public Organization(String organizationName, String organizationBillingEmail, boolean organizationFreePlan) {
        this.organizationName = organizationName;
        this.organizationBillingEmail = organizationBillingEmail;
        this.organizationFreePlan = organizationFreePlan;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationBillingEmail() {
        return organizationBillingEmail;
    }

    public void setOrganisationBillingEmail(String organisationBillingEmail) {
        this.organizationBillingEmail = organisationBillingEmail;
    }

    public boolean getOrganizationFreePlan() {
        return organizationFreePlan;
    }

    public void setOrganizationFreePlan(boolean organizationFreePlan) {
        this.organizationFreePlan = organizationFreePlan;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "organizationName='" + organizationName + '\'' +
                ", organisationBillingEmail='" + organizationBillingEmail + '\'' +
                ", organizationFreePlan=" + organizationFreePlan +
                '}';
    }
}
