package ua.nure.admin.summarytask.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.admin.summarytask.repository.NurseRepository;
import ua.nure.admin.summarytask.service.NurseService;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class NurseServiceImplTest {

    @Mock
    private NurseRepository repository;
    private NurseService nurseService;

    private String testNurseName = "nurse1";
    private int testNurseId = 1;

    @Before
    public void setUp() throws Exception {
        Mockito.when(repository.getNurseId(testNurseName)).thenReturn(testNurseId);
        nurseService = new NurseServiceImpl(repository);
    }

    @Test
    public void testGetNurseId() {
        final int actualNurseID = nurseService.getNurseId(testNurseName);
        Assert.assertEquals(actualNurseID, testNurseId);
    }

    @Test
    public void testWhenNurseNameIsNull() {
        final int actualNurseID = nurseService.getNurseId(null);
        Assert.assertEquals(-1, actualNurseID);
    }
}