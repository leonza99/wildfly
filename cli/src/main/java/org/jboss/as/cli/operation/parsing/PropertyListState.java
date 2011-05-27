/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.cli.operation.parsing;

import org.jboss.as.cli.CommandFormatException;


/**
 *
 * @author Alexey Loubyansky
 */
public class PropertyListState extends DefaultParsingState {

    public static final PropertyListState INSTANCE = new PropertyListState();
    public static final String ID = "PROP_LIST";

    PropertyListState() {
        this(PropertyState.INSTANCE);
    }

    PropertyListState(PropertyState paramState) {
        super(ID);
        putHandler(')', GlobalCharacterHandlers.LEAVE_STATE_HANDLER);
        setDefaultHandler(new EnterStateCharacterHandler(paramState));
        setReturnHandler(new CharacterHandler(){
            @Override
            public void handle(ParsingContext ctx)
                    throws CommandFormatException {
                if(ctx.getCharacter() == ')') {
                    GlobalCharacterHandlers.LEAVE_STATE_HANDLER.handle(ctx);
                }
            }});
        //this.setEndContentHandler(new ErrorCharacterHandler("')' is missing"));
        setIgnoreWhitespaces(true);
    }
}
