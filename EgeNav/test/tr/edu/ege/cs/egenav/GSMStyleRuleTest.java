/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.ege.cs.egenav;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Özgün Yılmaz
 */
public class GSMStyleRuleTest {
    
    public GSMStyleRuleTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void toStringTest() {
    
        GSMStyleRule rule=new GSMStyleRule();
        rule.setColor("0x00ff00");
        rule.setWeight("3.9");
        rule.setVisibility(GSMStyleRule.VISIBILITY_ON);
        assertEquals(rule.toString(),"color:0x00ff00|weight:3.9|visibility:on");
        
    }
}
