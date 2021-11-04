package guru.springframework.sdjpaintro;

import guru.springframework.sdjpaintro.domain.Book;
import guru.springframework.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.contentOf;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaTestSlice {

    @Autowired
    BookRepository bookRepository;

    @Order(2)
    @Test
    void testJpaTestSlice() {
        long countBefore = bookRepository.count();

        bookRepository.save(new Book("Our fourth book", "3224", "KremenaG"));
        long countAfterSlice = bookRepository.count();

        assertThat(countBefore).isEqualTo(countAfterSlice-1);
        assertThat(countBefore).isEqualTo(3);
        assertThat(countAfterSlice).isEqualTo(4);
    }

    @Commit
    @Order(1)
    @Test
    void testJpaTestSliceTransaction() {
        bookRepository.save(new Book("Our third book", "3223", "KremenaG"));
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);

    }
}
