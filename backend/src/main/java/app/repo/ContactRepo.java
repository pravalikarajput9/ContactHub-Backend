
package app.repo;

import app.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepo extends JpaRepository<Contact, Long> {

    List<Contact> findByOwnerEmailIgnoreCaseOrderByNameAsc(
            String ownerEmail);

    Optional<Contact> findByIdAndOwnerEmailIgnoreCase(
            Long id, String ownerEmail);
}

