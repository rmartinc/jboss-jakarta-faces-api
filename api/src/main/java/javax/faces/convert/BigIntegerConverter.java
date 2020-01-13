/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package javax.faces.convert;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import java.math.BigInteger;


/**
 * <p>{@link Converter} implementation for
 * <code>java.math.BigInteger</code> values.</p>
 */

public class BigIntegerConverter implements Converter {

    // ------------------------------------------------------ Manifest Constants


    /**
     * <p>The standard converter id for this converter.</p>
     */
    public static final String CONVERTER_ID = "javax.faces.BigInteger";

    /**
     * <p>The message identifier of the {@link javax.faces.application.FacesMessage} to be created if
     * the conversion to <code>BigInteger</code> fails.  The message format
     * string for this message may optionally include the following
     * placeholders:
     * <ul>
     * <li><code>{0}</code> replaced by the unconverted value.</li>
     * <li><code>{1}</code> replaced by an example value.</li>
     * <li><code>{2}</code> replaced by a <code>String</code> whose value
     * is the label of the input component that produced this message.</li>
     * </ul>
     */
    public static final String BIGINTEGER_ID =
         "javax.faces.converter.BigIntegerConverter.BIGINTEGER";

    /**
     * <p>The message identifier of the {@link javax.faces.application.FacesMessage} to be created if
     * the conversion of the <code>BigInteger</code> value to
     * <code>String</code> fails.   The message format string for this message
     * may optionally include the following placeholders:
     * <ul>
     * <li><code>{0}</code> relaced by the unconverted value.</li>
     * <li><code>{1}</code> replaced by a <code>String</code> whose value
     * is the label of the input component that produced this message.</li>
     * </ul>
     */
    public static final String STRING_ID =
         "javax.faces.converter.STRING";

    // ------------------------------------------------------- Converter Methods

    /**
     * @throws ConverterException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
                              String value) {

        if (context == null || component == null) {
            throw new NullPointerException();
        }

        // If the specified value is null or zero-length, return null
        if (value == null) {
            return (null);
        }
        value = value.trim();
        if (value.length() < 1) {
            return (null);
        }

        try {
            return (new BigInteger(value));
        } catch (NumberFormatException nfe) {
            throw new ConverterException(
                 MessageFactory.getMessage(context,
                                           BIGINTEGER_ID,
                                           value,
                                           "9876",
                                           MessageFactory.getLabel(context,
                                                                   component)), nfe);
        } catch (Exception e) {
            throw new ConverterException(e);
        }
    }

    /**
     * @throws ConverterException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component,
                              Object value) {

        if (context == null || component == null) {
            throw new NullPointerException();
        }

        // If the specified value is null, return a zero-length String
        if (value == null) {
            return "";
        }

        // If the incoming value is still a string, play nice
        // and return the value unmodified
        if (value instanceof String) {
            return (String) value;
        }

        try {
            return (value.toString());
        } catch (Exception e) {
            throw new ConverterException(
                 MessageFactory.getMessage(context,
                                           STRING_ID,
                                           value,
                                           MessageFactory.getLabel(context,
                                                                   component)),
                                           e);
        }
    }
}
