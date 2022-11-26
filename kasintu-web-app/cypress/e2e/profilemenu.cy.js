import React from "react";
<reference types="cypress" />

Cypress.Commands.add('login', () => {
    cy.visit('http://localhost:3000/UserAccess')
    cy.get('[data-cy=login-input-username]').type("testAccount");
    cy.get('[data-cy=login-input-password]').type("test")
    cy.get('[data-cy=user-access-login]').click();
    cy.url().should("include", "/");
})

Cypress.Commands.add('logout', () => {
    cy.visit('http://localhost:3000/')
    cy.get('[data-cy=profile-menu]').click();
    cy.get('[data-cy=profile-menu-logout]').click();
})

Cypress.Commands.add('gotoMyProfile', () => {
    cy.get('[data-cy=profile-menu]').click();
    cy.get('[data-cy=profile-menu-myprofile]').click();
})

describe("ProfileMenu", () => {

    beforeEach(() => {
        cy.login();
    });

    afterEach(() => {
        cy.logout();
    });

    it("should navigate to my profile page when clicking my profile", () => {
        cy.get('[data-cy=profile-menu]').click();
        cy.get('[data-cy=profile-menu-myprofile]').click();
        cy.url().should("include", "/MyProfile");
    });

    it("should not save when changing username and email and error encountered", () => {
        cy.intercept("PUT", 'http://localhost:8080/users/4d47dea224074ff1a6493752f8d979cd',
            {
                statusCode: 400
            });
        cy.gotoMyProfile();
        cy.get('[data-cy=myprofile-input-email]').clear().type("newtest@email.com");
        cy.get('[data-cy=myprofile-input-username]').clear().type("newtestAccount");
        cy.get('[data-cy=myprofile-save]').click();
        cy.get('[data-cy=myprofile-error]').should("exist");
    });

    it("should save when changing username and email", () => {
        cy.gotoMyProfile();
        cy.intercept("PUT", 'http://localhost:8080/users/4d47dea224074ff1a6493752f8d979cd',
            {
                statusCode: 200
            });
        cy.get('[data-cy=myprofile-input-email]').clear().type("newtest@email.com");
        cy.get('[data-cy=myprofile-input-username]').clear().type("newtestAccount");
        cy.get('[data-cy=myprofile-save]').click();
        cy.url().should("include", "/");
    });

    it("should not update password", () => {
        cy.gotoMyProfile();
        cy.get('[data-cy=myprofile-input-checkbox]').click();
        cy.get('[data-cy=myprofile-input-password]').type("newtest");
        cy.get('[data-cy=myprofile-input-confirm-password]').type("newtest123");
        cy.get('[data-cy=myprofile-save]').click();
        cy.get('[data-cy=myprofile-password-error]').should("exist");
    });

    it("should update password", () => {
        cy.intercept("PUT", 'http://localhost:8080/users/4d47dea224074ff1a6493752f8d979cd',
            {
                statusCode: 200
            });
        cy.gotoMyProfile();
        cy.get('[data-cy=myprofile-input-checkbox]').click();
        cy.get('[data-cy=myprofile-input-password]').type("newtest");
        cy.get('[data-cy=myprofile-input-confirm-password]').type("newtest");
        cy.get('[data-cy=myprofile-save]').click();
        cy.url().should("include", "/");
    });
})