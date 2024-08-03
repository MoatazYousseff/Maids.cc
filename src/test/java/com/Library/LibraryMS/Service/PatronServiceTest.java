package com.Library.LibraryMS.Service;

import com.Library.LibraryMS.models.Patron;
import com.Library.LibraryMS.repos.PatronRepo;
import com.Library.LibraryMS.services.PatronService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

public class PatronServiceTest {
    @InjectMocks
    private PatronService patronService;
    @Mock
    PatronRepo patronRepo;
    @Mock
    private Patron patron1=new Patron();
    private Patron patron2=new Patron();

    @Test
    @DisplayName("get all patrons")
    public void testGetAllPatrons(){
        List<Patron> patrons=new ArrayList<>();
        patrons.add(patron1);
        patrons.add(patron2);
        Mockito.when(patronRepo.findAll()).thenReturn(patrons);
        List<Patron>result=patronService.getPatrons();
        Assert.assertNotNull(result);
        Assert.assertEquals(2,result.size());
    }
    @Test
    @DisplayName("Delete patron")
    public void testDeletePatron(){

        Mockito.when(patronRepo.findById(patron1.getPatronId())).thenReturn(Optional.of(patron1));
        patronService.deletePatron(patron1.getPatronId());
        Mockito.verify(patronRepo,Mockito.times(1)).deleteById(patron1.getPatronId());
    }
    Patron patron=new Patron();
    @BeforeEach
    public void setValues(){
        patron.setPatronId(1L);
        patron.setName("jack");
        patron.setPhone(123456789L);
        patron.setEmail("thecret@gmail.com");

    }
    @Test
    @DisplayName("Add patron")
    public void testAddPatron(){

        Mockito.when(patronRepo.save(Mockito.any(Patron.class))).thenReturn(patron);
        Patron result=patronService.addPatron(patron);
        Assert.assertNotNull(result);
        Assert.assertEquals("thecret@gmail.com",result.getEmail());
    }
    @Test
    @DisplayName("Edit patron")
    public void testEditPatron(){
        Mockito.when(patronRepo.findById(patron.getPatronId())).thenReturn(Optional.of(patron));
        Patron editedPatron=new Patron();
        editedPatron.setEmail("ff@gmail.com");
        editedPatron.setPhone(512346789L);
        editedPatron.setName("muha");
        editedPatron.setPatronId(1L);
        Mockito.when(patronRepo.save(editedPatron)).thenReturn(editedPatron);
        Patron result=patronService.updatePatron(editedPatron,editedPatron.getPatronId());
        Assert.assertNotNull(result);
        Assert.assertEquals("muha",result.getName());
    }
}
