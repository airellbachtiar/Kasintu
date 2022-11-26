import React from "react";
<reference types="cypress" />

Cypress.Commands.add('login', () => {
    cy.visit('http://localhost:3000/UserAccess')
    cy.get('[data-cy=login-input-username]').type("admin")
    cy.get('[data-cy=login-input-password]').type("admin")
    cy.get('[data-cy=user-access-login]').click();
})

Cypress.Commands.add('logout', () => {
    cy.visit('http://localhost:3000/')
    cy.get('[data-cy=profile-menu]').click();
    cy.get('[data-cy=profile-menu-logout]').click();
})

Cypress.Commands.add('gotoCreature', () => {
    cy.get('[data-cy=navbar-menu]').click();
    cy.get('[data-cy=navbar-creatures]').click();
})

describe("ViewCreatureDetail", () => {

    it("should navigate to the creature page", () => {
        cy.visit("http://localhost:3000");
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-creatures]').click();
        cy.url().should("include", "/Creatures");
    });

    it("should navigate to detailed creature page", () => {
        cy.get('[data-cy=creature-7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2]').click();
        cy.url().should("include", "Creature/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2")
        cy.get('[data-cy=creature-detail-back]').click();
        cy.url().should("include", "/Creatures");
    });
})

describe("Admin creatures", () => {

    beforeEach(() => {
        cy.login();
    });

    afterEach(() => {
        cy.logout();
    });

    it("should navigate to the admin creatures page and back", () => {
        cy.get('[data-cy=navbar-menu]').click();
        cy.get('[data-cy=navbar-creatures]').click();
        cy.get('[data-cy=creature-add-button]').click();
        cy.url().should("include", "/AddCreature");
        cy.get('[data-cy=creature-add-back]').click();
        cy.url().should("include", "/Creatures");

    });

    it("should add a creature", () => {
        cy.intercept("POST", 'http://localhost:8080/creatures',
            {
                statusCode: 201
            });
        cy.gotoCreature();
        cy.get('[data-cy=creature-add-button]').click();
        cy.url().should("include", "/AddCreature");
        cy.get('[data-cy=creature-add-input-name]').type("test");
        cy.get('[data-cy=creature-add-input-rarity]').select(1);
        cy.get('[data-cy=creature-add-input-description]').type("test");
        cy.get('[data-cy=creature-add-save]').click();
        cy.url().should("include", "/Creatures");
    });

    it("should not add a creature", () => {
        cy.intercept("POST", 'http://localhost:8080/creatures',
            {
                statusCode: 400
            });
        cy.gotoCreature();
        cy.get('[data-cy=creature-add-button]').click();
        cy.url().should("include", "/AddCreature");
        cy.get('[data-cy=creature-add-input-name]').type("test");
        cy.get('[data-cy=creature-add-input-rarity]').select(1);
        cy.get('[data-cy=creature-add-input-description]').type("test");
        cy.get('[data-cy=creature-add-save]').click();
        cy.get('[data-cy=creature-add-error]').should("exist");
        cy.get('[data-cy=creature-add-back]').click();
        cy.url().should("include", "/Creatures");
    });

    it("should edit a creature", () => {
        cy.intercept("GET", 'http://localhost:8080/creatures/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2',
            {
                fixture: 'creature.json'
            });
        cy.intercept("PUT", 'http://localhost:8080/creatures/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2',
            {
                statusCode: 200
            });
        cy.gotoCreature();
        cy.get('[data-cy=creature-7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2]').click();
        cy.url().should("include", "Creature/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2");
        cy.get('.mantine-ActionIcon-hover > svg').click();
        cy.get('[data-cy=creature-detail-edit]').click();
        cy.get('[data-cy=creature-edit-name]').clear().type("test");
        cy.get('[data-cy=creature-edit-rarity]').select(1);
        cy.get('[data-cy=creature-edit-description]').clear().type("test");
        cy.get('[data-cy=creature-edit-save]').click();
        cy.url().should("include", "/Creatures");
    });

    it("should not edit a creature", () => {
        cy.intercept("GET", 'http://localhost:8080/creatures/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2',
            {
                fixture: 'creature.json'
            });
        cy.intercept("PUT", 'http://localhost:8080/creatures/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2',
            {
                statusCode: 400
            });
        cy.gotoCreature();
        cy.get('[data-cy=creature-7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2]').click();
        cy.url().should("include", "Creature/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2");
        cy.get('.mantine-ActionIcon-hover > svg').click();
        cy.get('[data-cy=creature-detail-edit]').click();
        cy.get('[data-cy=creature-edit-name]').clear().type("test");
        cy.get('[data-cy=creature-edit-rarity]').select(1);
        cy.get('[data-cy=creature-edit-description]').clear().type("test");
        cy.get('[data-cy=creature-edit-save]').click();
        cy.get('[data-cy=creature-edit-error]').should("exist");
        cy.get('[data-cy=creature-edit-cancel]').click();
        cy.url().should("include", "/Creature/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2");
    });

    it("should delete a creature", () => {
        cy.intercept("GET", 'http://localhost:8080/creatures/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2',
            {
                fixture: 'creature.json'
            });
        cy.intercept("DELETE", 'http://localhost:8080/creatures/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2',
            {
                statusCode: 200
            });
        cy.gotoCreature();
        cy.get('[data-cy=creature-7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2]').click();
        cy.url().should("include", "Creature/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2");
        cy.get('.mantine-ActionIcon-hover > svg').click();
        cy.get('[data-cy=creature-detail-edit]').click();
        cy.get('[data-cy=creature-edit-delete]').click();
        cy.get('.mantine-Button-filled > .mantine-3xbgk5 > .mantine-qo1k2').click();
        cy.url().should("include", "/Creatures");
    });

    it("should not delete a creature", () => {
        cy.intercept("GET", 'http://localhost:8080/creatures/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2',
            {
                fixture: 'creature.json'
            });
        cy.intercept("DELETE", 'http://localhost:8080/creatures/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2',
            {
                statusCode: 400
            });
        cy.gotoCreature();
        cy.get('[data-cy=creature-7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2]').click();
        cy.url().should("include", "Creature/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2");
        cy.get('.mantine-ActionIcon-hover > svg').click();
        cy.get('[data-cy=creature-detail-edit]').click();
        cy.get('[data-cy=creature-edit-delete]').click();
        cy.get('.mantine-Button-filled > .mantine-3xbgk5 > .mantine-qo1k2').click();
        cy.get('[data-cy=creature-edit-error]').should("exist");
        cy.get('[data-cy=creature-edit-cancel]').click();
        cy.url().should("include", "/Creature/7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2");
    });
})