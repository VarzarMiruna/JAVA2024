CREATE TABLE authors (
    authorid SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE books (
    bookid SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(100),
    language VARCHAR(100),
    publicationdate INT,
    numberofpages INT
);

CREATE TABLE genres (
    genreid SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE bookgenres (
    bookid INT,
    genreid INT,
    PRIMARY KEY (bookid, genreid),
    FOREIGN KEY (bookid) REFERENCES books(bookid),
    FOREIGN KEY (genreid) REFERENCES genres(genreid)
);
