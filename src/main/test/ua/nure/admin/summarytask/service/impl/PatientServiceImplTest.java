package ua.nure.admin.summarytask.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.admin.summarytask.repository.PatientRepository;
import ua.nure.admin.summarytask.service.PatientService;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceImplTest {

    @Mock
    private PatientRepository repository;
    private PatientService patientService;

    private String testPatientName = "patient1";
    private int testPatientId = 0;

    @Before
    public void setUp() throws Exception {

        Mockito.when(repository.getDoctorId(testPatientName)).thenReturn(testPatientId);
        patientService = new PatientServiceImpl(repository);
    }

    @Test
    public void testGetDoctorId() {
        final int actualPatientID = patientService.getId(testPatientName);
        Assert.assertEquals(actualPatientID, testPatientId);
    }

    @Test
    public void testWhenDoctorNameIsNull() {
        final int actualPatientID = patientService.getId(null);
        Assert.assertEquals(-1, actualPatientID);
    }
}