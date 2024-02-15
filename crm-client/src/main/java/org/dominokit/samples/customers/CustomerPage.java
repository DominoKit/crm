package org.dominokit.samples.customers;

import org.dominokit.domino.ui.badges.Badge;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.tabs.FillItem;
import org.dominokit.domino.ui.tabs.Tab;
import org.dominokit.domino.ui.tabs.TabsPanel;
import org.dominokit.domino.ui.utils.PostfixAddOn;
import org.dominokit.domino.ui.utils.PrefixAddOn;
import org.dominokit.samples.CrmPage;
import org.dominokit.samples.Customer;
import static org.dominokit.domino.ui.style.DominoCss.*;
import static org.dominokit.domino.ui.utils.Domino.div;
import static org.dominokit.domino.ui.utils.Domino.img;
import static org.dominokit.domino.ui.utils.Domino.span;

public class CustomerPage extends CrmPage<CustomerPage> {

    public static CustomerPage create(Customer customer) {
        return new CustomerPage(customer);
    }

    public CustomerPage(Customer customer) {
        super(customer.getName());
        withPageNavBar((parent, pageNavBar) -> pageNavBar
                .appendChild(PrefixAddOn.of(Button.create(Icons.account_edit(), "Edit")
                                .addCss(dui_min_w_24)
                        )
                )
                .appendChild(PostfixAddOn.of(Icons.account_multiple_check_outline().clickable()))
                .appendChild(PostfixAddOn.of(Icons.account_clock_outline().clickable()))
                .appendChild(PostfixAddOn.of(Icons.account_tag_outline().clickable()))
        );
        withContent((parent, content) -> content
                .addCss(dui_p_4, dui_flex, dui_items_stretch)
                .appendChild(div()
                        .addCss(dui_flex, dui_flex_col, dui_w_full, dui_gap_8, dui_bg_dominant_d_1, dui_p_4, dui_rounded_md)
                        .appendChild(div()
                                .addCss(dui_flex, dui_gap_4, dui_items_center)
                                .appendChild(img("images/person-placeholder.png")
                                        .addCss(dui_w_24, dui_h_24, dui_bg_dominant_d_1, dui_border, dui_border_dominant_d_3, dui_rounded_md)
                                )
                                .appendChild(span().textContent(customer.getName()).addCss(dui_font_size_12, dui_fg_color_2, dui_grow_1))
                                .appendChild(Badge.create("Meeting : 2").addCss(dui_rounded_full, dui_brown))
                                .appendChild(Badge.create("Invoiced amount : 14K").addCss(dui_rounded_full, dui_green))
                                .appendChild(Badge.create("Unpaid invoices : 4").addCss(dui_rounded_full, dui_warning))
                                .appendChild(Badge.create("Tickets : 7").addCss(dui_rounded_full, dui_blue))
                        )
                        .appendChild(TabsPanel.create()
                                .addCss(dui_w_full, dui_bg_dominant_d_2, dui_p_4, dui_rounded_md)
                                .withTabsContent((parent1, tabsContent) -> tabsContent.addCss(dui_flex, dui_max_h_full, dui_overflow_y_hidden, dui_p_0))
                                .appendChild(Tab.create(Icons.contacts(), "Contacts")
                                        .withContent((parent1, tabContent) -> tabContent.addCss( dui_flex, dui_overflow_y_hidden, dui_relative, dui_w_full))
                                        .addCss(dui_rounded_tr_md, dui_rounded_tl_md, dui_min_w_48)
                                        .appendChild(ContactsList.create())
                                        .activate()
                                )
                                .appendChild(Tab.create(Icons.note(), "Notes").addCss(dui_rounded_tr_md, dui_rounded_tl_md, dui_min_w_48))
                                .appendChild(Tab.create(Icons.receipt(), "Invoices").addCss(dui_rounded_tr_md, dui_rounded_tl_md, dui_min_w_48))
                                .appendChild(Tab.create(Icons.ticket(), "Support tickets").addCss(dui_rounded_tr_md, dui_rounded_tl_md, dui_min_w_48))

                        )
                )
        );
    }
}