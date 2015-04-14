package org.gwtproject.thirdparty.social.browserid.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.resources.client.ClientBundle.Source;
/**
 * @author <a href="mailto:kerbymart@gmail.com">Kerby Martino</a>
 * @version 0.0.1
 * @since 0.0.1
 */
public interface JsResources extends ClientBundle {

    public static final JsResources INSTANCE = GWT.create(JsResources.class);

    @Source("jquery.browserid.js")
    TextResource jqueryBrowserIDScript();
}
