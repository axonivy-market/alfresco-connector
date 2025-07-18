package alfresco.ecm.connector.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.alfresco.api.explorer.NodeEntry;

import alfresco.ecm.connector.test.util.AlfrescoConnectorTestUtils;
import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.engine.client.sub.SubProcessCallResult;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.scripting.objects.List;
import ch.ivyteam.ivy.security.ISession;

@Testcontainers
@IvyProcessTest(enableWebServer = true)
public class TestAlfrescoECM {
  private static final String FINISHED_SETUP_LOG_TEXT_REGEX = ".*readyProbe: Success - Tested*";
  private static final BpmProcess CALL_READ_DOCUMENTS = BpmProcess.path("Alfresco/Documents");

  @BeforeEach
  void setup(AppFixture fixture) {
    fixture.config("RestClients.Alfresco.Url",
        "http://localhost:8080/alfresco/api/-default-/public/alfresco/versions/1");
    fixture.var("Alfresco.Username", "admin");
    fixture.var("Alfresco.Password", "admin");
  }

  /**
   * This Docker Compose file was cloned from Alfresco's official GitHub repository:
   * https://github.com/Alfresco/acs-deployment/blob/master/docker-compose/community-compose.yaml
   *
   * Please ensure that you regularly update this file to align with the latest version
   * from the upstream repository.
   */
  @SuppressWarnings("resource")
  @Container
  public final ComposeContainer db2 = new ComposeContainer(
      new java.io.File("../alfresco-connector-demo/docker/compose.yaml")).withExposedService("alfresco", 8080)
      .waitingFor("alfresco", Wait.forLogMessage(FINISHED_SETUP_LOG_TEXT_REGEX, 1).withStartupTimeout(Duration.ofMinutes(10)));

  @Test
  @SuppressWarnings("unchecked")
  public void callReadDocuments(BpmClient bpmClient, ISession session) throws IOException {
    SubProcessCallResult result = bpmClient.start()
        .subProcess(CALL_READ_DOCUMENTS.elementName("getDocumentsFromFolder(String)")).as().session(session)
        .execute("-shared-").subResult();
    assertThat(result.param("documents", List.class)).isEmpty();
    bpmClient.start().subProcess(CALL_READ_DOCUMENTS.elementName("postDocumentToFolder(String,File)")).as()
        .session(session).execute("-shared-", AlfrescoConnectorTestUtils.exportFromCMS("/Files/test", "yaml"));
    result = bpmClient.start().subProcess(CALL_READ_DOCUMENTS.elementName("getDocumentsFromFolder(String)")).as()
        .session(session).execute("-shared-").subResult();
    assertThat(result.param("documents", List.class)).isNotEmpty();
    result = bpmClient.start().subProcess(CALL_READ_DOCUMENTS.elementName("addNewNodeFolder(String)")).as()
        .session(session).execute("test").subResult();
    assertThat(result.param("nodeEntry", NodeEntry.class)).isNotNull();
  }
}