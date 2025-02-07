-- Könyvek
INSERT INTO books (title, author, quantity) VALUES
                                                ('Effective Java', 'Joshua Bloch', 5),
                                                ('Clean Code', 'Robert C. Martin', 3),
                                                ('Java Concurrency in Practice', 'Brian Goetz', 4);

-- Felhasználók
INSERT INTO users (name, address) VALUES
                                      ('John Doe', '123 Main St'),
                                      ('Jane Smith', '456 Elm St'),
                                      ('Alice Johnson', '789 Oak St');

-- Kölcsönzések
INSERT INTO loans (book_id, user_id, quantity, taken_out_at) VALUES
                                                                 (1, 1, 1, NOW()),
                                                                 (2, 2, 2, NOW());
