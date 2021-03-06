package be.urpi.software.site.core.persistence;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class DomainObject<S extends Serializable> {
    @Id
    private S id;

    public S getId() {
        return id;
    }
}
