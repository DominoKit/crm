package org.dominokit.samples;

import com.google.gwt.core.client.EntryPoint;
import org.dominokit.domino.ui.style.DominoCss;
import org.dominokit.domino.ui.themes.DominoThemeManager;
import org.dominokit.domino.ui.utils.ElementsFactory;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class App implements EntryPoint, ElementsFactory, DominoCss {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        DominoThemeManager.INSTANCE.applyUserThemes();
        body().appendChild(Shell.instance);

    }
}
