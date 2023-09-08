package LibrarySystem.Service;

import LibrarySystem.DTO.BookDTO;
import LibrarySystem.Entity.Book;
import LibrarySystem.Entity.User;
import LibrarySystem.Repository.BookRepository;
import LibrarySystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    // 도서 등록
    public void addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        bookRepository.save(book);
    }

    // 도서 수정
    public void updateBook(Long bookId, BookDTO bookDTO) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDTO.getTitle());
            book.setAuthor(bookDTO.getAuthor());
            bookRepository.save(book);
        }
    }

    // 도서 대출 이력 확인
    public Optional<Book> getBorrowedBooksByUserId(Long userId) {
        return bookRepository.findAllByUserId(userId);
    }

    // 도서 대출
    public void borrowBook(Long bookId, Long userId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalBook.isPresent() && optionalUser.isPresent()) {
            Book book = optionalBook.get();
            User user = optionalUser.get();

            if (book.isAvailable()) {
                book.setAvailable(false);
                book.setId(userId);
                book.setLoanDate(LocalDateTime.now());
                bookRepository.save(book);
            }
        }
    }

    // 도서 반납
    public void returnBook(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setAvailable(true);
            book.setId(null);
            bookRepository.save(book);
        }
    }
}
