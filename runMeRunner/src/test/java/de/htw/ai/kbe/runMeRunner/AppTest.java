package de.htw.ai.kbe.runMeRunner;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.htw.ai.kbe.runMeRunner.analyse.ClassAnalyse;

/**
 * Unit test for simple App.
 */
public class AppTest {
	private static final String CLASS_NAME= "de.htw.ai.kbe.runMeRunner.MyClassWithRunMes1";
	private static final int COUNT_METHODS = 8; 
	
	
    @Test
    public void testApp() throws Exception
    {
        ClassAnalyse classAnalyse = new ClassAnalyse(CLASS_NAME);
        
        Result result = new Result();
        result.setMethodCount(classAnalyse.countMethodsInClass());
    		int countFromClass = result.getMethodCount();
        
        assertTrue(countFromClass==COUNT_METHODS);
    }
    
    
}
