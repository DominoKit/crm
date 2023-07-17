package org.dominokit.samples;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.layout.AppLayout;
import org.dominokit.domino.ui.tree.Tree;
import org.dominokit.domino.ui.tree.TreeItem;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.ChildHandler;
import org.dominokit.samples.notes.NotesPage;

public class CrmMainMenu extends BaseDominoElement<HTMLDivElement, CrmMainMenu> {

    private final Tree<String> menu;
    private final AppLayout shell;

    public static CrmMainMenu create(AppLayout shell) {
        return new CrmMainMenu(shell);
    }

    public CrmMainMenu(AppLayout shell) {
        this.shell = shell;
        this.menu = Tree.<String>create("Main menu")
                .appendChild(TreeItem.<String>create(Icons.application_settings(), "CRM"))
                .appendChild(TreeItem.<String>create(Icons.note_edit(), "Notes")
                        .addClickListener(evt -> shell.withContent((parent, content) -> content.setContent(NotesPage.create()))))
                .appendChild(TreeItem.<String>create(Icons.cogs(), "Settings"))
        ;
        init(this);
    }

    public CrmMainMenu withMenu(ChildHandler<CrmMainMenu, Tree<String>> handler) {
        handler.apply(this, menu);
        return this;
    }

    @Override
    public HTMLDivElement element() {
        return menu.element();
    }
}