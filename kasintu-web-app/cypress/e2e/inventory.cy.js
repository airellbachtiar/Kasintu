import React from "react";
<reference types="cypress" />

Cypress.Commands.add('login', (username, password) => {
    cy.intercept("GET", 'http://localhost:8080/players/4d47dea224074ff1a6493752f8d979cd',
        {
            fixture: 'player.json'
        });
    cy.intercept("POST", 'http://localhost:8080/login',
        {
            fixture: 'playerAccessToken.json'
        });
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

describe("InventoryTest", () => {
    beforeEach(() => {
        cy.login("testAccount", "test");
    });
    afterEach(() => {
        cy.logout();
    });

    it("should navigate to inventory page", () => {
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-inventory]').click();
        cy.url().should("include", "/Inventory");
    });

    it("should navigate to empty inventory page", () => {
        cy.intercept("GET", 'http://localhost:8080/inventory/player/4d47dea224074ff1a6493752f8d979cd',
            {
                fixture: 'emptyinventory.json'
            });
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-inventory]').click();
        cy.url().should("include", "/Inventory");
        cy.get('[data-cy=inventory-empty]').should("exist");
    });

    it("should navigate to detailed owned creature page", () => {
        cy.intercept("GET", 'http://localhost:8080/inventory/player/4d47dea224074ff1a6493752f8d979cd',
            {
                fixture: 'inventory.json'
            });
        cy.intercept("GET", 'http://localhost:8080/ownedCreature/09d57f25ffbd46c7876580f94a889807',
            {
                fixture: 'ownedCreature.json'
            });
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-inventory]').click();
        cy.url().should("include", "/Inventory");

        cy.get('[data-cy=owned-creature-09d57f25ffbd46c7876580f94a889807]').click();
        cy.url().should("include", "/OwnedCreature/09d57f25ffbd46c7876580f94a889807");
        cy.get('[data-cy=owned-creature-detail-back]').click();
        cy.url().should("include", "/Inventory");
    })

    it("should delete an owned creature", () => {
        cy.intercept("GET", 'http://localhost:8080/inventory/player/4d47dea224074ff1a6493752f8d979cd',
            {
                fixture: 'inventory.json'
            });
        cy.intercept("GET", 'http://localhost:8080/ownedCreature/09d57f25ffbd46c7876580f94a889807',
            {
                fixture: 'ownedCreature.json'
            });
        cy.intercept("DELETE", 'http://localhost:8080/ownedCreature/09d57f25ffbd46c7876580f94a889807',
            {
                statusCode: 204
            });
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-inventory]').click();
        cy.url().should("include", "/Inventory");

        cy.get('[data-cy=owned-creature-09d57f25ffbd46c7876580f94a889807]').click();
        cy.url().should("include", "/OwnedCreature/09d57f25ffbd46c7876580f94a889807");
        cy.get('.mantine-ActionIcon-hover > svg').click();
        cy.get('[data-cy=owned-creature-detail-delete]').click();
        cy.get('.mantine-Button-filled').click();
        cy.get('[data-cy=owned-creature-09d57f25ffbd46c7876580f94a889807]').should('not.exist');
        cy.url().should("include", "/Inventory");
    })
})