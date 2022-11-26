import React from "react";
<reference types="cypress" />

describe("UserAccess", () => {

    it("should not register because password doesn't match", () => {
        cy.visit('http://localhost:3000')
        cy.get('[data-cy=navbar-login]').click();
        cy.get('[data-cy=user-access-register-button]').click();
        cy.get('[data-cy=register-input-email]').type("test@email.com");
        cy.get('[data-cy=register-input-username]').type("testAccount");
        cy.get('[data-cy=register-input-password]').type("test");
        cy.get('[data-cy=register-input-confirm-password]').type("test123");
        cy.get('[data-cy=user-access-register]').click();
        cy.url().should("include", "/UserAccess");
        cy.get('[data-cy=user-access-register-password-error]').should("exist");
    })

    it("should not register because email is already in use", () => {
        cy.intercept("POST", 'http://localhost:8080/players',
            {
                statusCode: 401
            });
        cy.visit('http://localhost:3000')
        cy.get('[data-cy=navbar-login]').click();
        cy.get('[data-cy=user-access-register-button]').click();
        cy.get('[data-cy=register-input-email]').type("test@email.com");
        cy.get('[data-cy=register-input-username]').type("testAccount");
        cy.get('[data-cy=register-input-password]').type("test");
        cy.get('[data-cy=register-input-confirm-password]').type("test");
        cy.get('[data-cy=user-access-register]').click();
        cy.url().should("include", "/UserAccess");
        cy.get('[data-cy=user-access-register-error]').should("exist");
    })

    it("should register", () => {
        cy.intercept("POST", 'http://localhost:8080/players',
            {
                fixture: 'playerAccessToken.json',
                statusCode: 204
            });
        cy.visit('http://localhost:3000')
        cy.get('[data-cy=navbar-login]').click();
        cy.get('[data-cy=user-access-register-button]').click();
        cy.get('[data-cy=register-input-email]').type("test@email.com");
        cy.get('[data-cy=register-input-username]').type("testAccount");
        cy.get('[data-cy=register-input-password]').type("test");
        cy.get('[data-cy=register-input-confirm-password]').type("test");
        cy.get('[data-cy=user-access-register]').click();
        cy.url().should("include", "/UserAccess");
        cy.get('[data-cy=user-access-login]').should("exist");
    })

    it("should not login because input is incorrect", () => {
        cy.visit('http://localhost:3000')
        cy.get('[data-cy=navbar-login]').click();
        cy.get('[data-cy=login-input-username]').type("testAccount");
        cy.get('[data-cy=login-input-password]').type("test123")
        cy.get('[data-cy=user-access-login]').click();
        cy.get('[data-cy=user-access-login-error]').should("exist");
    })

    it("should login", () => {
        cy.get('[data-cy=login-input-username]').clear().type("testAccount");
        cy.get('[data-cy=login-input-password]').clear().type("test")
        cy.get('[data-cy=user-access-login]').click();
        cy.url().should("include", "/");
    })

    it("should logout", () => {
        cy.get('[data-cy=profile-menu]').click();
        cy.get('[data-cy=profile-menu-logout]').click();
        cy.url().should("include", "/")
    })
})