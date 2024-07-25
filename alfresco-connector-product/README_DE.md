# Alfreso Plattform ECM Konnektor

Der [Alfresco Plattform ECM](https://www.alfresco.com/de/ecm-software) Konnektor von Axon Ivy beschleunigt deine Prozessautomatisierung, indem er dir Zugang zu
 Unternehmensinhalten ermöglicht, ganz gleich, wo und wie du arbeitest. Die Alfresco Plattform stellt umfassende Funktionen 
 für das Enterprise Content Management (ECM) zur Verfügung.
 
 Dieser Konnektor:
 
- Basiert auf einem REST-Webservice.
- Stellt die Funktionen der Alfresco Plattform für Prozesse in AxonIvy zur Verfügung, z.B. das Erstellen von Ordnern oder das Speichern von Dokumenten in Alfresco.
- Unterstützt dich mit einer Open Source Implementierung

## Demo

Shows how to create folders and store documents.

![demo-dialog](images/alfrescoConnectorDemo.png)
![demo-connector](images/alfrescoDemoProcess.png)

If you have used the docker installation as described in chapter [Setup](https://market.axonivy.com/alfrescoecm#tab-setup) you you can test your file upload with http://localhost:8080/content-app/#/personal-files - the file should be placed on folder "shared":

![demo-alfrescoview](images/alfrescoView.png)

## Setup

The connector is preconfigured for the Community Edition for Docker running on localhost port 8080, see: https://docs.alfresco.com/content-services/community/install/containers/docker-compose/.
You may adjust the Alfresco Rest client to access your Alfresco installation.

![rest-client-config](images/alfrescoRESTClient_Config.png)

