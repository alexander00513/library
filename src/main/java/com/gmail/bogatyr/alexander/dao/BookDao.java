package com.gmail.bogatyr.alexander.dao;

import com.gmail.bogatyr.alexander.entity.Catalog;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBException;
import java.io.IOException;

import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * Created by alexander on 11.02.16.
 */
@Repository
public class BookDao extends BaseDao<Catalog> {

    public BookDao() throws JAXBException {
        super(Catalog.class);
    }

    public Catalog findAll() {
        try {
            Catalog catalog = (Catalog) jaxbUnmarshaller.unmarshal(mainXml.getFile());
            logger.info(String.format("has been found %d elements", nonNull(catalog) && !catalog.isEmpty() ?
                    catalog.getBooks().size() : 0));
            return catalog;
        } catch (IOException | JAXBException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public void save(Catalog catalog) {
        try {
            int size = 0;
            if (nonNull(catalog) && !isEmpty(catalog.getBooks())) {
                jaxbMarshaller.marshal(catalog, mainXml.getFile());
                size = catalog.getBooks().size();
            }
            logger.info(String.format("has been saved %S elements", size));
        } catch (IOException | JAXBException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
