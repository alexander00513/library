package com.gmail.bogatyr.alexander.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

/**
 * Created by alexander on 11.02.16.
 */
public class BaseDao<T> {

    protected final Logger logger;

    @Value("classpath:main.xml")
    protected Resource mainXml;

    protected final Unmarshaller jaxbUnmarshaller;
    protected final Marshaller jaxbMarshaller;

    public BaseDao(Class<T> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        this.jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        this.jaxbMarshaller = jaxbContext.createMarshaller();
        this.jaxbMarshaller.setProperty(JAXB_FORMATTED_OUTPUT, true);
        this.logger = LoggerFactory.getLogger(clazz);
    }

    protected void setMainXml(Resource mainXml) {
        this.mainXml = mainXml;
    }
}
