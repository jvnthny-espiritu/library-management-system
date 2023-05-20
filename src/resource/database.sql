CREATE DATABASE library;

USE library;

CREATE TABLE librarian (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    username VARCHAR(45),
    password VARCHAR(45)
);

CREATE TABLE book (
    book_title VARCHAR(255) PRIMARY KEY,
    ISBN VARCHAR(13),
    author VARCHAR(100),
    publication_year VARCHAR(4),
    genre VARCHAR(50)
);

CREATE TABLE student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    year_level VARCHAR(1),
    program VARCHAR(255)
);

CREATE TABLE records (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    book_title VARCHAR(255) NOT NULL,
    date_borrowed VARCHAR(10),
    due_date VARCHAR(10),
    returned_date VARCHAR(10),
    penalties VARCHAR(255),
    status VARCHAR(20)
);

INSERT INTO librarian (name, username, password) VALUES
    ('Admin', 'admin', 'admin'),
    ('Jave Anthony Espiritu', 'javeespiritu', 'jave'),
    ('China Mae Gonzales', 'chinagonzales', 'china'),
    ('Caryll Labay', 'carylllabay', 'caryll');

INSERT INTO book (book_title, ISBN, author, publication_year, genre) VALUES
    ('Atalanta Atalanta', '9781250855572', 'Jennifer Saint', '2023', 'Fantasy'),
    ('Blood Debts', '9781250825926', 'Terry J. Benton-Walker', '2023', 'Mystery'),
    ('Dirty Laundry', '9780593497388', 'Disha Bose', '2023', 'Thriller'),
    ('Flipped', '9780375825446', 'Wendelin Vann Draanen', '2001', 'Romance'),
    ('Harry Potter and the Deathly Hollows', '0747591059', 'J. K. Rowling', '2007', 'Fantasy'),
    ('Looking for Alaska', '9781435249158', 'John Green', '2005', 'Young Adult'),
    ('March: Book One', '9781603093002', 'John Lewis, Andrew Aydin, and Nate Powell', '2013', 'Young Adult'),
    ('Sharp Objects', '9788417125721', 'Gillian Flynn', '2006', 'Mystery'),
    ('The Nanny', '9780593549353', 'Lana Ferguson', '2023', 'Romance'),
    ('The Perks of Being a Wallflower', '9780671027346', 'Steven Chbosky', '1999', 'Young Adult'),
    ('The Woman in the Window', '9780008234188', 'A.J. Finn', '2018', 'Thriller');

INSERT INTO student (name, year_level, program) VALUES
    ('Ann Placido', '4', 'Information Technology'),
    ('Juan Dela Cruz', '1', 'Computer Science'),
    ('Maria Santos', '2', 'Biology'),
    ('Miguel Reyes', '3', 'Business Administration'),
    ('Sofia Gomez', '4', 'Psychology'),
    ('Diego Hernandez', '1', 'Engineering'),
    ('Isabella Lopez', '2', 'English Literature'),
    ('Andres Torres', '3', 'Mathematics'),
    ('Camila Rivera', '4', 'History'),
    ('Gabriel Morales', '1', 'Physics'),
    ('Luna Perez', '2', 'Chemistry');

INSERT INTO records (student_id, book_title, date_borrowed, due_date, returned_date, penalties, status) VALUES
    (1, 'The Woman in the Window', '2023-05-01', '2023-05-08', '2023-05-09', '0', 'returned'),
    (2, 'Flipped', '2023-05-02', '2023-05-09', '2023-05-10', '0', 'returned'),
    (3, 'Dirty Laundry', '2023-05-03', '2023-05-10', NULL, '0', 'not yet return'),
    (4, 'Harry Potter and the Deathly Hollows', '2023-05-04', '2023-05-11', NULL, '0', 'not yet return'),
    (5, 'Looking for Alaska', '2023-05-05', '2023-05-12', NULL, '0', 'not yet return'),
    (6, 'The Nanny', '2023-05-06', '2023-05-13', NULL, '0', 'not yet return');
