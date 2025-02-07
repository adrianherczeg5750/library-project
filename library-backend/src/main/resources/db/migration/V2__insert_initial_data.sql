
INSERT INTO books (title, author, quantity) VALUES
                                                ('Effective Java', 'Joshua Bloch', 5),
                                                ('Clean Code', 'Robert C. Martin', 3),
                                                ('Java Concurrency in Practice', 'Brian Goetz', 4),
                                                ('The Pragmatic Programmer', 'Andrew Hunt', 6),
                                                ('Refactoring', 'Martin Fowler', 5),
                                                ('Design Patterns', 'Erich Gamma', 4),
                                                ('Code Complete', 'Steve McConnell', 3),
                                                ('The Mythical Man-Month', 'Frederick P. Brooks Jr.', 5),
                                                ('You Dont Know JS', 'Kyle Simpson', 7),
                                                ('Introduction to the Theory of Computation', 'Michael Sipser', 4),
                                                ('The Art of Computer Programming', 'Donald Knuth', 2),
                                                ('Patterns of Enterprise Application Architecture', 'Martin Fowler', 6),
                                                ('Domain-Driven Design', 'Eric Evans', 4),
                                                ('Harry Potter and the Deathly Hallows', 'J.K. Rowling', 7);


INSERT INTO users (name, address) VALUES
                                      ('John Doe', '123 Main St'),
                                      ('Jane Smith', '456 Elm St'),
                                      ('Alice Johnson', '789 Oak St'),
                                      ('Bob Marley', '321 Pine St'),
                                      ('Charlie Brown', '654 Maple St'),
                                      ('David White', '987 Birch St'),
                                      ('Emma Watson', '741 Cedar St'),
                                      ('Frank Black', '852 Spruce St'),
                                      ('Grace Green', '963 Willow St'),
                                      ('Henry Ford', '159 Oakwood St'),
                                      ('Ivy Adams', '357 Cherry St'),
                                      ('Jack Daniels', '258 Ash St'),
                                      ('Karen Blue', '753 Redwood St');


INSERT INTO loans (book_id, user_id, quantity, taken_out_at) VALUES
                                                                 (1, 1, 1, NOW()),
                                                                 (2, 2, 2, NOW()),
                                                                 (3, 3, 1, NOW()),
                                                                 (4, 4, 1, NOW()),
                                                                 (5, 5, 2, NOW()),
                                                                 (6, 6, 1, NOW()),
                                                                 (7, 7, 1, NOW()),
                                                                 (8, 8, 2, NOW()),
                                                                 (9, 9, 1, NOW()),
                                                                 (10, 10, 1, NOW()),
                                                                 (1, 5, 1, NOW()),
                                                                 (2, 6, 1, NOW()),
                                                                 (3, 7, 1, NOW()),
                                                                 (4, 8, 2, NOW()),
                                                                 (5, 9, 1, NOW()),
                                                                 (6, 10, 1, NOW()),
                                                                 (7, 1, 2, NOW()),
                                                                 (8, 2, 1, NOW()),
                                                                 (9, 3, 1, NOW()),
                                                                 (10, 4, 1, NOW()),
                                                                 (1, 7, 1, NOW()),
                                                                 (2, 8, 2, NOW());
