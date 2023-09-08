package LibrarySystem.Controller;

import LibrarySystem.DTO.BookDTO;
import LibrarySystem.Entity.Book;
import LibrarySystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BookDTO bookDTO) {
        bookService.addBook(bookDTO);
        return ResponseEntity.ok("Book added successfully");
    }

    @PutMapping("/{bookId}/update")
    public ResponseEntity<String> updateBook(@PathVariable Long bookId, @RequestBody BookDTO bookDTO) {
        bookService.updateBook(bookId, bookDTO);
        return ResponseEntity.ok("Book updated successfully");
    }

    @GetMapping("/borrowed/{userId}")
    public ResponseEntity<Optional<Book>> getBorrowedBooks(@PathVariable Long userId) {
        Optional<Book> borrowedBooks = bookService.getBorrowedBooksByUserId(userId);
        return ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping("/borrow/{bookId}/{userId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long userId) {
        bookService.borrowBook(bookId, userId);
        return ResponseEntity.ok("Book borrowed successfully");
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        bookService.returnBook(bookId);
        return ResponseEntity.ok("Book returned successfully");
    }
}
