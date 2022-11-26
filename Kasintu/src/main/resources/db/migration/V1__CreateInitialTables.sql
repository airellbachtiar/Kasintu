CREATE TABLE users
(
    user_id varchar (100) NOT NULL,
    username varchar(20),
    email varchar(100),
    password varchar(100),
    start_date date,
    PRIMARY KEY (user_id),
    UNIQUE(username)
);

CREATE TABLE admins
(
    admin_id varchar (100) NOT NULL,
    user_id varchar (100),
    PRIMARY KEY (admin_id),
    FOREIGN KEY(user_id) REFERENCES users (user_id)
);

CREATE TABLE players
(
    player_id varchar (100) NOT NULL,
    user_id varchar(100),
    coin int ,
    PRIMARY KEY (player_id),
    FOREIGN KEY(user_id) REFERENCES users (user_id)
);

CREATE TABLE inventories
(
    inventory_id varchar (100) NOT NULL,
    player_id varchar (100),
    PRIMARY KEY (inventory_id),
    FOREIGN KEY(player_id) REFERENCES players (player_id)
);

CREATE TABLE rarities
(
    rarity_id varchar(100) NOT NULL,
    type varchar(20) NOT NULL,
    PRIMARY KEY (rarity_id)
);

CREATE TABLE creatures
(
    creature_id varchar(100) NOT NULL,
    rarity_id varchar(100),
    name varchar(50),
    description varchar(400),
    PRIMARY KEY (creature_id),
    FOREIGN KEY (rarity_id) REFERENCES rarities (rarity_id)
);

CREATE TABLE owned_creatures
(
    owned_creature_id varchar(100) NOT NULL,
    creature_id varchar(100),
    inventory_id varchar(100),
    PRIMARY KEY (owned_creature_id),
    FOREIGN KEY(creature_id) REFERENCES creatures(creature_id),
    FOREIGN KEY(inventory_id) REFERENCES inventories(inventory_id)
);

CREATE TABLE banners
(
    banner_id varchar(100) NOT NULL,
    start_date date ,
    end_date date ,
    is_available bit ,
    cost INT,
    PRIMARY KEY (banner_id)
);

CREATE TABLE pull_rates
(
    pull_rate_id varchar(100) NOT NULL,
    rarity_id varchar(100),
    banner_id varchar(100),
    pull_rate int ,
    PRIMARY KEY (pull_rate_id),
    FOREIGN KEY (rarity_id) REFERENCES rarities(rarity_id),
    FOREIGN KEY (banner_id) REFERENCES banners(banner_id)
);

CREATE TABLE obtainable_creatures
(
    creature_id varchar(100),
    banner_id varchar(100),
    FOREIGN KEY(creature_id) REFERENCES creatures (creature_id),
    FOREIGN KEY(banner_id) REFERENCES banners(banner_id)
);