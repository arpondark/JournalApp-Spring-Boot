package com.arpon.JournalApp.Service;


import java.util.List;

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
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            // Validate input
            if (journalEntry == null) {
                throw new RuntimeException("Journal entry cannot be null");
            }
            if (journalEntry.getTitle() == null || journalEntry.getTitle().trim().isEmpty()) {
                throw new RuntimeException("Journal entry title is required");
            }
            if (userName == null || userName.trim().isEmpty()) {
                throw new RuntimeException("Username is required");
            }
            
            // Find user
            User user = userService.findByUsername(userName);
            if (user == null) {
                throw new RuntimeException("User not found: " + userName);
            }
            
            // Set date and save entry
            journalEntry.setDate(new java.util.Date());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            
            // Add to user's entries
            if (user.getJournalEntries() == null) {
                user.setJournalEntries(new java.util.ArrayList<>());
            }
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
            
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the entry: " + e.getMessage(), e);
        }
    }

    // Remove id-based methods and add username-based methods
    public List<JournalEntry> getAllEntriesByUsername(String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return user.getJournalEntries();
        }
        return List.of();
    }

    public void deleteEntryByUsernameAndIndex(String username, int entryIndex) {
        User user = userService.findByUsername(username);
        if (user == null || user.getJournalEntries() == null || entryIndex < 0 || entryIndex >= user.getJournalEntries().size()) {
            throw new RuntimeException("Entry not found");
        }
        user.getJournalEntries().remove(entryIndex);
        userService.saveUser(user);
    }

    public void updateEntryByUsernameAndIndex(String username, int entryIndex, JournalEntry journalEntry) {
        User user = userService.findByUsername(username);
        if (user == null || user.getJournalEntries() == null || entryIndex < 0 || entryIndex >= user.getJournalEntries().size()) {
            throw new RuntimeException("Entry not found");
        }
        JournalEntry existingEntry = user.getJournalEntries().get(entryIndex);
        existingEntry.setTitle(journalEntry.getTitle());
        existingEntry.setContent(journalEntry.getContent());
        existingEntry.setDate(journalEntry.getDate() != null ? journalEntry.getDate() : existingEntry.getDate());
        userService.saveUser(user);
    }
}
