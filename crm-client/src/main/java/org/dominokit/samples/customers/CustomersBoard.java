package org.dominokit.samples.customers;

import static org.dominokit.domino.ui.utils.Domino.*;
import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.grid.AutoGrid;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.samples.Customer;
import org.dominokit.samples.Shell;
import static org.dominokit.domino.ui.style.DominoCss.*;

public class CustomersBoard extends BaseDominoElement<HTMLDivElement, CustomersBoard> {

    private AutoGrid autoGrid;
    private int count = 0;

    public static CustomersBoard create() {
        return new CustomersBoard();
    }

    public CustomersBoard() {
        this.autoGrid = AutoGrid.create().addCss(dui_overflow_y_auto);
        init(this);
        for (int i = count; i < 50; i++) {
            appendChild(createCustomerCard(i));
            count++;
        }
        autoGrid.addEventListener("scroll", evt -> {

            double gridHeight = autoGrid.element().getBoundingClientRect().height;
            double scrollHeight = autoGrid.element().scrollHeight;
            double diff = scrollHeight - gridHeight;
            double scrollTop = autoGrid.element().scrollTop;

            if (scrollTop == diff) {
                int limit= count+50;
                for (int i = count; i < limit; i++) {
                    appendChild(createCustomerCard(i));
                }
            }
        });

    }

    private CustomerCard createCustomerCard(int i) {
        Customer customer = Customer.of("Customer " + i, "0123456789", "customer." + i + "@mail.com");
        return CustomerCard.create(customer)
                .addClickListener(event -> Shell.instance.withLayout((parent, layout) -> {
                    layout.withContent((parent1, content) -> content.setContent(CustomerPage.create(customer)));
                }));
    }

    public CustomersBoard reload(){
        autoGrid.clearElement();
        count =0;
        for (int i = count; i < 50; i++) {
            appendChild(CustomerCard.create(Customer.of("Customer " + i, "0123456789", "customer." + i + "@mail.com")));
            count++;
        }
        autoGrid.element().scrollTop = 0;
        return this;
    }

    @Override
    public HTMLDivElement element() {
        return autoGrid.element();
    }
}