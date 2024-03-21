package org.dominokit.samples.notes;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.button.LinkButton;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.richtext.RichTextEditor;
import org.dominokit.domino.ui.utils.PostfixAddOn;
import org.dominokit.domino.ui.utils.PrefixAddOn;
import org.dominokit.samples.CrmPage;
import static org.dominokit.domino.ui.style.DominoCss.*;

public class NotesPage extends CrmPage<NotesPage> {

    private RichTextEditor richTextEditor;

    public static NotesPage create() {
        return new NotesPage();
    }

    public NotesPage() {
        super("Notes");
        withPageNavBar((parent, pageNavBar) -> pageNavBar
                .appendChild(PrefixAddOn.of(Button.create(Icons.database_plus_outline(), "Create")
                                .addCss(dui_min_w_24)
                        )
                )
                .appendChild(PrefixAddOn.of(LinkButton.create(Icons.database_import_outline(), "Import").addCss(dui_min_w_24)))
                .appendChild(PostfixAddOn.of(Icons.view_column()
                        .clickable()
                ))
                .appendChild(PostfixAddOn.of(Icons.format_list_bulleted()
                        .clickable()
                ))
                .appendChild(PostfixAddOn.of(Icons.chart_bar().clickable()))
        );
        withContent((parent, content) -> content.addCss(dui_p_4, dui_flex, dui_items_stretch));
        appendChild(richTextEditor = RichTextEditor.create())
        ;
    }
}
