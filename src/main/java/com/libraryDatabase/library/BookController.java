package com.libraryDatabase.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("")
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") int id) {
        return bookRepository.getById(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Book> books) {
        return bookRepository.save(books);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.getById(id);

        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setGenre(updatedBook.getGenre());
            book.setRating(updatedBook.getRating());

            bookRepository.update(book);

            return 200;

        } else {

            return 404;

        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.getById(id);

        if (book != null) {

            if (updatedBook.getTitle() != null) {
                book.setTitle(updatedBook.getTitle());
            }

            if (updatedBook.getAuthor() != null) {
                book.setAuthor(updatedBook.getAuthor());
            }

            if (updatedBook.getGenre() != null) {
                book.setGenre(updatedBook.getGenre());
            }

            if (updatedBook.getRating() > 0) {
                book.setRating(updatedBook.getRating());
            }

            bookRepository.update(book);

            return 200;

        } else {

            return 404;

        }
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        bookRepository.delete(id);

        return 200;

    }
}
