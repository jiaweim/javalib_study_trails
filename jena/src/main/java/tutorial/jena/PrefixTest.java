package tutorial.jena;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.testng.annotations.Test;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @since 10 Oct 2018, 8:49 AM
 */
public class PrefixTest
{
    // 修饰 QName 前缀的样式，否则自动生成的，很丑
    @Test
    public void prefixDefine()
    {
        Model model = ModelFactory.createDefaultModel();
        String nsA = "http://somewhere/else#";
        String nsB = "http://nowhere/else#";
        Resource root = model.createResource(nsA + "root");
        Property P = model.createProperty(nsA + "P");
        Property Q = model.createProperty(nsB + "Q");
        Resource x = model.createResource(nsA + "x");
        Resource y = model.createResource(nsA + "y");
        Resource z = model.createResource(nsA + "z");
        model.add(root, P, x).add(root, P, y).add(y, Q, z);

        System.out.println( "# -- no special prefixes defined" );
        model.write( System.out );

        System.out.println( "# -- nsA defined" );
        model.setNsPrefix( "nsA", nsA );
        model.write( System.out );

        System.out.println( "# -- nsA and cat defined" );
        model.setNsPrefix( "cat", nsB );
        model.write( System.out );
    }
}
