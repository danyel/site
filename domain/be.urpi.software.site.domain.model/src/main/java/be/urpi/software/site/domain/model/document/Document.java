package be.urpi.software.site.domain.model.document;

import be.urpi.software.site.core.persistence.stereotype.IdSequenceGenerator;
import be.urpi.software.site.domain.model.api.generator.IdGenerator;
import be.urpi.software.site.domain.model.core.DomainObject;

import javax.persistence.Entity;

@Entity
@IdSequenceGenerator(generatorType = IdGenerator.class)
public class Document extends DomainObject<String> {
}
