/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reason.filesplitter.logic;

import com.reason.filesplitter.logic.SymbolsPattern;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author reason
 */
public class SymbolsPatternTest {
    
    public SymbolsPatternTest() {
    }

    /**
     * Test of checkOnSymbols method, of class SymbolsPattern.
     */
    @Test
    public void testCheckOnPunctSymbols() {
        System.out.println("checkOnSymbols");
        String line = ",Hello,";
        SymbolsPattern instance = new SymbolsPattern();
        boolean result = instance.checkOnPunctSymbols(line);
        assertTrue(result);
    }

    /**
     * Test of removeSymbols method, of class SymbolsPattern.
     */
    @Test
    public void testRemovePunctSymbols() {
        System.out.println("removeSymbols");
        String line = ",Hello,";
        SymbolsPattern instance = new SymbolsPattern();
        String expResult = "Hello";
        String result = instance.removePunctSymbols(line);
        assertEquals(expResult, result);
    }
    
}
