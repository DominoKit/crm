package org.dominokit.samples;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import static org.dominokit.domino.ui.style.DominoCss.*;
import static org.dominokit.domino.ui.utils.Domino.div;

public class RatingComponent extends BaseDominoElement<HTMLDivElement, RatingComponent> {

    private final DivElement root;

    public static RatingComponent create(int value, int max) {
        return new RatingComponent(value, max);
    }

    public RatingComponent(int value, int max) {
        this.root = div().addCss(dui_flex)
                .apply(self -> {
                    for (int i = 0; i < max; i++) {
                        if (i < value) {
                            self.appendChild(Icons.star().addCss(dui_fg_amber, dui_font_size_4));
                        } else {
                            self.appendChild(Icons.star_outline().addCss(dui_fg_amber, dui_font_size_4));
                        }
                    }
                })
        ;

        init(this);
    }

    @Override
    public HTMLDivElement element() {
        return root.element();
    }
}