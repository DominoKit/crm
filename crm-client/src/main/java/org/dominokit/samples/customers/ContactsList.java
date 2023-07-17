package org.dominokit.samples.customers;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.datatable.ColumnConfig;
import org.dominokit.domino.ui.datatable.DataTable;
import org.dominokit.domino.ui.datatable.TableConfig;
import org.dominokit.domino.ui.datatable.plugins.pagination.SortPlugin;
import org.dominokit.domino.ui.datatable.plugins.selection.SelectionPlugin;
import org.dominokit.domino.ui.datatable.store.LocalListDataStore;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.samples.Contact;
import org.dominokit.samples.Customer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContactsList extends BaseDominoElement<HTMLDivElement, ContactsList> {

    private final DivElement root;
    private DataTable<Contact> datatable;

    public static ContactsList create() {
        return new ContactsList();
    }

    public ContactsList() {
        this.root = div().addCss(dui_w_full, dui_flex, dui_overflow_y_hidden, dui_relative, dui_rounded_br_md, dui_rounded_bl_md);

        TableConfig<Contact> tableConfig = new TableConfig<>();
        tableConfig
                .setStickyHeader(true)
                .setMultiSelect(true)
                .addColumn(ColumnConfig.<Contact>create("name", "Name")
                        .setSortable(true)
                        .setCellRenderer(cellInfo -> text(cellInfo.getRecord().getName()))
                )
                .addColumn(ColumnConfig.<Contact>create("nickName", "Nick name")
                        .setCellRenderer(cellInfo -> text(cellInfo.getRecord().getNickName()))
                )
                .addColumn(ColumnConfig.<Contact>create("age", "Age")
                        .setSortable(true)
                        .setCellRenderer(cellInfo -> text(String.valueOf(cellInfo.getRecord().getAge())))
                )
                .addColumn(ColumnConfig.<Contact>create("employee", "Employee")
                        .setSortable(true)
                        .setCellRenderer(cellInfo -> cellInfo.getRecord().isEmployee() ? Icons.check().element() : text())
                )
                .addPlugin(new SortPlugin<>())
                .addPlugin(new SelectionPlugin<>())
        ;
        LocalListDataStore<Contact> dataStore = new LocalListDataStore<>(customerList());
        datatable = new DataTable<>(tableConfig, dataStore)
                .addCss(dui_w_full, dui_overflow_y_auto, dui_max_h_full)
                .withTableHead((parent, thead) -> thead.addCss(dui_sticky, dui_top_0))
        ;
        dataStore.load();

        this.root.appendChild(datatable);

        init(this);
    }

    private List<Contact> customerList() {
        return IntStream.range(0, 100)
                .mapToObj(value -> Contact.of("Customer " + value, "Nick name " + value, 32, true))
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