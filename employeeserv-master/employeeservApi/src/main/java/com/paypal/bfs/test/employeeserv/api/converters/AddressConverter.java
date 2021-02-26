package com.paypal.bfs.test.employeeserv.api.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

/**
 * @author gursimran.kaur
 */
@Slf4j
public class AddressConverter implements AttributeConverter<Address, String> {

    ObjectMapper objectMapper = new ObjectMapper();
        @Override
        public String convertToDatabaseColumn(Address customerInfo) {

            String customerInfoJson = null;
            try {
                customerInfoJson = objectMapper.writeValueAsString(customerInfo);
            } catch (final IOException e) {
//                logger.error("JSON writing error", e);
            }

            return customerInfoJson;
        }

        @Override
        public Address convertToEntityAttribute(String customerInfoJSON) {

            Address customerInfo = null;
            try {
                customerInfo = objectMapper.readValue(customerInfoJSON, Address.class);
            } catch (final IOException e) {
//                logger.error("JSON reading error", e);
            }

            return customerInfo;
        }

    
}
