package tutorial.lib.guava.graph;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 05 May 2018, 4:02 PM
 */
public class ValueGraphTest
{
    private static final String DEFAULT = "default";
    MutableValueGraph<Integer, String> graph;

    @Test
    public void directedGraph()
    {
        MutableValueGraph<Integer, String> graph = ValueGraphBuilder.directed().allowsSelfLoops(true).build();
        graph.putEdgeValue(1, 2, "valueA");
        graph.putEdgeValue(2, 1, "valueB");
        graph.putEdgeValue(2, 3, "valueC");
        graph.putEdgeValue(4, 4, "valueD");

        assertEquals(graph.edgeValueOrDefault(1, 2, DEFAULT), "valueA");
        assertEquals(graph.edgeValueOrDefault(2, 1, DEFAULT), "valueB");
        assertEquals(graph.edgeValueOrDefault(2, 3, DEFAULT), "valueC");
        assertEquals(graph.edgeValueOrDefault(4, 4, DEFAULT), "valueD");
    }

    @Test
    public void undirectedGraph()
    {
        MutableValueGraph<Object, Object> graph = ValueGraphBuilder.undirected().allowsSelfLoops(true).build();
        graph.putEdgeValue(1, 2, "valueA");
        graph.putEdgeValue(2, 1, "valueB"); // overwrites valueA in undirected case
        graph.putEdgeValue(2, 3, "valueC");
        graph.putEdgeValue(4, 4, "valueD");

        assertEquals(graph.edgeValueOrDefault(1, 2, DEFAULT), "valueB");
        assertEquals(graph.edgeValueOrDefault(2, 1, DEFAULT), "valueB");
        assertEquals(graph.edgeValueOrDefault(2, 3, DEFAULT), "valueC");
        assertEquals(graph.edgeValueOrDefault(4, 4, DEFAULT), "valueD");
    }

    @Test
    public void putEdgeValue_directed()
    {
        MutableValueGraph<Integer, String> graph = ValueGraphBuilder.directed().build();

        assertNull(graph.putEdgeValue(1, 2, "valueA"));
        assertNull(graph.putEdgeValue(2, 1, "valueB"));
        assertEquals(graph.putEdgeValue(1, 2, "valueC"), "valueA");
        assertEquals(graph.putEdgeValue(2, 1, "valueD"), "valueB");
    }


    @Test
    public void putEdgeValue_undirected()
    {
        graph = ValueGraphBuilder.undirected().build();

        assertNull(graph.putEdgeValue(1, 2, "valueA"));
        assertEquals(graph.putEdgeValue(2, 1, "valueB"), "valueA");
        assertEquals(graph.putEdgeValue(1, 2, "valueC"), "valueB");
        assertEquals(graph.putEdgeValue(2, 1, "valueD"), "valueC");
    }

    @Test
    public void removeEdge_directed()
    {
        graph = ValueGraphBuilder.directed().build();
        graph.putEdgeValue(1, 2, "valueA");
        graph.putEdgeValue(2, 1, "valueB");
        graph.putEdgeValue(2, 3, "valueC");

        assertEquals(graph.removeEdge(1, 2), "valueA");
        assertNull(graph.removeEdge(1, 2));
        assertEquals(graph.removeEdge(2, 1), "valueB");
        assertNull(graph.removeEdge(2, 1));
        assertEquals(graph.removeEdge(2, 3), "valueC");
        assertNull(graph.removeEdge(2, 3));
    }

    @Test
    public void removeEdge_undirected()
    {
        graph = ValueGraphBuilder.undirected().build();
        graph.putEdgeValue(1, 2, "valueA");
        graph.putEdgeValue(2, 1, "valueB");
        graph.putEdgeValue(2, 3, "valueC");

        assertEquals(graph.removeEdge(1, 2), "valueB");
        assertNull(graph.removeEdge(1, 2));
        assertNull(graph.removeEdge(2, 1));
        assertEquals(graph.removeEdge(2, 3), "valueC");
        assertNull(graph.removeEdge(2, 3));
    }

    @Test
    public void edgeValue_missing()
    {
        graph = ValueGraphBuilder.directed().build();

        assertEquals(graph.edgeValueOrDefault(1, 2, DEFAULT), DEFAULT);
        assertEquals(graph.edgeValueOrDefault(2, 1, DEFAULT), DEFAULT);
        assertEquals(graph.edgeValue(1, 2).orElse(DEFAULT), DEFAULT);
        assertEquals(graph.edgeValue(2, 1).orElse(DEFAULT), DEFAULT);

        assertNull(graph.edgeValueOrDefault(1, 2, null));
        assertNull(graph.edgeValueOrDefault(2, 1, null));
        assertNull(graph.edgeValue(1, 2).orElse(null));
        assertNull(graph.edgeValue(2, 1).orElse(null));

        graph.putEdgeValue(1, 2, "valueA");
        graph.putEdgeValue(2, 1, "valueB");
        assertEquals(graph.edgeValueOrDefault(1, 2, DEFAULT), "valueA");
        assertEquals(graph.edgeValueOrDefault(2, 1, DEFAULT), "valueB");
        assertEquals(graph.edgeValueOrDefault(1, 2, null), "valueA");
        assertEquals(graph.edgeValueOrDefault(2, 1, null), "valueB");
        assertEquals(graph.edgeValue(1, 2).get(), "valueA");
        assertEquals(graph.edgeValue(2, 1).get(), "valueB");

        graph.removeEdge(1, 2);
        graph.putEdgeValue(2, 1, "valueC");
        assertEquals(graph.edgeValueOrDefault(1, 2, DEFAULT), DEFAULT);
        assertEquals(graph.edgeValueOrDefault(2, 1, DEFAULT), "valueC");
        assertEquals(graph.edgeValue(1, 2).orElse(DEFAULT), DEFAULT);
        assertNull(graph.edgeValueOrDefault(1, 2, null));
        assertEquals(graph.edgeValueOrDefault(2, 1, null), "valueC");
        assertNull(graph.edgeValue(1, 2).orElse(null));
        assertEquals(graph.edgeValue(2, 1).get(), "valueC");
    }

    @Test
    public void equivalence_considersEdgeValue()
    {
        graph = ValueGraphBuilder.undirected().build();
        graph.putEdgeValue(1, 2, "valueA");

        MutableValueGraph<Integer, String> otherGraph = ValueGraphBuilder.undirected().build();
        otherGraph.putEdgeValue(1, 2, "valueA");

        assertEquals(graph, otherGraph);

        otherGraph.putEdgeValue(1, 2, "valueB");
        assertNotEquals(graph, otherGraph); // values differ
    }
}
