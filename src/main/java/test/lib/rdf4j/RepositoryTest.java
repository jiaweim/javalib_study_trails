package test.lib.rdf4j;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

/**
 * @author JiaweiMao on 2017.05.12
 * @since 1.0-SNAPSHOT
 */
public class RepositoryTest {

    public static void main(String[] args) {

        Repository repo = new SailRepository(new MemoryStore());
        repo.initialize();



    }

}
