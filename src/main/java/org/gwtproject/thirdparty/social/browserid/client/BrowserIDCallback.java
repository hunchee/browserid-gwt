package org.gwtproject.thirdparty.social.browserid.client;

public interface BrowserIDCallback {
    public void onLogin();
    public void onFail();
    public void onLogout();
}
