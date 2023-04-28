package alfresco.ecm.connector.test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * Mocks parts of the Alfresco ECM Rest API which has been used in the AlfrescoEcmDemo
 * <p>docs<br/>https://api-explorer.alfresco.com/api-explorer/definitions/alfresco-core.json</p>
 */
@Path(AlfrescoMock.PATH_SUFFIX)
@PermitAll
@Hidden
public class AlfrescoMock
{
  static final String PATH_SUFFIX = "ecmMock";

  // URI where this mock can be reached: to be referenced in tests that use it!
  @SuppressWarnings("restriction")
  public static final String URI = "{"+ch.ivyteam.ivy.rest.client.config.IvyDefaultJaxRsTemplates.APP_URL+"}/api/"+PATH_SUFFIX;


  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("nodes/-shared-/children")
  public String getRootFolderNodes()
  {
    return load("json/rootfolder.json");
  }

  private static String load(String path)
  {
    try(InputStream is = AlfrescoMock.class.getResourceAsStream(path))
    {
      return IOUtils.toString(is, StandardCharsets.UTF_8);
    }
    catch (IOException ex)
    {
      throw new RuntimeException("Failed to read resource: "+path);
    }
  }
}