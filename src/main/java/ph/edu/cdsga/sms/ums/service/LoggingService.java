package ph.edu.cdsga.sms.ums.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoggingService {

	private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

	public String log(String uuid, String component, String message, String info) {
		Map<String, String> map = new HashMap<>();
		if (component.length() != 0 && !ObjectUtils.isEmpty(component)) {
			map.put("COMPONENT", component);
		}
		if (uuid.length() != 0 && !ObjectUtils.isEmpty(uuid)) {
			map.put("UUID", uuid);
		}
		if (message.length() != 0 && !ObjectUtils.isEmpty(message)) {
			map.put("MESSAGE", message);
		}
		if (info.length() != 0 && !ObjectUtils.isEmpty(info)) {
			map.put("INFO", info);
		}
		String stringMap = map.toString();
		logger.info(stringMap);
		return stringMap;
	}
}
