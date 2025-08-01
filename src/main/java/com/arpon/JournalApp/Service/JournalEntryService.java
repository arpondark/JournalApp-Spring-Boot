package com.arpon.JournalApp.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpon.JournalApp.Entity.JournalEntry;
import com.arpon.JournalApp.Entity.User;
import com.arpon.JournalApp.Repository.JournalEntryRepository;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    
    @Autowired
    private UserService userService;

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(String id) {
        ObjectId objectId = new ObjectId(id);
        return journalEntryRepository.findById(objectId);
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }

    public void updateEntry(String id, JournalEntry journalEntry) {
        ObjectId objectId = new ObjectId(id);
        Optional<JournalEntry> existingEntryOptional = journalEntryRepository.findById(objectId);

        if (existingEntryOptional.isPresent()) {
            JournalEntry existingEntry = existingEntryOptional.get();

            // Update the fields that are provided in the request
            // Title is marked as @NonNull, so it should always be present
            existingEntry.setTitle(journalEntry.getTitle());
            
            if (journalEntry.getContent() != null) {
                existingEntry.setContent(journalEntry.getContent());
            }
            // Keep the original date if not provided in the request
            if (journalEntry.getDate() != null) {
                existingEntry.setDate(journalEntry.getDate());
            }

            journalEntryRepository.save(existingEntry);
        } else {
            // If the entry doesn't exist, set the ID and save as a new entry
            journalEntry.setId(objectId);
            journalEntryRepository.save(journalEntry);
        }
    }

    public void deleteEntryById(String id) {
        ObjectId objectId = new ObjectId(id);
        journalEntryRepository.deleteById(objectId);
    }

    public List<JournalEntry> getAllEntriesByUserId(ObjectId userId) {
        Optional<User> userOptional = userService.findUserById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get().getJournalEntries();
        }
        return List.of();
    }
}
