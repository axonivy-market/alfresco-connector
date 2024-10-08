package alfresco.ecm.connector.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.ivyteam.ivy.bpm.engine.client.BpmClient;
import ch.ivyteam.ivy.bpm.engine.client.element.BpmProcess;
import ch.ivyteam.ivy.bpm.engine.client.sub.SubProcessCallResult;
import ch.ivyteam.ivy.bpm.exec.client.IvyProcessTest;
import ch.ivyteam.ivy.environment.AppFixture;
import ch.ivyteam.ivy.scripting.objects.List;
import ch.ivyteam.ivy.security.ISession;

/**
 * Rest Service functionality is mocked out here: {@link AlfrescoMock}
 */
@IvyProcessTest(enableWebServer = true)
public class TestAlfrescoECM {

  private static final BpmProcess CALL_READ_DOCUMENTS = BpmProcess.name("readDocuments");

  @BeforeEach
  void setup(AppFixture fixture) {
    fixture.config("RestClients.Alfresco.Url", AlfrescoMock.URI);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void callReadDocuments(BpmClient bpmClient, ISession session) {
    SubProcessCallResult result = bpmClient.start()
      .subProcess(CALL_READ_DOCUMENTS).as().session(session)
      .execute("-shared-").subResult();
    assertThat(result.param("connectionError", String.class)).isEmpty();
    assertThat(result.param("documents", List.class)).isNotEmpty();
  }
}