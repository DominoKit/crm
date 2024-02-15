package org.dominokit.samples.pipelines;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.badges.Badge;
import org.dominokit.domino.ui.dnd.DragSource;
import org.dominokit.domino.ui.dnd.Draggable;
import org.dominokit.domino.ui.dnd.DropZone;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.icons.ToggleMdiIcon;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.layout.NavBar;
import org.dominokit.domino.ui.lists.ListGroup;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.style.CssClass;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.PostfixAddOn;
import org.dominokit.samples.DndState;
import org.dominokit.samples.Opportunity;
import org.dominokit.samples.RatingComponent;
import static org.dominokit.domino.ui.style.DominoCss.*;
import static org.dominokit.domino.ui.utils.Domino.div;
import static org.dominokit.domino.ui.utils.Domino.span;

public class PipelineList extends BaseDominoElement<HTMLDivElement, PipelineList> {

    private static CssClass crm_list = () -> "crm-list";
    private final DivElement root;
    private ListGroup<Opportunity> list;
    private DropZone dropZone;
    private DragSource dragSource;
    private Badge countBadge;

    public static PipelineList create(String status, Color color) {
        return new PipelineList(status, color);
    }

    public PipelineList(String status, Color color) {
        this.dragSource = new DragSource();
        this.root = div()
                .addCss(dui_flex, dui_flex_col, dui_w_full, crm_list, dui_bg_dominant_d_1, dui_m_b_4, dui_p_4, dui_rounded_md, color.getContextColor())
                .appendChild(NavBar.create(status)
                        .addCss(dui_h_8)
                        .appendChild(PostfixAddOn.of(countBadge = Badge.create("0").addCss(dui_rounded_full, dui_context)))
                        .appendChild(PostfixAddOn.of(Icons.cog().clickable()))
                        .appendChild(PostfixAddOn.of(Icons.plus().clickable()))
                        .appendChild(PostfixAddOn.of(ToggleMdiIcon.create(Icons.chevron_up().clickable(), Icons.chevron_down().clickable())
                                        .toggleOnClick(true)
                                        .addClickListener(evt -> {
                                            if(list.isCollapsed()){
                                                list.expand();
                                            }else {
                                                list.collapse();
                                            }
                                        })
                                )
                        )
                )
                .appendChild(list =
                        ListGroup.<Opportunity>create()
//                                .setCollapseStrategy(new HeightCollapseStrategy())
                                .apply(listGroup -> {
                                    dropZone = new DropZone();
                                    dropZone.addDropTarget(listGroup, draggableId -> {
                                        LOGGER.info("Dropping on : "+status);
                                        DndState.LIST.getParent().remove(DndState.LIST.getData());
                                        addOpportunity(DndState.LIST.getData());
                                    });
                                })
                                .setBordered(false)
                                .setSelectable(false)
                                .addCss(dui_rounded_md, dui_min_h_12)
                                .setItemRenderer((listGroup, listItem) -> {
                                    Opportunity opportunity = listItem.getValue();
                                    listItem
                                            .addCss(dui_border_0, dui_m_b_1, dui_rounded_md)
                                            .appendChild(div()
                                                    .addCss(dui_flex, dui_gap_2, dui_h_12, dui_p_x_4, dui_items_center, dui_border, dui_border_l_2, dui_border_solid, dui_rounded_md, dui_border_context)
                                                    .appendChild(Icons.drag_vertical().addCss(dui_fg_color_3))
                                                    .appendChild(span().textContent(opportunity.getTitle()).addCss(dui_min_w_72, dui_max_w_72))
                                                    .appendChild(Icons.radiobox_marked().addCss(dui_fg_context, dui_font_size_4))
                                                    .appendChild(span().textContent(opportunity.getCustomerName()).addCss(dui_grow_1))
                                                    .appendChild(RatingComponent.create(opportunity.getPriority(), 5))
                                                    .appendChild(Icons.bell_outline().clickable())
                                                    .appendChild(Icons.timer_outline().clickable())
                                            );
                                    dragSource.addDraggable(Draggable.of(listItem, element -> {
                                        DndState.LIST.set(listItem, this, opportunity);
                                    }));
                                })
                );
        init(this);
    }

    public PipelineList addOpportunity(Opportunity opportunity) {
        list.addItem(opportunity);
        countBadge.setText(String.valueOf(list.getItems().size()));
        return this;
    }

    public PipelineList remove(Opportunity opportunity){
        list.removeItem(opportunity);
        countBadge.setText(String.valueOf(list.getItems().size()));
        return this;
    }

    @Override
    public HTMLDivElement element() {
        return root.element();
    }
}