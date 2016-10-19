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
package sample.rs.service;
import org.apache.cxf.Bus;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.codahale.CodahaleMetricsProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;

@SpringBootApplication
@EnableEurekaClient
public class SampleRestApplication {
    @Bean
    public MetricRegistry metricRegistry(){
        return new MetricRegistry();
    }
    
    @Bean(initMethod = "start", destroyMethod = "stop")
    public JmxReporter jmxReporter(MetricRegistry metricRegistry) {
        return JmxReporter.forRegistry(metricRegistry).build();
    }
    
    @Bean
    public Feature metricsFeature(Bus bus){
        return new MetricsFeature(new CodahaleMetricsProvider(bus));
    }
    
    public static void main(String[] args) {
        SpringApplication.run(SampleRestApplication.class, args);
    }
}
