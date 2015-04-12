package be.urpi.software.site.core.persistence;

import be.urpi.software.site.core.persistence.api.generator.Generator;

import java.util.UUID;

public class UUIDGenerator implements Generator<UUID> {
    @Override
    public UUID generate() {
        return UUID.randomUUID();
    }
}
