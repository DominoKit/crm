package org.dominokit.samples.pipelines;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.icons.StateChangeMdiIcon;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.utils.BaseDominoElement;

import java.util.ArrayList;
import java.util.List;

public class PrioritySelector extends BaseDominoElement<HTMLDivElement, PrioritySelector> {

    private final DivElement root;
    private List<StateChangeMdiIcon> icons= new ArrayList<>();

    public static PrioritySelector create(int max) {
        return new PrioritySelector(max);
    }

    public PrioritySelector(int max) {
        this.root = div()
                .addCss(dui_flex, dui_gap_2, dui_items_center)
                .appendChild(span().textContent("Priority"))
        ;
        for(int i = 0; i<max; i++){
            final int indx = i;
            StateChangeMdiIcon icon = StateChangeMdiIcon.create(Icons.star_outline()
                            .addCss(dui_fg_amber, dui_font_size_6))
                    .withState("selected", Icons.star().addCss(dui_fg_amber, dui_font_size_6))
                    .withState("deselected", Icons.star_outline().addCss(dui_fg_amber, dui_font_size_6))
                    .addClickListener(evt -> {
                        for(int j=0;j<=indx; j++){
                            icons.get(j).setState("selected");
                        }
                        for(int j=indx+1;j<max; j++){
                            icons.get(j).setState("deselected");
                        }
                    });
            icons.add(icon);
            this.root.appendChild(icon);
        }

        init(this);
    }

    @Override
    public HTMLDivElement element() {
        return root.element();
    }
}