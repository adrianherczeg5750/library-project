CREATE TABLE IF NOT EXISTS books (
                                     id SERIAL PRIMARY KEY,
                                     title VARCHAR(100) NOT NULL,
                                     author VARCHAR(100) NOT NULL,
                                     quantity INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(50) NOT NULL,
                                     address VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS loans (
                                     id SERIAL PRIMARY KEY,
                                     book_id INTEGER NOT NULL REFERENCES books(id),
                                     user_id INTEGER NOT NULL REFERENCES users(id),
                                     quantity INTEGER NOT NULL,
                                     taken_out_at TIMESTAMP NOT NULL,
                                     brought_back_at TIMESTAMP
);
