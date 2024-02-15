package org.dominokit.samples.pipelines;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.badges.Badge;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.PostfixAddOn;
import org.dominokit.samples.Opportunity;
import org.dominokit.samples.RatingComponent;
import static org.dominokit.domino.ui.style.DominoCss.*;
import static org.dominokit.domino.ui.utils.Domino.div;
import static org.dominokit.domino.ui.utils.Domino.span;

public class OpportunityCard extends BaseDominoElement<HTMLDivElement, OpportunityCard> {

    private final Card root;
    private final Opportunity opportunity;

    public static OpportunityCard create(Opportunity opportunity) {
        return new OpportunityCard(opportunity);
    }

    public OpportunityCard(Opportunity opportunity) {
        this.opportunity = opportunity;
        this.root = Card.create(opportunity.getTitle())
                .addCss(dui_m_b_1, dui_rounded_md)
                .withHeader((parent, header) -> header
                        .withMainHeader((parent1, mainHeader) -> mainHeader.addCss(dui_gap_2))
                        .appendChild(PostfixAddOn.of(Badge.create(opportunity.getExpectedRevenue() + "$").addCss(dui_context, dui_rounded_full)))
                        .appendChild(PostfixAddOn.of(Icons.dots_vertical().clickable()))
                )
                .appendChild(div()
                        .addCss(dui_flex, dui_gap_1, dui_items_center, dui_m_b_4)
                        .appendChild(Icons.radiobox_marked().addCss(dui_fg_context, dui_font_size_4))
                        .appendChild(span().textContent(opportunity.getCustomerName()).addCss(dui_grow_1))
                )
                .appendChild(div()
                        .addCss(dui_flex, dui_gap_1, dui_items_center)
                        .appendChild(RatingComponent.create(opportunity.getPriority(), 5))
                        .appendChild(div().addCss(dui_grow_1))
                        .appendChild(Icons.bell_outline().clickable())
                        .appendChild(Icons.timer_outline().clickable())
                )
        ;
        init(this);
    }

    public Opportunity getOpportunity() {
        return opportunity;
    }

    @Override
    public HTMLDivElement element() {
        return root.element();
    }
}