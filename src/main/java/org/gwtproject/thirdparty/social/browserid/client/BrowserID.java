package org.gwtproject.thirdparty.social.browserid.client;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.gwtproject.thirdparty.social.browserid.client.resources.JsResources;

/**
 * @author <a href="mailto:kerbymart@gmail.com">Kerby Martino</a>
 * @version 0.0.1
 * @since 0.0.1
 */
public class BrowserID {

    private static final String INJECT_JS_PATH = "https://login.persona.org/include.js";
    private static BrowserIDCallback callback;

    /**
     * TODO: Simple inject helper
     * @param callback
     */
    public static void inject(final AsyncCallback<Void> callback){
        ScriptInjector.fromUrl(INJECT_JS_PATH).setCallback(new Callback<Void, Exception>() {
            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }

            @Override
            public void onSuccess(Void aVoid) {
                ScriptInjector.fromString(JsResources.INSTANCE.jqueryBrowserIDScript().getText())
                        .setWindow(ScriptInjector.TOP_WINDOW)
                        .inject();
                callback.onSuccess(aVoid);
            }
        }).setWindow(ScriptInjector.TOP_WINDOW).inject();
    }

    public void init(BrowserIDCallback callback){
        BrowserID.callback = callback;
    }

    public static native void browserID(Element el, String verifier)/*-{
        var options = {
            onlogin : function(response){
                $wnd.console.log('Login response' + response);
                @org.gwtproject.thirdparty.social.browserid.client.BrowserID::callbackLogin(Lcom/google/gwt/core/client/JavaScriptObject;)(response);
            },
            onfail : function(response){
                $wnd.console.log('Fail response' + response);
                @org.gwtproject.thirdparty.social.browserid.client.BrowserID::callbackFail(Lcom/google/gwt/core/client/JavaScriptObject;)(response);
            },
            onlogout : function(response){
                $wnd.console.log('Logout response' + response);
                @org.gwtproject.thirdparty.social.browserid.client.BrowserID::callbackLogout(Lcom/google/gwt/core/client/JavaScriptObject;)(response);
            },
            server : verifier
        }
        $wnd.$(el).browserID(options);
    }-*/;

    @SuppressWarnings("unused")
    public static void callbackLogin(JavaScriptObject response){
        BrowserID.callback.onLogin();
    }

    @SuppressWarnings("unused")
    public static void callbackFail(JavaScriptObject response){
        BrowserID.callback.onFail();
    }

    @SuppressWarnings("unused")
    public static void callbackLogout(JavaScriptObject response){
        BrowserID.callback.onLogout();
    }
}
