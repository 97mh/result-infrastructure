package org.infrastructure.result.exception;

import org.infrastructure.result.IBusinessCode;

public class FeignRemoteException extends BusinessCodeException {

    private String serverApplicationName;

    private String remoteAddress;

    public FeignRemoteException(IBusinessCode businessCode, String serverApplicationName, String remoteAddress) {
        super(businessCode);
        this.serverApplicationName = serverApplicationName;
        this.remoteAddress = remoteAddress;
    }

    public FeignRemoteException(Integer code, String reason, String serverApplicationName, String remoteAddress) {
        super(code, reason);
        this.serverApplicationName = serverApplicationName;
        this.remoteAddress = remoteAddress;
    }

    public String getServerApplicationName() {
        return serverApplicationName;
    }

    public void setServerApplicationName(String serverApplicationName) {
        this.serverApplicationName = serverApplicationName;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

}
