package tutorial.jena.sparql;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.testng.annotations.Test;

import java.io.InputStream;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 10 Oct 2018, 10:48 AM
 */
public class SELECTTest
{
    @org.testng.annotations.Test
    public void testSelect()
    {
        Model model = null;
        String queryString = "...";
        Query query = QueryFactory.create(queryString);
        // QueryExecution objects are java.lang.AutoCloseable
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.next();
                RDFNode x = soln.get("varName"); // Get a result variable by name
                Resource r = soln.getResource("VarR"); // Get a result variable - must be a resource
                Literal l = soln.getLiteral("VarL"); // Get a result varaible - must be a literal
            }
        }
    }

    @Test
    public void testQuery()
    {
        InputStream stream = FileManager.get().open("vc-db-1.rdf");
        Model model = ModelFactory.createDefaultModel();
        model.read(stream, null);

        String queryStr = "SELECT ?x\n" +
                "WHERE\n" +
                " { ?x <http://www.w3.org/2001/vcard-rdf/3.0#FN> \"John Smith\" }\n";
        try (QueryExecution exec = QueryExecutionFactory.create(queryStr, model)) {
            ResultSet resultSet = exec.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution solution = resultSet.next();
                System.out.println(solution);
            }
        }

        queryStr = "SELECT ?x ?name\n" +
                "WHERE\n" +
                " { ?x <http://www.w3.org/2001/vcard-rdf/3.0#FN> ?name }\n";
        try (QueryExecution exec = QueryExecutionFactory.create(queryStr, model)) {
            ResultSet resultSet = exec.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution solution = resultSet.next();
                System.out.println(solution);
            }
        }

        queryStr = "SELECT ?givenName\n" +
                "WHERE\n" +
                " { ?y <http://www.w3.org/2001/vcard-rdf/3.0#Family> \"Smith\" .\n" +
                "   ?y <http://www.w3.org/2001/vcard-rdf/3.0#Given>  ?givenName .\n" +
                " }\n";
        try (QueryExecution exec = QueryExecutionFactory.create(queryStr, model)) {
            ResultSet resultSet = exec.execSelect();
            while (resultSet.hasNext()) {
                QuerySolution solution = resultSet.next();
                System.out.println(solution);
            }
        }
    }
}
