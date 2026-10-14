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

    // Get all contacts for a user
    @GetMapping
    public ResponseEntity<?> getContacts(
            @RequestParam String ownerEmail) {
        try {
            List<Contact> contacts = service.getAll(ownerEmail);
            return ResponseEntity.ok(contacts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    // Add a new contact
    @PostMapping
    public ResponseEntity<?> addContact(
            @RequestParam String ownerEmail,
            @RequestBody Contact contact) {
        try {
            if (contact == null ||
                    contact.getName() == null ||
                    contact.getName().isBlank()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message",
                                "Contact name is required"));
            }

            Contact savedContact = service.add(contact, ownerEmail);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedContact);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    // Update an existing contact
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContact(
            @PathVariable Long id,
            @RequestParam String ownerEmail,
            @RequestBody Contact contact) {
        try {
            Contact updatedContact =
                    service.update(id, contact, ownerEmail);

            return ResponseEntity.ok(updatedContact);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }

    // Delete a contact
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(
            @PathVariable Long id,
            @RequestParam String ownerEmail) {
        try {
            service.delete(id, ownerEmail);

            return ResponseEntity.ok(
                    Map.of("message",
                            "Contact deleted successfully"));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", e.getMessage()));
        }
    }
}
