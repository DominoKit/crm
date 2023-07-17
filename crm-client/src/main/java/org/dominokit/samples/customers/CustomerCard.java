package org.dominokit.samples.customers;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.icons.LabeledIcon;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.infoboxes.InfoBox;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.samples.Customer;

public class CustomerCard extends BaseDominoElement<HTMLDivElement, CustomerCard> {

    private final InfoBox infoBox;

    public static CustomerCard create(Customer customer) {
        return new CustomerCard(customer);
    }

    public CustomerCard(Customer customer) {
        this.infoBox = InfoBox.create(Icons.office_building_marker_outline())
                .addCss(dui_rounded_md, dui_bg_dominant_d_1)
                .withTitle((parent, title) -> {
                    title
                            .addCss(dui_fg_color_2)
                            .appendChild(LabeledIcon.create(Icons.email(), customer.getEmail()))
                            .appendChild(LabeledIcon.create(Icons.phone(), customer.getPhone()));
                })
                .withInfo((parent, info) -> info
                        .addCss(dui_fg_color_1)
                        .setTextContent(customer.getName()))
                .withIcon((parent, iconElement) -> iconElement.addCss(dui_fg_accent, dui_bg_dominant_d_1))
                .setFlipped(true)
                .setHoverEffect(InfoBox.HoverEffect.ZOOM);
        init(this);
    }

    @Override
    public HTMLDivElement element() {
        return infoBox.element();
    }
}