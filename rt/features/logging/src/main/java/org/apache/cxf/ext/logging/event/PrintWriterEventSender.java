/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.cxf.ext.logging.event;

import java.io.PrintWriter;
import java.util.Date;

/**
 * 
 */
public class PrintWriterEventSender extends AbstractPrintLogEventSender {
    PrintWriter writer;
    
    public PrintWriterEventSender(PrintWriter writer) {
        this.writer = writer;
    }
    
    void setPrintWriter(PrintWriter w) {
        writer = w;
    }
    
    
    /** {@inheritDoc}*/
    @Override
    public void send(LogEvent event) {
        StringBuilder b = new StringBuilder();
        b.append(new Date().toString()).append(" - PrintWriterEventSender");
        prepareBuilder(b, event);
        synchronized (writer) {
            writer.print(b.toString());
        }
    }
    
}
