package tutorial.lib.jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.io.DIMACSExporter;
import org.jgrapht.io.DIMACSFormat;
import org.jgrapht.io.ExportException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 Jan 2020, 1:42 PM
 */
public class DIMACSExporterTest
{
    private static final String V1 = "v1";
    private static final String V2 = "v2";
    private static final String V3 = "v3";
    private static final String V4 = "v4";
    private static final String V5 = "v5";

    private static final String NL = System.getProperty("line.separator");

    private static final String UNDIRECTED =
            "c" + NL +
                    "c SOURCE: Generated using the JGraphT library" + NL +
                    "c" + NL +
                    "p sp 3 2" + NL +
                    "a 1 2" + NL +
                    "a 3 1" + NL;

    @Test
    public void testUndirected()
            throws UnsupportedEncodingException, ExportException
    {
        Graph<String, DefaultEdge> g = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
        g.addVertex(V1);
        g.addVertex(V2);
        g.addEdge(V1, V2);
        g.addVertex(V3);
        g.addEdge(V3, V1);

        DIMACSExporter<String, DefaultEdge> exporter = new DIMACSExporter<>();
        exporter.setFormat(DIMACSFormat.SHORTEST_PATH);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        exporter.exportGraph(g, os);
        String res = new String(os.toByteArray(), "UTF-8");
        System.out.println(res);
        assertEquals(UNDIRECTED, res);
    }
}
