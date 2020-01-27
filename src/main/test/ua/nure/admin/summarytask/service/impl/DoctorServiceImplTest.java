package ua.nure.admin.summarytask.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.admin.summarytask.repository.DoctorRepository;
import ua.nure.admin.summarytask.repository.impl.DoctorRepositoryImpl;
import ua.nure.admin.summarytask.service.DoctorService;

@RunWith(MockitoJUnitRunner.class)
public class DoctorServiceImplTest {

    @Mock
    private DoctorRepository repository;
    private DoctorService doctorService;

    private String testDoctorName = "doctor1";
    private int testDoctorId = 1;

    @Before
    public void setUp() throws Exception {

        Mockito.when(repository.getDoctorId(testDoctorName)).thenReturn(testDoctorId);
        doctorService = new DoctorServiceImpl(repository);
    }

    @Test
    public void testGetDoctorId() {
        final int actualDoctorID = doctorService.getDoctorId(testDoctorName);
        Assert.assertEquals(actualDoctorID, testDoctorId);
    }

    @Test
    public void testWhenDoctorNameIsNull() {
        final int actualDoctorID = doctorService.getDoctorId(null);
        Assert.assertEquals(-1, actualDoctorID);
    }
}