import React from "react";
<reference types="cypress" />

Cypress.Commands.add('login', (username, password) => {
    cy.visit('http://localhost:3000/UserAccess')
    cy.get('[data-cy=login-input-username]').type(username)
    cy.get('[data-cy=login-input-password]').type(password)
    cy.get('[data-cy=user-access-login]').click();
})

Cypress.Commands.add('logout', () => {
    cy.visit('http://localhost:3000/')
    cy.get('[data-cy=profile-menu]').click();
    cy.get('[data-cy=profile-menu-logout]').click();
})

describe("NavigationTestBeforeLogin", () => {
    beforeEach(() => {
        cy.visit("http://localhost:3000");
    });

    it("should navigate to the home page", () => {
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-home]').click();
        cy.url().should("include", "/");
    });

    it("should navigate to the creature page", () => {
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-creatures]').click();
        cy.url().should("include", "/Creatures");
    });

    it("should navigate to user access page when clicking inventory", () => {
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-inventory]').click();
        cy.url().should("include", "/UserAccess");
    });

    it("should navigate to user access page when clicking summon", () => {
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-summon]').click();
        cy.url().should("include", "/UserAccess");
    });
})

describe("NavigationTestAfterLogin", () => {
    beforeEach(() => {
        cy.login("testAccount", "test");
    });
    afterEach(() => {
        cy.logout();
    });

    it("should navigate to the home page", () => {
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-home]').click();
        cy.url().should("include", "/");
    });

    it("should navigate to the creature page", () => {
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-creatures]').click();

        cy.url().should("include", "/Creatures");
    });

    it("should navigate to inventory page", () => {
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-inventory]').click();
        cy.url().should("include", "/Inventory");
    });

    it("should navigate to summon page", () => {
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-summon]').click();
        cy.url().should("include", "/Summon");
    });
})