package org.dominokit.samples;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.button.Button;
import org.dominokit.domino.ui.button.LinkButton;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.layout.NavBar;
import org.dominokit.domino.ui.search.SearchBox;
import org.dominokit.domino.ui.tree.Tree;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.ChildHandler;
import org.dominokit.domino.ui.utils.PostfixAddOn;
import org.dominokit.domino.ui.utils.PrefixAddOn;

public abstract class CrmPage<T extends CrmPage<T>> extends BaseDominoElement<HTMLDivElement, T> {

    private final Card card;
    private NavBar pageNavBar;
    private DivElement content;

    public CrmPage(String pageTitle) {
        this.card = Card.create(pageTitle)
                .addCss(dui_min_h_full, dui_max_h_full)
                .withIcon(Icons.view_grid_outline())
                .withHeader((parent, cardHeader) -> cardHeader.appendChild(PostfixAddOn.of(SearchBox.create().addCss(dui_w_72, dui_bg_dominant_d_1, dui_rounded_full, dui_fg_color_3))))
                .withBody((parent, body) -> body.addCss(dui_grow_1, dui_overflow_y_auto, dui_p_0))
                .withBody((parent, body) -> {
                    body
                            .addCss(dui_flex, dui_flex_col)
                            .appendChild(div()
                                    .addCss(dui_flex, dui_flex_col, dui_grow_1, dui_overflow_hidden)
                                    .appendChild(div()
                                            .addCss(dui_elevation_1)
                                            .appendChild(pageNavBar = NavBar.create()
                                                    .addCss(dui_h_14)
                                            )
                                    )
                                    .appendChild(content = div().addCss(dui_grow_1, dui_overflow_hidden))
                            );
                })
        ;
        init((T) this);
    }


    public T withCard(ChildHandler<T, Card> handler) {
        handler.apply((T) this, card);
        return (T) this;
    }

    public void withPageNavBar(ChildHandler<T, NavBar> handler) {
        handler.apply((T) this, pageNavBar);
    }

    public void withContent(ChildHandler<T, DivElement> handler) {
        handler.apply((T) this, content);
    }

    @Override
    public Element getAppendTarget() {
        return content.element();
    }

    @Override
    public HTMLDivElement element() {
        return card.element();
    }
}