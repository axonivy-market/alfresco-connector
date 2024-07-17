# Alfreso Plattform ECM Connector

Axon Ivy’s [Alfresco Plattform](https://www.alfresco.com/de/ecm-software) connector helps you to accelerate process automation initiatives by accessing content wherever and however you work. Alfresco Content Services includes full-featured enterprise content management system (ECM) capabilities. This connector:

- Is based on REST webservice technologies.
- Provides access to the core features of Alfresco Content Services. e.g. create folders and to store documents.
- Supports you with a demo implementation to reduce your integration effort.

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
