package tutorial.lib.jgrapht;

import org.jgrapht.Graph;
import org.jgrapht.graph.*;
import org.jgrapht.io.CSVExporter;
import org.jgrapht.io.CSVFormat;
import org.jgrapht.io.ComponentNameProvider;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 Jan 2020, 12:21 PM
 */
public class CSVExporterTest
{
    private static final String NL = System.getProperty("line.separator");
    private static final ComponentNameProvider<Integer> nameProvider = Object::toString;
    private static final String UNDIRECTED_EDGE_LIST =
            "1;2" + NL
                    + "1;3" + NL
                    + "3;4" + NL
                    + "4;5" + NL
                    + "5;1" + NL;
    private static final String DIRECTED_EDGE_LIST =
            "1;2" + NL
                    + "1;3" + NL
                    + "3;1" + NL
                    + "3;4" + NL
                    + "4;5" + NL
                    + "5;1" + NL;
    private static final String DIRECTED_WEIGHTED_EDGE_LIST =
            "1;2;2.0" + NL
                    + "1;3;2.0" + NL
                    + "3;1;2.0" + NL
                    + "3;4;2.0" + NL
                    + "4;5;2.0" + NL
                    + "5;1;2.0" + NL;
    private static final String DIRECTED_ADJACENCY_LIST =
            "1;2;3" + NL
                    + "2" + NL
                    + "3;1;4" + NL
                    + "4;5" + NL
                    + "5;1;2;3;4;5;5" + NL;

    @Test
    public void testUndirectedEdgeList()
    {
        Graph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 1);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 1);

        CSVExporter<Integer, DefaultEdge> exporter =
                new CSVExporter<>(nameProvider, CSVFormat.EDGE_LIST, ';');
        StringWriter w = new StringWriter();
        exporter.exportGraph(g, w);
        assertEquals(UNDIRECTED_EDGE_LIST, w.toString());
    }


    @Test
    public void testDirectedEdgeList()
    {
        Graph<Integer, DefaultEdge> g = new SimpleDirectedGraph<>(DefaultEdge.class);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 1);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 1);

        CSVExporter<Integer, DefaultEdge> exporter =
                new CSVExporter<>(nameProvider, CSVFormat.EDGE_LIST, ';');
        StringWriter w = new StringWriter();
        exporter.exportGraph(g, w);
        assertEquals(DIRECTED_EDGE_LIST, w.toString());
    }


    @Test
    public void testDirectedWeightedEdgeList()
    {
        Graph<Integer, DefaultEdge> g = new SimpleDirectedGraph<>(DefaultEdge.class);
        g = new AsWeightedGraph<>(g, e -> 2.0, false, false);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 1);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 1);

        CSVExporter<Integer, DefaultEdge> exporter =
                new CSVExporter<>(nameProvider, CSVFormat.EDGE_LIST, ';');
        exporter.setParameter(CSVFormat.Parameter.EDGE_WEIGHTS, true);
        StringWriter w = new StringWriter();
        exporter.exportGraph(g, w);
        assertEquals(DIRECTED_WEIGHTED_EDGE_LIST, w.toString());
    }


    @Test
    public void testDirectedAdjacencyList()
    {
        Graph<Integer, DefaultEdge> g = new DirectedPseudograph<>(DefaultEdge.class);
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(3);
        g.addVertex(4);
        g.addVertex(5);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 1);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 1);
        g.addEdge(5, 2);
        g.addEdge(5, 3);
        g.addEdge(5, 4);
        g.addEdge(5, 5);
        g.addEdge(5, 5);

        CSVExporter<Integer, DefaultEdge> exporter =
                new CSVExporter<>(nameProvider, CSVFormat.ADJACENCY_LIST, ';');
        StringWriter w = new StringWriter();
        exporter.exportGraph(g, w);
        assertEquals(DIRECTED_ADJACENCY_LIST, w.toString());
    }

}
