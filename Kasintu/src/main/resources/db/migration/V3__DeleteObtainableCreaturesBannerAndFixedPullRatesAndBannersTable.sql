ALTER TABLE obtainable_creatures
DROP
FOREIGN KEY obtainable_creatures_ibfk_1;

ALTER TABLE obtainable_creatures
DROP
FOREIGN KEY obtainable_creatures_ibfk_2;

ALTER TABLE pull_rates
DROP
FOREIGN KEY pull_rates_ibfk_2;

DROP TABLE obtainable_creatures;

ALTER TABLE pull_rates
DROP
COLUMN banner_id;

ALTER TABLE banners
DROP
COLUMN end_date;

ALTER TABLE banners
DROP
COLUMN is_available;

ALTER TABLE banners
DROP
COLUMN start_date;