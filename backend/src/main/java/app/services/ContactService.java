
package app.services;

import app.models.Contact;
import app.repo.ContactRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    private final ContactRepo repo;

    public ContactService(ContactRepo repo) {
        this.repo = repo;
    }

    public List<Contact> getAll(String ownerEmail) {
        return repo.findByOwnerEmailIgnoreCaseOrderByNameAsc(
                ownerEmail);
    }

    public Contact add(Contact contact, String ownerEmail) {
        contact.setId(null);
        contact.setOwnerEmail(ownerEmail);
        return repo.save(contact);
    }

    public Contact update(Long id, Contact input, String ownerEmail) {
        Contact existing = repo
                .findByIdAndOwnerEmailIgnoreCase(id, ownerEmail)
                .orElseThrow(() ->
                        new RuntimeException("Contact not found"));

        existing.setName(input.getName());
        existing.setEmail(input.getEmail());
        existing.setPhone(input.getPhone());
        existing.setAddress(input.getAddress());
        existing.setNotes(input.getNotes());

        return repo.save(existing);
    }

    public void delete(Long id, String ownerEmail) {
        Contact existing = repo
                .findByIdAndOwnerEmailIgnoreCase(id, ownerEmail)
                .orElseThrow(() ->
                        new RuntimeException("Contact not found"));

        repo.delete(existing);
    }
}

