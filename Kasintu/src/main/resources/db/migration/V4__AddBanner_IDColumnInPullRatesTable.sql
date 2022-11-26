ALTER TABLE pull_rates
    ADD banner_id VARCHAR(255) NULL;

ALTER TABLE pull_rates
    ADD CONSTRAINT FK_PULL_RATES_ON_BANNER FOREIGN KEY (banner_id) REFERENCES banners (banner_id);