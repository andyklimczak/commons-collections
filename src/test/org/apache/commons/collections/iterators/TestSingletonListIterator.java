/*
 * $Header: /home/jerenkrantz/tmp/commons/commons-convert/cvs/home/cvs/jakarta-commons//collections/src/test/org/apache/commons/collections/iterators/TestSingletonListIterator.java,v 1.1 2002/08/17 11:39:16 scolebourne Exp $
 * $Revision: 1.1 $
 * $Date: 2002/08/17 11:39:16 $
 *
 * ====================================================================
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2001 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.commons.collections.iterators;

import junit.framework.*;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Tests the SingletonListIterator.
 *
 * @author Stephen Colebourne
 * @version $Id: TestSingletonListIterator.java,v 1.1 2002/08/17 11:39:16 scolebourne Exp $
 */
public class TestSingletonListIterator extends TestIterator {

    private static final Object testValue = "foo";
    
    public static Test suite() {
        return new TestSuite(TestSingletonListIterator.class);
    }
    
    public TestSingletonListIterator(String testName) {
        super(testName);
    }
    
    /**
     * Returns null. SingletonListIterator can never be empty;
     * they always have exactly one element.
     * 
     * @return null
     */
    public Iterator makeEmptyIterator() {
        return null;
    }

    public Iterator makeFullIterator() {
        return new SingletonListIterator( testValue );
    }

    /**
     * Return a new, empty {@link Object} to used for testing.
     */
    public Object makeObject() {
        return makeFullIterator();
    }
    
    /**
     * Whether or not we are testing an iterator that can be
     * empty.  SingletonIterators are never empty;
     * 
     * @return false
     */
    public boolean supportsEmptyIterator() {
        return false;
    }

    public void testIterator() {
        ListIterator iter = (ListIterator) makeObject();
        assertTrue( "Iterator should have next item", iter.hasNext() );
        assertTrue( "Iterator should have no previous item", !iter.hasPrevious() );
        assertEquals( "Iteration next index", 0, iter.nextIndex() );
        assertEquals( "Iteration previous index", -1, iter.previousIndex() );
        
        Object iterValue = iter.next();
        assertEquals( "Iteration value is correct", testValue, iterValue );
        
        assertTrue( "Iterator should have no next item", !iter.hasNext() );
        assertTrue( "Iterator should have previous item", iter.hasPrevious() );
        assertEquals( "Iteration next index", 1, iter.nextIndex() );
        assertEquals( "Iteration previous index", 0, iter.previousIndex() );

        iterValue = iter.previous();
        assertEquals( "Iteration value is correct", testValue, iterValue );
        
        assertTrue( "Iterator should have next item", iter.hasNext() );
        assertTrue( "Iterator should have no previous item", !iter.hasPrevious() );
        assertEquals( "Iteration next index", 0, iter.nextIndex() );
        assertEquals( "Iteration previous index", -1, iter.previousIndex() );

        iterValue = iter.next();
        assertEquals( "Iteration value is correct", testValue, iterValue );
        
        assertTrue( "Iterator should have no next item", !iter.hasNext() );
        assertTrue( "Iterator should have previous item", iter.hasPrevious() );
        assertEquals( "Iteration next index", 1, iter.nextIndex() );
        assertEquals( "Iteration previous index", 0, iter.previousIndex() );

    	try {
    	    iter.next();
    	} catch (Exception e) {
    	  assertTrue("NoSuchElementException must be thrown", 
    		 e.getClass().equals((new NoSuchElementException()).getClass()));
    	}
        iter.previous();
        try {
            iter.previous();
        } catch (Exception e) {
          assertTrue("NoSuchElementException must be thrown", 
             e.getClass().equals((new NoSuchElementException()).getClass()));
        }
    }
}

