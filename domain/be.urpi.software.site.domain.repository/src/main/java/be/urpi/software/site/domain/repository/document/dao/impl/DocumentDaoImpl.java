package be.urpi.software.site.domain.repository.document.dao.impl;

import be.urpi.software.site.domain.model.document.Document;
import be.urpi.software.site.domain.repository.document.dao.api.DocumentDao;
import be.urpi.software.site.domain.repository.document.mapper.DocumentMapper;
import be.urpi.software.site.domain.repository.stereotype.DAO;

import javax.annotation.Resource;

@DAO
public class DocumentDaoImpl implements DocumentDao {
    @Resource
    private DocumentMapper documentMapper;
    @Override
    public void create(final Document document) {

    }
}
