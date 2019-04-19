package tutorial.jena.io;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.VCARD;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.io.StringWriter;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 09 Oct 2018, 9:32 PM
 */
public class XMLIO
{
    // some definitions
    static String tutorialURI = "http://hostname/rdf/tutorial/";
    static String briansName = "Brian McBride";
    static String briansEmail1 = "brian_mcbride@hp.com";
    static String briansEmail2 = "brian_mcbride@hpl.hp.com";
    static String title = "An Introduction to RDF and the Jena API";
    static String date = "23/01/2001";

    @Test
    public void testWriter()
    {
        // some definitions
        String personURI = "http://somewhere/JohnSmith";
        String givenName = "John";
        String familyName = "Smith";
        String fullName = givenName + " " + familyName;
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        //   and add the properties cascading style
        Resource johnSmith = model.createResource(personURI)
                .addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.N, model.createResource()
                        .addProperty(VCARD.Given, givenName)
                        .addProperty(VCARD.Family, familyName));

        // now write the model in XML form to a file
        StringWriter writer = new StringWriter();
        model.write(writer, "RDF/XML-ABBREV");
        System.out.println(writer.toString());
        Assert.assertEquals(writer.toString(), "<rdf:RDF\r\n" +
                "    xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\r\n" +
                "    xmlns:vcard=\"http://www.w3.org/2001/vcard-rdf/3.0#\">\r\n" +
                "  <rdf:Description rdf:about=\"http://somewhere/JohnSmith\">\r\n" +
                "    <vcard:N rdf:parseType=\"Resource\">\r\n" +
                "      <vcard:Family>Smith</vcard:Family>\r\n" +
                "      <vcard:Given>John</vcard:Given>\r\n" +
                "    </vcard:N>\r\n" +
                "    <vcard:FN>John Smith</vcard:FN>\r\n" +
                "  </rdf:Description>\r\n" +
                "</rdf:RDF>\r\n");
    }

    @Test
    public void testReader()
    {
        Model model = ModelFactory.createDefaultModel();
        InputStream inputStream = FileManager.get().open("vc-db-1.rdf");
        model.read(inputStream, null);
        model.write(System.out);
    }
}
