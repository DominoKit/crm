package org.dominokit.samples.pipelines;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import static org.dominokit.domino.ui.style.DominoCss.*;
import static org.dominokit.domino.ui.utils.Domino.div;
import static org.dominokit.domino.ui.utils.Domino.span;

public class PipelineListGroup extends BaseDominoElement<HTMLDivElement, PipelineListGroup> {

    private final DivElement root;

    public static PipelineListGroup create() {
        return new PipelineListGroup();
    }

    public PipelineListGroup() {
        this.root = div()
                .addCss(dui_flex, dui_flex_col, dui_w_full, dui_overflow_y_auto, dui_max_h_full, dui_p_4)
                .appendChild(div()
                        .addCss(dui_flex, dui_flex_col, dui_gap_2, dui_p_4, dui_justify_center, dui_items_center, dui_order_last)
                        .appendChild(Icons.plus().addCss(dui_fg_green).clickable())
                        .appendChild(span().textContent("Add new list"))
                );
        init(this);
    }

    @Override
    public HTMLDivElement element() {
        return root.element();
    }
}