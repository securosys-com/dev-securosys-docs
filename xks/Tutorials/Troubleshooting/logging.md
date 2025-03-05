---
sidebar_position: 1
title: Configure logging - XKS Proxy for AWS KMS
sidebar_label: Configure the logging
description: Learn how to configure Securosys XKS Proxy logging with the provided logback.xml file. Set up console and file logging for effective monitoring.
tags: [AWS KMS, XKS]
---

import Link from '@docusaurus/Link';

# Configuring the XKS Proxy Logging

The [Securosys XKS Proxy ```.zip``` file](/xks/downloads.md) contains ```logback.xml```, a configuration file for logging behaviour of the Securosys XKS proxy.

Use the logging configuration file and uncomment the desired lines to setup your logging environment.  Below is an example configuration which logs information to the console and writes a file on the host:

```yml
<configuration scan="true" scanPeriod="300 seconds">

    <property name="LOGS" value="log" />

    <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
        <withJansi>true</withJansi>
        <layout class="ch.qos.logback.classic.PatternLayout">
            <Pattern>
                %white(%d{ISO8601}) %highlight(%-5level) [%blue(%t)] %yellow(%C): %msg%n%throwable
            </Pattern>
        </layout>
    </appender>

    <appender name="RollingFile"
              class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOGS}/xks_proxy.log</file>
        <encoder
                class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <Pattern>%d %p %C [%t] %m%n</Pattern>
        </encoder>

        <rollingPolicy
                class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- rollover daily and when the file reaches 10 MegaBytes -->
            <fileNamePattern>${LOGS}/xks_proxy-%d{yyyy-MM-dd}.log
            </fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
    </appender>

    <!-- REMOTE LOGGING -->

    <!-- remote logging to a Syslog server -->
    <!--
    <appender name="SYSLOG" class="ch.qos.logback.classic.net.SyslogAppender">
        <syslogHost>127.0.0.1</syslogHost>
        <port>514</port>
        <facility>AUDIT</facility>
        <suffixPattern>%level [%thread] [%logger] %msg</suffixPattern>
    </appender>
    -->

    <!-- remote logging to Splunk -->
    <!--
    <appender name="SPLUNK" class="com.splunk.logging.TcpAppender">
        <RemoteHost>127.0.0.1</RemoteHost>
        <Port>4560</Port>
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%date{ISO8601} %level [%thread] [%logger] %msg%n</pattern>
        </layout>
    </appender>
    -->
    <!--
    <appender name="SplunkHEC" class="com.splunk.logging.HttpEventCollectorLogbackAppender">
        <url>https://localhost:8088</url>
        <token>58d7c29c-93c5-41b7-bc68-833e144777d5</token>
        <disableCertificateValidation>true</disableCertificateValidation>
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>%msg</pattern>
        </layout>
    </appender>
    -->

    <!-- remote logging to TCP Socket -->
    <!--
    <appender name="Socket" class="ch.qos.logback.classic.net.SocketAppender">
        <remoteHost>127.0.0.1</remoteHost>
        <port>4560</port>
        <includeCallerData>false</includeCallerData>
        <reconnectionDelay>30000</reconnectionDelay>
        <queueSize>128</queueSize>
        <eventDelayLimit>100</eventDelayLimit>
    </appender>
    -->

    <!-- LOG everything at INFO level -->
    <root level="info">
        <appender-ref ref="RollingFile" />
        <appender-ref ref="Console" />
    </root>

    <logger name="securosys" level="trace" additivity="false">
        <appender-ref ref="RollingFile" />
        <appender-ref ref="Console" />
    </logger>

</configuration>

```

## More content

- [Download the Securosys XKS Proxy for AWS](../../downloads.md) (login required)
- [Example - Creation of an XKS in AWS KMS](../Examples/Example-AWS-KMS.md)
- [Example - Generating a .jks domain file](../Examples/Example-jks.md)