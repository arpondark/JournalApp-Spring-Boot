package com.arpon.JournalApp.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("{username}")
    public ResponseEntity<List<JournalEntry>> getAllEntriesByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            List<JournalEntry> entries = journalEntryService.getAllEntriesByUserId(user.getId());
            return new ResponseEntity<>(entries, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    public List<JournalEntry> getAll() {
        return journalEntryService.getAllEntries();
    }

    @PostMapping
    public void createEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(new Date());

        journalEntryService.saveEntry(journalEntry);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalEntry> getByid(@PathVariable String id) {

       Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
       if(journalEntry.isPresent()) {
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable String id) {
        Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
        if(journalEntry.isPresent()) {
            journalEntryService.deleteEntryById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "id/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable String id, @RequestBody JournalEntry journalEntry) {
        Optional<JournalEntry> existingEntry = journalEntryService.findById(id);
        journalEntryService.updateEntry(id, journalEntry);

        if(existingEntry.isPresent()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
