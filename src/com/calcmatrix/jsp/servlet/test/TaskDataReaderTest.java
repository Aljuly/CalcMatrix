package com.calcmatrix.jsp.servlet.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.calcmatrix.jsp.servlet.InputDescription;
import com.calcmatrix.jsp.servlet.JsonCreator;
import com.calcmatrix.jsp.servlet.TaskData;

public class TaskDataReaderTest {

	TaskData taskdata; 
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		try {
            taskdata = (new JsonCreator("WebFormDesc.json")).getInputdata();
        } catch (IOException e) {
            e.printStackTrace();
        }
	    
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		taskdata = null;
	}
	
	@Test
	public void test() {
		Map<Integer, InputDescription> inputDataDesc = taskdata.getInputDataDesc();
		assertEquals(5, inputDataDesc.size());
		assertEquals(4, inputDataDesc.get(1).getInputfields().size());
		assertEquals(3, inputDataDesc.get(2).getInputfields().size());
		assertEquals(1, inputDataDesc.get(5).getInputfields().size());
	}

}
