package com.jpkvalidation.service;


import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Component
public class ValidationService {

    public String validateAgainstXSD(InputStream xml) throws Exception {
        InputStream xsd = null;
        try {
            xsd = new FileInputStream(new File(ResourceUtils.getURL("jpk-vat.xsd").getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return "Twój Plik JPK jest poprawny";
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
