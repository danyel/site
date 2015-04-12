package be.urpi.software.site.core.persistence;

import be.urpi.software.site.core.persistence.stereotype.IdSequenceGenerator;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
@IdSequenceGenerator(generatorType = UUIDGenerator.class)
public class Testing extends DomainObject<UUID> {
}
