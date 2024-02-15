package org.dominokit.samples.pipelines;

import static org.dominokit.domino.ui.style.DominoCss.*;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.button.LinkButton;
import org.dominokit.domino.ui.dialogs.AbstractDialog;
import org.dominokit.domino.ui.dialogs.DialogSize;
import org.dominokit.domino.ui.forms.DoubleBox;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.forms.suggest.Select;
import org.dominokit.domino.ui.forms.suggest.SelectOption;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.layout.NavBar;
import org.dominokit.domino.ui.utils.FooterContent;
import org.dominokit.domino.ui.utils.PostfixAddOn;

public class CreateOpportunityDialog extends AbstractDialog<CreateOpportunityDialog> {

    public static CreateOpportunityDialog create() {
        return new CreateOpportunityDialog();
    }

    public CreateOpportunityDialog() {
        setStretchWidth(DialogSize.MEDIUM)
                .setStretchHeight(DialogSize.SMALL)
                .withHeader((dialog, header) ->
                        header.appendChild(NavBar.create("Create opportunity")
                                .addCss(dui_h_8, dui_p_0)
                                .appendChild(PostfixAddOn.of(
                                                Icons.close()
                                                        .addCss(dui_fg_red)
                                                        .clickable()
                                                        .addClickListener(evt -> dialog.close())
                                        )
                                )
                        )
                )
                .appendChild(TextBox.create("Title").setPlaceholder("Opportunity title"))
                .appendChild(Select.<String>create("Customer")
                        .setPlaceholder("Customer name")
                        .appendChild(SelectOption.create("Customer A","Customer A","Customer A" ))
                        .appendChild(SelectOption.create("Customer B","Customer B","Customer B" ))
                        .appendChild(SelectOption.create("Customer C","Customer C","Customer C" ))
                        .appendChild(SelectOption.create("Customer D","Customer D","Customer C" ))
                        .appendChild(SelectOption.create("Customer E","Customer E","Customer E" ))
                        .appendChild(SelectOption.create("Customer F","Customer F","Customer F" ))
                        .appendChild(SelectOption.create("Customer G","Customer G","Customer G" ))
                        .appendChild(SelectOption.create("Customer H","Customer H","Customer H" ))
                        .appendChild(SelectOption.create("Customer I","Customer I","Customer I" ))
                        .appendChild(SelectOption.create("Customer J","Customer J","Customer J" ))
                        .appendChild(SelectOption.create("Customer K","Customer K","Customer K" ))
                        .appendChild(SelectOption.create("Customer L","Customer L","Customer L" ))
                        .appendChild(SelectOption.create("Customer M","Customer M","Customer M" ))
                        .appendChild(SelectOption.create("Customer N","Customer N","Customer N" ))
                        .appendChild(SelectOption.create("Customer O","Customer O","Customer O" ))
                        .appendChild(SelectOption.create("Customer P","Customer P","Customer P" ))
                        .appendChild(SelectOption.create("Customer Q","Customer Q","Customer Q" ))
                        .appendChild(SelectOption.create("Customer R","Customer R","Customer R" ))
                        .appendChild(SelectOption.create("Customer S","Customer S","Customer S" ))
                        .appendChild(SelectOption.create("Customer T","Customer T","Customer T" ))
                        .appendChild(SelectOption.create("Customer U","Customer U","Customer U" ))
                        .appendChild(SelectOption.create("Customer V","Customer V","Customer V" ))
                        .appendChild(SelectOption.create("Customer W","Customer W","Customer W" ))
                        .appendChild(SelectOption.create("Customer X","Customer X","Customer X" ))
                        .appendChild(SelectOption.create("Customer Y","Customer Y","Customer Y" ))
                        .appendChild(SelectOption.create("Customer Z","Customer Z","Customer Z" ))
                )
                .appendChild(DoubleBox.create("Expected revenue").setPlaceholder("Expected revenue"))
                .appendChild(PrioritySelector.create(5))
                .withContentFooter((dialog, footer) -> {
                    footer.addCss(dui_flex, dui_gap_2, dui_justify_end);
                    dialog
                            .appendChild(FooterContent.of(LinkButton.create("CLOSE").addCss(dui_w_32).addClickListener(evt -> dialog.close())))
                            .appendChild(FooterContent.of(Button.create(Icons.cursor_default_click(), "Create").addCss(dui_w_32).addClickListener(evt -> dialog.close())));
                });
    }
}