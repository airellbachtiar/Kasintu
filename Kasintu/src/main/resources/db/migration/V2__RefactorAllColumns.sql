ALTER TABLE obtainable_creatures
    ADD CONSTRAINT uc_obtainable_creatures_creature UNIQUE (creature_id);

ALTER TABLE admins
    MODIFY admin_id VARCHAR (255);

ALTER TABLE banners
    MODIFY banner_id VARCHAR (255);

ALTER TABLE obtainable_creatures
    MODIFY banner_id VARCHAR (255);

ALTER TABLE obtainable_creatures
    MODIFY banner_id VARCHAR (255) NOT NULL;

ALTER TABLE pull_rates
    MODIFY banner_id VARCHAR (255);

ALTER TABLE creatures
    MODIFY creature_id VARCHAR (255);

ALTER TABLE obtainable_creatures
    MODIFY creature_id VARCHAR (255);

ALTER TABLE obtainable_creatures
    MODIFY creature_id VARCHAR (255) NOT NULL;

ALTER TABLE owned_creatures
    MODIFY creature_id VARCHAR (255);

ALTER TABLE creatures
    MODIFY `description` VARCHAR (255);

ALTER TABLE users
    MODIFY email VARCHAR (255);

ALTER TABLE banners
DROP
COLUMN end_date;

ALTER TABLE banners
DROP
COLUMN start_date;

ALTER TABLE banners
    ADD end_date datetime NULL;

ALTER TABLE inventories
    MODIFY inventory_id VARCHAR (255);

ALTER TABLE owned_creatures
    MODIFY inventory_id VARCHAR (255);

ALTER TABLE creatures
    MODIFY name VARCHAR (255);

ALTER TABLE owned_creatures
    MODIFY owned_creature_id VARCHAR (255);

ALTER TABLE users
    MODIFY password VARCHAR (255);

ALTER TABLE inventories
    MODIFY player_id VARCHAR (255);

ALTER TABLE players
    MODIFY player_id VARCHAR (255);

ALTER TABLE pull_rates
    MODIFY pull_rate_id VARCHAR (255);

ALTER TABLE creatures
    MODIFY rarity_id VARCHAR (255);

ALTER TABLE pull_rates
    MODIFY rarity_id VARCHAR (255);

ALTER TABLE rarities
    MODIFY rarity_id VARCHAR (255);

ALTER TABLE banners
    ADD start_date datetime NULL;

ALTER TABLE rarities
    MODIFY type VARCHAR (255);

ALTER TABLE rarities
    MODIFY type VARCHAR (255) NULL;

ALTER TABLE admins
    MODIFY user_id VARCHAR (255);

ALTER TABLE players
    MODIFY user_id VARCHAR (255);

ALTER TABLE users
    MODIFY user_id VARCHAR (255);

ALTER TABLE users
    MODIFY username VARCHAR (255);