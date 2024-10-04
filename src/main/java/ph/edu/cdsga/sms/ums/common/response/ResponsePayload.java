package ph.edu.cdsga.sms.ums.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ResponsePayload implements Serializable {
	private static final long serialVersionUID = 1L;

	private String statusCode;
	private String statusDesc;

	public ResponsePayload() {
		super();
	}

}
