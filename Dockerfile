FROM quay.io/wildfly/wildfly:28.0.0.Final-jdk17
COPY target/lab2-lab-2.war /opt/jboss/wildfly/standalone/deployments/
CMD /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0
