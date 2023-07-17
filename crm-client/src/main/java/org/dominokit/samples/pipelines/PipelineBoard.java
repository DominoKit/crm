package org.dominokit.samples.pipelines;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.utils.BaseDominoElement;

public class PipelineBoard extends BaseDominoElement<HTMLDivElement, PipelineBoard> {

    private final DivElement root;

    public static PipelineBoard create() {
        return new PipelineBoard();
    }

    public PipelineBoard() {
        this.root = div()
                .addCss(dui_flex, dui_gap_1, dui_items_stretch, dui_overflow_x_auto)
                .appendChild(div()
                        .addCss(dui_flex, dui_flex_col, dui_gap_2, dui_p_4, dui_justify_start, dui_items_center, dui_order_last)
                        .appendChild(Icons.plus().addCss(dui_fg_green).clickable())
                        .appendChild(span().textContent("Add new column")
                                .setCssProperty("writing-mode", "vertical-rl")
                                .setCssProperty("text-orientation", "mixed")
                        )
                )
        ;
        init(this);
    }

    @Override
    public HTMLDivElement element() {
        return root.element();
    }
}