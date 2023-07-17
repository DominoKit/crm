package org.dominokit.samples.customers;

import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.button.LinkButton;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.pagination.ScrollingPagination;
import org.dominokit.domino.ui.utils.PostfixAddOn;
import org.dominokit.domino.ui.utils.PrefixAddOn;
import org.dominokit.samples.CrmPage;
import org.dominokit.samples.pipelines.CreateOpportunityDialog;

public class CustomersListPage extends CrmPage<CustomersListPage> {

    private CustomersBoard customersBoard;
    private CustomersList customersList;

    public static CustomersListPage create() {
        return new CustomersListPage();
    }

    public CustomersListPage() {
        super("Customers");
        customersBoard = CustomersBoard.create();
        customersList = CustomersList.create();

        withPageNavBar((parent, pageNavBar) -> pageNavBar
                .appendChild(PrefixAddOn.of(Button.create(Icons.database_plus_outline(), "Create")
                                .addCss(dui_min_w_24)
                                .addClickListener(evt -> {
                                    CreateOpportunityDialog.create().open();
                                })
                        )
                )
                .appendChild(PrefixAddOn.of(LinkButton.create(Icons.database_import_outline(), "Import").addCss(dui_min_w_24)))
                .appendChild(PostfixAddOn.of(ScrollingPagination.create(500, 20, 5)
                                .withPageList((pager, pagesList) -> pagesList.addCss(dui_p_4px, dui_rounded_full, dui_bg_dominant_d_1))

                ))
                .appendChild(PostfixAddOn.of(Icons.reload()
                        .clickable()
                        .addClickListener(evt -> {
                            customersBoard.reload();
                            customersList.reload();
                        })
                ))
                .appendChild(PostfixAddOn.of(Icons.view_module()
                        .clickable()
                        .addClickListener(evt -> {
                            if(!customersBoard.isAttached()){
                                customersList.remove();
                                appendChild(customersBoard);
                            }
                        })
                ))
                .appendChild(PostfixAddOn.of(Icons.format_list_bulleted()
                        .clickable()
                        .addClickListener(evt -> {
                            if(!customersList.isAttached()){
                                customersBoard.remove();
                                appendChild(customersList);
                                customersList.reload();
                            }
                        })
                ))
        );
        withContent((parent, content) -> content
                .addCss(dui_p_4, dui_flex, dui_items_stretch)
                .appendChild(customersBoard)
        );

    }
}