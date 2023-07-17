package org.dominokit.samples;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.layout.AppLayout;
import org.dominokit.domino.ui.menu.direction.DropDirection;
import org.dominokit.domino.ui.style.CssClass;
import org.dominokit.domino.ui.tabs.Tab;
import org.dominokit.domino.ui.tabs.TabsPanel;
import org.dominokit.domino.ui.themes.DominoThemeDark;
import org.dominokit.domino.ui.themes.DominoThemeLight;
import org.dominokit.domino.ui.themes.DominoThemeManager;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.ChildHandler;
import org.dominokit.domino.ui.utils.PostfixAddOn;
import org.dominokit.samples.customers.CustomersListPage;
import org.dominokit.samples.notes.NotesPage;
import org.dominokit.samples.pipelines.PipelinesPage;

import static elemental2.dom.DomGlobal.window;

public class Shell extends BaseDominoElement<HTMLDivElement, Shell> {

    public static final Shell instance = Shell.create();
    private CssClass crm_header_tabs = ()->"crm-header-tabs";

    private final AppLayout appLayout;

    private static Shell create() {
        return new Shell();
    }

    public Shell() {
        PipelinesPage pipelines = PipelinesPage.create();
        CustomersListPage customersListPage = CustomersListPage.create();
        this.appLayout = AppLayout.create("CRM")
                .setFixLeftDrawer(true)
                .withLeftDrawer((shell, drawer) -> drawer.appendChild(CrmMainMenu.create(shell)))
                .withNavBar((layout, navVar) -> {
                    navVar
                            .appendChild(PostfixAddOn.of(Icons.theme_light_dark()
                                    .setTooltip("Dark mode on/off", DropDirection.BEST_MIDDLE_SIDE)
                                    .clickable()
                                    .addClickListener(evt -> {
                                        if (DominoThemeDark.INSTANCE.isApplied()) {
                                            DominoThemeManager.INSTANCE.apply(DominoThemeLight.INSTANCE);
                                        } else {
                                            DominoThemeManager.INSTANCE.apply(DominoThemeDark.INSTANCE);
                                        }
                                    })
                            ))
                            .appendChild(PostfixAddOn.of(Icons.message_settings().clickable()))
                            .appendChild(PostfixAddOn.of(Icons.bell().clickable()))
                            .appendChild(PostfixAddOn.of(Icons.cog().clickable()))
                            .appendChild(PostfixAddOn.of(Icons.github()
                                            .addClickListener(evt -> {
                                                window.open("https://github.com/DominoKit/crm", "_blank");
                                            })
                                    .clickable()))
                            .withBody((parent1, navBody) -> navBody
                                    .addCss(dui_self_stretch, dui_flex, dui_justify_center, dui_items_center)
                                    .appendChild(TabsPanel.create()
                                            .addCss(crm_header_tabs)
                                            .withTabsContent((parent2, content) -> content.hide())
                                            .appendChild(Tab.create("Pipelines")
                                                    .addClickListener(evt -> layout.getContent().setContent(pipelines))
                                            )
                                            .appendChild(Tab.create("Customers")
                                                    .addClickListener(evt -> layout.getContent().setContent(customersListPage))
                                            )
                                            .appendChild(Tab.create("Reporting"))
                                            .appendChild(Tab.create("Configuration"))
                                    ))
                    ;
                })
                .withContent((parent, content) -> content
                        .setCssProperty("max-height", "calc(100vh - 64px)")
                        .appendChild(pipelines))
        ;
        init(this);
    }

    public Shell withLayout(ChildHandler<Shell, AppLayout> handler) {
        handler.apply(this, appLayout);
        return this;
    }

    @Override
    public HTMLDivElement element() {
        return appLayout.element();
    }
}