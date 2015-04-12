package be.urpi.software.site.domain.model.api.generator;

import be.urpi.software.site.core.persistence.api.generator.Generator;

import java.util.UUID;

public class IdGenerator implements Generator<String> {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
