package ph.edu.cdsga.sms.ums.common.response;

import ph.edu.cdsga.sms.ums.enums.ResponseStatus;

import javax.ws.rs.core.Response;
import java.util.Date;

public class SuccessResponse extends JSendResponse {

    /**
     *
     * @param data data
     * @param appName appName
     * @param appVersion appVersion
     */
    public SuccessResponse(Object data, String message, String appName, String appVersion) {
        init();
        this.setProgramName(appName);
        this.setVersion(appVersion);
        this.setMessage(message);
        this.setData(data);
    }

    private void init() {
        this.setStatus(ResponseStatus.SUCCESS.getStatus());
        this.setDatetime(new Date());
        this.setCode(Response.Status.OK.getStatusCode());
    }

}
