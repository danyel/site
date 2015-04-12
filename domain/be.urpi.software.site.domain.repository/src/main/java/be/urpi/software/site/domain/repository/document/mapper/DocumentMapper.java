package be.urpi.software.site.domain.repository.document.mapper;

import be.urpi.software.site.domain.model.document.Document;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class DocumentMapper {
    public Document map(final ResultSet resultSet){return new Document();}
}
