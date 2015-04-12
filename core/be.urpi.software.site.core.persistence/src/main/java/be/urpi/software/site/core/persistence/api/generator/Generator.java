package be.urpi.software.site.core.persistence.api.generator;

import java.io.Serializable;

public interface Generator<S extends Serializable> {
    S generate();
}
