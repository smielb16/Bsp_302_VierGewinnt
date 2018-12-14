/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author elisc
 */
@RunWith(value = Parameterized.class)
public class VierGewinntBLTest {

    @Parameter(value = 0)
    public int col;

    @Parameter(value = 1)
    public int timesCalled;
    
    @Parameter(value = 2)
    public int colexp;

    @Parameters(name = "Test")
    public static Iterable<Object[]> data1() {
        return Arrays.asList(new Object[][]{
            {1, 4, 6-4},
            {2, 2, 6-2},
            {3, 5, 6-5},
            {4, 1, 6-1},
            {5, 1, 6-1},
            {6, 3, 6-3},
        });
    }

    public VierGewinntBLTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of makeMove method, of class VierGewinntBL.
     * @throws java.lang.Exception
     */
    @Test
    public void testMakeMove() throws Exception {
        System.out.println("makeMove");
        VierGewinntBL instance = new VierGewinntBL();
        for (int i = 0; i < timesCalled; i++) {
            instance.makeMove(col);
            instance.getRow(col);
        }
        int exp = instance.getRow(col);
        assertEquals(exp, colexp);
    }

}
