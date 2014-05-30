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
public class GSMStyleTest {
    
    public GSMStyleTest() {
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
    public void toStringTest1() {
    
        GSMStyle style=new GSMStyle("road.local","geometry");
        
        GSMStyleRule rule=new GSMStyleRule();
        rule.setColor("0x00ff00");
        rule.setWeight("1");
        rule.setVisibility(GSMStyleRule.VISIBILITY_ON);
        style.addRule(rule);
        assertEquals(style.toString(),"style=feature:road.local|element:geometry|color:0x00ff00|weight:1|visibility:on");
    }
    
    @Test
    public void toStringTest2() {
    
        GSMStyle style=new GSMStyle("administrative","labels");
        GSMStyleRule rule=new GSMStyleRule();
        rule.setWeight("3.9");
        rule.setVisibility(GSMStyleRule.VISIBILITY_ON);
        rule.setInverseLightness(true);
        style.addRule(rule);
        assertEquals(style.toString(),"style=feature:administrative|element:labels|weight:3.9|visibility:on|invert_lightness:true");
    }
    
    @Test
    public void toStringTest3() {
    
        GSMStyle style=new GSMStyle("poi","");
        GSMStyleRule rule=new GSMStyleRule();
        rule.setVisibility(GSMStyleRule.VISIBILITY_SIMP);
        style.addRule(rule);
        assertEquals(style.toString(),"style=feature:poi|visibility:simplified");
    }
}
