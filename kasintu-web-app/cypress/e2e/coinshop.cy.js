import React from "react";
<reference types="cypress" />

Cypress.Commands.add('login', () => {
    cy.visit('http://localhost:3000/UserAccess')
    cy.get('[data-cy=login-input-username]').type("testAccount")
    cy.get('[data-cy=login-input-password]').type("test")
    cy.get('[data-cy=user-access-login]').click();
})

Cypress.Commands.add('logout', () => {
    cy.visit('http://localhost:3000/')
    cy.get('[data-cy=profile-menu]').click();
    cy.get('[data-cy=profile-menu-logout]').click();
})

describe("CoinShop", () => {
    beforeEach(() => {
        cy.login();
    });

    afterEach(() => {
        cy.logout();
    });;;

    it("should navigate to coin shop page and add coin", () => {
        cy.get('[data-cy=navbar-coin-shop]').click();
        cy.get('[data-cy=coincard-1000]').click();
        cy.get('.mantine-Button-filled').click();
        cy.url().should("include", "/");
    });
})