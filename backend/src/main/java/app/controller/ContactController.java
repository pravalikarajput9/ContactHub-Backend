
package app.controller;

import app.models.Contact;
import app.services.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174",
        "http://localhost:5175"
})
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getContacts(
            @RequestParam String ownerEmail) {
        return ResponseEntity.ok(service.getAll(ownerEmail));
    }

    @PostMapping
    public ResponseEntity<?> addContact(
            @RequestParam String ownerEmail,
            @RequestBody Contact contact) {
        if (contact.getName() == null ||
                contact.getName().isBlank()) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", "Contact name is required"));
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.add(contact, ownerEmail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(
            @PathVariable Long id,
            @RequestParam String ownerEmail,
            @RequestBody Contact contact) {
        try {
            return ResponseEntity.ok(
                    service.update(id, contact, ownerEmail));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(
            @PathVariable Long id,
            @RequestParam String ownerEmail) {
        try {
            service.delete(id, ownerEmail);
            return ResponseEntity.ok(
                    Map.of("message", "Contact deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}

