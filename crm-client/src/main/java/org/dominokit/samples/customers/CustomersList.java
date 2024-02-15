package org.dominokit.samples.customers;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.datatable.ColumnConfig;
import org.dominokit.domino.ui.datatable.DataTable;
import org.dominokit.domino.ui.datatable.TableConfig;
import org.dominokit.domino.ui.datatable.plugins.pagination.SortPlugin;
import org.dominokit.domino.ui.datatable.plugins.selection.SelectionPlugin;
import org.dominokit.domino.ui.datatable.store.LocalListDataStore;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.forms.TextBox;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.samples.Customer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.dominokit.domino.ui.style.DominoCss.*;
import static org.dominokit.domino.ui.utils.Domino.div;
import static org.dominokit.domino.ui.utils.Domino.text;

public class CustomersList extends BaseDominoElement<HTMLDivElement, CustomersList> {

    private final DivElement root;
    private DataTable<Customer> datatable;

    public static CustomersList create() {
        return new CustomersList();
    }

    public CustomersList() {
        this.root = div().addCss(dui_w_full, dui_overflow_y_hidden, dui_relative);

        TableConfig<Customer> tableConfig = new TableConfig<>();
        tableConfig
                .setStickyHeader(true)
                .setMultiSelect(true)
                .addColumn(ColumnConfig.<Customer>create("name", "Name")
                        .setSortable(true)
                        .setCellRenderer(cellInfo -> text(cellInfo.getRecord().getName()))
                )
                .addColumn(ColumnConfig.<Customer>create("phone", "Phone")
                        .setCellRenderer(cellInfo -> text(cellInfo.getRecord().getPhone()))
                )
                .addColumn(ColumnConfig.<Customer>create("email", "Email")
                        .setSortable(true)
                        .setCellRenderer(cellInfo -> text(cellInfo.getRecord().getEmail()))
                )
                .addPlugin(new SortPlugin<>())
                .addPlugin(new SelectionPlugin<>())
                ;
        LocalListDataStore<Customer> dataStore= new LocalListDataStore<>(customerList());
        datatable = new DataTable<>(tableConfig, dataStore)
                .addCss(dui_w_full, dui_overflow_y_auto, dui_max_h_full)
                .withTableHead((parent, thead) -> thead.addCss(dui_sticky, dui_top_0))
        ;

        this.root.appendChild(datatable);

        init(this);
    }

    private List<Customer> customerList(){
        return IntStream.range(0, 100)
                .mapToObj(value -> Customer.of("Customer "+value, "0123456789", "customer."+value+"@mail.com"))
                .collect(Collectors.toList());
    }

    @Override
    public HTMLDivElement element() {
        return root.element();
    }

    public void reload() {
        datatable.getDataStore().load();
    }
}