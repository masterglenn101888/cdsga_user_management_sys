package ph.edu.cdsga.sms.ums.common.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Format {

	private static final Logger LOGGER = LoggerFactory.getLogger(Format.class);

	@Override
	public String toString() {
		try {
			return new ObjectMapper().enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS).writeValueAsString(this);
		} catch (JsonProcessingException e) {
			LOGGER.info(String.valueOf(e));
			return "";
		}
	}

}
