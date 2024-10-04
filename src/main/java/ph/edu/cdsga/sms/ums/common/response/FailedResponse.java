package ph.edu.cdsga.sms.ums.common.response;

import ph.edu.cdsga.sms.ums.enums.ResponseStatus;

import java.util.Date;

public class FailedResponse extends JSendResponse {

    /**
     * @param data data
     * @param appName appName
     * @param appVersion appVersion
     */
    public FailedResponse(Object data, int code, String message, String appName, String appVersion) {
        init();
        this.setCode(code);
        this.setProgramName(appName);
        this.setVersion(appVersion);
        this.setData(data);
        this.setMessage(message);
    }

    private void init() {
        this.setStatus(ResponseStatus.ERROR.getStatus());
        this.setDatetime(new Date());
    }

}
