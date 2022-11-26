import React from "react";
<reference types="cypress" />

Cypress.Commands.add('login', () => {
    cy.visit('http://localhost:3000/UserAccess')
    cy.get('[data-cy=login-input-username]').type("testAccount")
    cy.get('[data-cy=login-input-password]').type("test")
    cy.get('[data-cy=user-access-login]').click();
    cy.get('[data-cy=navbar-menu]').click();
    cy.get('[data-cy=navbar-summon]').click();
})

Cypress.Commands.add('logout', () => {
    cy.visit('http://localhost:3000/')
    cy.get('[data-cy=profile-menu]').click();
    cy.get('[data-cy=profile-menu-logout]').click();
})

describe("Summoning", () => {
    beforeEach(() => {
        cy.login();
    });
    afterEach(() => {
        cy.logout();
    });

    it("should summon once and get a creature", () => {
        cy.intercept("POST", 'http://localhost:8080/game/4d47dea224074ff1a6493752f8d979cd',
            {
                fixture: 'summononce.json'
            });
        cy.get('[data-cy=bannercard-one-summon]').click();
        cy.url().should("include", "/SummonBanner/77193dfaa72f4836bf1b2f079d9c38b9/1");
        cy.get('[data-cy=summon-creature-c11e3f5b89be41299c8d1d173928b10a]').should("exist");
    });

    it("should summon once and get none", () => {
        cy.intercept("POST", 'http://localhost:8080/game/4d47dea224074ff1a6493752f8d979cd',
            {
                fixture: 'summononcenull.json'
            });
        cy.get('[data-cy=bannercard-one-summon]').click();
        cy.url().should("include", "/SummonBanner/77193dfaa72f4836bf1b2f079d9c38b9/1");
        cy.get('[data-cy=summon-creature-null]').should("exist");
    });
    

    it("should summon multiple", () => {
        cy.intercept("POST", 'http://localhost:8080/game/4d47dea224074ff1a6493752f8d979cd',
            {
                fixture: 'summonmultiple.json'
            });
        cy.get('[data-cy=bannercard-multiple-summon]').click();
        cy.url().should("include", "/SummonBanner/77193dfaa72f4836bf1b2f079d9c38b9/10");
        cy.wait(4000);
        cy.get('[data-cy=summon-creature-b63032a9c18f44c08e45159341ebe2a7]').should("exist");
        cy.get('[data-cy=summon-creature-4f8682ed54494d97a98800054186bdda]').should("exist");
        cy.get('[data-cy=summon-creature-8c6be0fe31e14e8ca2f821ad13246ab5]').should("exist");
        cy.get('[data-cy=summon-creature-d218d2d592b540fe9a9ec11fa12a95bf]').should("exist");
        cy.get('[data-cy=summon-creature-a7f5b6dcab294856930dee133fb0c62b]').should("exist");
        cy.get('[data-cy=summon-creature-7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2]').should("exist");
    });


    it("should summon and can summon once again", () => {
        cy.intercept("POST", 'http://localhost:8080/game/4d47dea224074ff1a6493752f8d979cd',
            {
                fixture: 'summononce.json'
            });
        cy.get('[data-cy=bannercard-one-summon]').click();
        cy.url().should("include", "/SummonBanner/77193dfaa72f4836bf1b2f079d9c38b9/1");
        cy.get('[data-cy=summon-creature-c11e3f5b89be41299c8d1d173928b10a]').should("exist");
        cy.intercept("POST", 'http://localhost:8080/game/4d47dea224074ff1a6493752f8d979cd',
            {
                fixture: 'summononcenull.json'
            });
        cy.get('[data-cy=summon-once-again]').click();
        cy.url().should("include", "/SummonBanner/77193dfaa72f4836bf1b2f079d9c38b9/1");
        cy.get('[data-cy=summon-creature-null]').should("exist");
    });

    it("should summon and can summon multiple again", () => {
        cy.intercept("POST", 'http://localhost:8080/game/4d47dea224074ff1a6493752f8d979cd',
            {
                fixture: 'summonmultiple.json'
            });
        cy.get('[data-cy=bannercard-multiple-summon]').click();
        cy.url().should("include", "/SummonBanner/77193dfaa72f4836bf1b2f079d9c38b9/10");
        cy.wait(4000);
        cy.get('[data-cy=summon-creature-b63032a9c18f44c08e45159341ebe2a7]').should("exist");
        cy.get('[data-cy=summon-creature-4f8682ed54494d97a98800054186bdda]').should("exist");
        cy.get('[data-cy=summon-creature-8c6be0fe31e14e8ca2f821ad13246ab5]').should("exist");
        cy.get('[data-cy=summon-creature-d218d2d592b540fe9a9ec11fa12a95bf]').should("exist");
        cy.get('[data-cy=summon-creature-a7f5b6dcab294856930dee133fb0c62b]').should("exist");
        cy.get('[data-cy=summon-creature-7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2]').should("exist");
        cy.get('[data-cy=summon-multiple-again]').click();
        cy.url().should("include", "/SummonBanner/77193dfaa72f4836bf1b2f079d9c38b9/10");
        cy.wait(4000);
        cy.get('[data-cy=summon-creature-b63032a9c18f44c08e45159341ebe2a7]').should("exist");
        cy.get('[data-cy=summon-creature-b63032a9c18f44c08e45159341ebe2a7]').should("exist");
        cy.get('[data-cy=summon-creature-4f8682ed54494d97a98800054186bdda]').should("exist");
        cy.get('[data-cy=summon-creature-8c6be0fe31e14e8ca2f821ad13246ab5]').should("exist");
        cy.get('[data-cy=summon-creature-d218d2d592b540fe9a9ec11fa12a95bf]').should("exist");
        cy.get('[data-cy=summon-creature-a7f5b6dcab294856930dee133fb0c62b]').should("exist");
        cy.get('[data-cy=summon-creature-7cfb9bcb5ba34ce3bfed7f8b4dcc2ef2]').should("exist");
    });
})