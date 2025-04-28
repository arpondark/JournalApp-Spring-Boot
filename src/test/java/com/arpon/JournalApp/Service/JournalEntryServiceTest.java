package com.arpon.JournalApp.Service;

import com.arpon.JournalApp.Entity.JournalEntry;
import com.arpon.JournalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JournalEntryServiceTest {

    @Mock
    private JournalEntryRepository journalEntryRepository;

    @InjectMocks
    private JournalEntryService journalEntryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateEntry_WhenEntryExists_ShouldUpdateOnlyProvidedFields() {
        // Arrange
        String id = "507f1f77bcf86cd799439011"; // Valid ObjectId string
        ObjectId objectId = new ObjectId(id);
        
        // Create existing entry with all fields set
        JournalEntry existingEntry = new JournalEntry();
        existingEntry.setId(objectId);
        existingEntry.setTitle("Original Title");
        existingEntry.setContent("Original Content");
        Date originalDate = new Date();
        existingEntry.setDate(originalDate);
        
        // Create update request with only title provided
        JournalEntry updateRequest = new JournalEntry();
        updateRequest.setTitle("Updated Title");
        // Content and date are not provided in the update request
        
        // Mock repository behavior
        when(journalEntryRepository.findById(objectId)).thenReturn(Optional.of(existingEntry));
        
        // Act
        journalEntryService.updateEntry(id, updateRequest);
        
        // Assert
        // Verify that save was called with an entry that has:
        // - Updated title
        // - Original content (preserved)
        // - Original date (preserved)
        verify(journalEntryRepository).save(argThat(savedEntry -> 
            savedEntry.getTitle().equals("Updated Title") &&
            savedEntry.getContent().equals("Original Content") &&
            savedEntry.getDate().equals(originalDate)
        ));
        
        System.out.println("[DEBUG_LOG] Test updateEntry_WhenEntryExists_ShouldUpdateOnlyProvidedFields passed");
    }

    @Test
    void updateEntry_WhenEntryDoesNotExist_ShouldCreateNewEntry() {
        // Arrange
        String id = "507f1f77bcf86cd799439011"; // Valid ObjectId string
        ObjectId objectId = new ObjectId(id);
        
        // Create update request
        JournalEntry updateRequest = new JournalEntry();
        updateRequest.setTitle("New Title");
        updateRequest.setContent("New Content");
        
        // Mock repository behavior - entry doesn't exist
        when(journalEntryRepository.findById(objectId)).thenReturn(Optional.empty());
        
        // Act
        journalEntryService.updateEntry(id, updateRequest);
        
        // Assert
        // Verify that save was called with an entry that has:
        // - The specified ID
        // - The provided title and content
        verify(journalEntryRepository).save(argThat(savedEntry -> 
            savedEntry.getId().equals(objectId) &&
            savedEntry.getTitle().equals("New Title") &&
            savedEntry.getContent().equals("New Content")
        ));
        
        System.out.println("[DEBUG_LOG] Test updateEntry_WhenEntryDoesNotExist_ShouldCreateNewEntry passed");
    }
}