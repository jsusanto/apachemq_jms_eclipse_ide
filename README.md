# apachemq_jms_eclipseproject
Tutorial ApacheMQ + JMS using Eclipse IDE

How to run this project?
Step 1. Add new java console project

Step 2. Import using existing Maven project

Step 3. Run Application.java

Pre-requisites
==============
Install ApacheMQ
When you have installed this, go to your browser and open http://localhost:8161/admin
default username/password admin/admin

Run Application.java
Open http://localhost:9000/ to populate data from servlet and it will hit to the backend (send and receive service).

com.linkedinjms.chapter3
========================
It's a Send / Receive service using ApacheMQ and a continuation from chapter 2.

The format is using JSON.
We're about to use XML as well however, the latest library XStreamMarshaller is not compatible.
As result, we couldn't test using XML serialization.