package com.arpon.JournalApp.Service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.arpon.JournalApp.Repository.JournalEntryRepository;

class JournalEntryServiceTest {

    @Mock
    private JournalEntryRepository journalEntryRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private JournalEntryService journalEntryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // TODO: Update tests to match the new username-based API
    // The current API uses username and index-based operations instead of ID-based operations
    
    /*
    @Test
    void updateEntry_WhenEntryExists_ShouldUpdateOnlyProvidedFields() {
        // This test needs to be updated for the new API: updateEntryByUsernameAndIndex
    }

    @Test
    void updateEntry_WhenEntryDoesNotExist_ShouldCreateNewEntry() {
        // This test needs to be updated for the new API: updateEntryByUsernameAndIndex
    }
    */
}