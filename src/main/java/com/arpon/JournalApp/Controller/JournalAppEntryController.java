package com.arpon.JournalApp.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpon.JournalApp.Entity.JournalEntry;
import com.arpon.JournalApp.Entity.User;
import com.arpon.JournalApp.Service.JournalEntryService;
import com.arpon.JournalApp.Service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalAppEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<List<JournalEntry>> getAllEntriesByUsername(@PathVariable String username) {
        List<JournalEntry> entries = journalEntryService.getAllEntriesByUsername(username);
        if (entries != null && !entries.isEmpty()) {
            return new ResponseEntity<>(entries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public List<JournalEntry> getAll() {
        return journalEntryService.getAllEntries();
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> createEntry(@PathVariable String username, @RequestBody JournalEntry journalEntry) {
        try {
            journalEntryService.saveEntry(journalEntry, username);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{username}/{entryIndex}")
    public ResponseEntity<JournalEntry> getEntryByIndex(@PathVariable String username, @PathVariable int entryIndex) {
        User user = userService.findByUsername(username);
        if (user != null && user.getJournalEntries() != null && entryIndex >= 0 && entryIndex < user.getJournalEntries().size()) {
            return new ResponseEntity<>(user.getJournalEntries().get(entryIndex), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{username}/{entryIndex}")
    public ResponseEntity<?> deleteEntry(@PathVariable String username, @PathVariable int entryIndex) {
        try {
            journalEntryService.deleteEntryByUsernameAndIndex(username, entryIndex);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{username}/{entryIndex}")
    public ResponseEntity<?> updateEntry(@PathVariable String username, @PathVariable int entryIndex, @RequestBody JournalEntry journalEntry) {
        try {
            journalEntryService.updateEntryByUsernameAndIndex(username, entryIndex, journalEntry);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
