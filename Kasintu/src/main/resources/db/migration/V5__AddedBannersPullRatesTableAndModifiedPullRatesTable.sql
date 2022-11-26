CREATE TABLE banners_pull_rates
(
    banner_id    VARCHAR(255) NOT NULL,
    pull_rate_id VARCHAR(255) NOT NULL
);

ALTER TABLE banners_pull_rates
    ADD CONSTRAINT uc_banners_pull_rates_pull_rate UNIQUE (pull_rate_id);

ALTER TABLE banners_pull_rates
    ADD CONSTRAINT fk_banpulrat_on_banner FOREIGN KEY (banner_id) REFERENCES banners (banner_id);

ALTER TABLE banners_pull_rates
    ADD CONSTRAINT fk_banpulrat_on_pull_rate FOREIGN KEY (pull_rate_id) REFERENCES pull_rates (pull_rate_id);

ALTER TABLE pull_rates
DROP
FOREIGN KEY FK_PULL_RATES_ON_BANNER;

ALTER TABLE pull_rates
DROP
COLUMN banner_id;