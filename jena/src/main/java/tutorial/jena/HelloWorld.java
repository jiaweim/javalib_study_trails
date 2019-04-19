package tutorial.jena;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;
import org.testng.annotations.Test;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 09 Oct 2018, 8:12 PM
 */
public class HelloWorld
{
    // some definitions
    static String personURI = "http://somewhere/JohnSmith";
    String givenName = "John";
    String familyName = "Smith";
    String fullName = givenName + " " + familyName;

    @Test
    public void createModel()
    {
        // create an empty Model
        Model model = ModelFactory.createDefaultModel(); // default model, memory-based model

        // create the resource
        Resource johnSmith = model.createResource(personURI);

        // add the property
        johnSmith.addProperty(VCARD.FN, fullName);
        johnSmith.addProperty(VCARD.N, model.createResource().addProperty(VCARD.Given, givenName)
                .addProperty(VCARD.Family, familyName));

        StmtIterator it = johnSmith.listProperties();
        while (it.hasNext()) {
            Statement statement = it.next();
            Resource subject = statement.getSubject();
            Property predicate = statement.getPredicate();
            RDFNode object = statement.getObject();

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }

            System.out.println(" .");
        }
    }


}
