CREATE TABLE IF NOT EXISTS dynamic_links (
    id SERIAL PRIMARY KEY,
    url TEXT NOT NULL,
    description TEXT
);

INSERT INTO dynamic_links (url, description)
VALUES ('https://example.com', 'Example description');
