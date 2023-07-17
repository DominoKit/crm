package org.dominokit.samples.pipelines;

import elemental2.dom.Element;
import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.badges.Badge;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.dnd.DragSource;
import org.dominokit.domino.ui.dnd.Draggable;
import org.dominokit.domino.ui.dnd.DropZone;
import org.dominokit.domino.ui.icons.lib.Icons;
import org.dominokit.domino.ui.style.Color;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.PostfixAddOn;
import org.dominokit.samples.DndState;

import java.util.ArrayList;
import java.util.List;

public class PipelineColumn extends BaseDominoElement<HTMLDivElement, PipelineColumn> {

    private final Card card;
    private DropZone dropZone;
    private DragSource dragSource;
    private Badge countBadge;
    private List<OpportunityCard> opportunities = new ArrayList<>();

    public static PipelineColumn create(String status, Color color) {
        return new PipelineColumn(status, color);
    }

    public PipelineColumn(String status, Color color) {
        this.dragSource = new DragSource();
        this.card = Card.create(status)
                .addCss(dui_elevation_0,
                        dui_w_72,
                        dui_bg_dominant_d_1,
                        dui_rounded_lg,
                        dui_border,
                        dui_border_solid,
                        dui_border_dominant_d_2,
                        color.getContextColor()
                )
                .withBody((parent, body) -> {
                    body.addCss(dui_p_1, dui_h_full, dui_overflow_y_auto);
                    dropZone = new DropZone();
                    dropZone.addDropTarget(body, draggableId -> {
                        DndState.BOARD.getParent().remove(DndState.BOARD.getSource());
                        appendChild(OpportunityCard.create(DndState.BOARD.getData()));
                    });
                })
                .withHeader((parent, header) -> header
                        .addCss(dui_bg_dominant_d_2)
                        .withMainHeader((parent1, mainHeader) -> mainHeader.addCss(dui_gap_3))
                        .withMainTitle((parent1, titleElement) -> titleElement.addCss(dui_text_ellipsis))
                        .appendChild(PostfixAddOn.of(countBadge = Badge.create("0").addCss(dui_rounded_full, dui_context)))
                        .appendChild(PostfixAddOn.of(Icons.cog().clickable()))
                        .appendChild(PostfixAddOn.of(Icons.plus().clickable()))
                )
        ;
        init(this);
    }

    public PipelineColumn appendChild(OpportunityCard opportunityCard){
        opportunities.add(opportunityCard);
        dragSource.addDraggable(Draggable.of(opportunityCard, card -> {
            DndState.BOARD.set(opportunityCard, this, opportunityCard.getOpportunity());
        }));
        countBadge.setText(String.valueOf(opportunities.size()));
        return super.appendChild(opportunityCard);
    }

    public PipelineColumn remove(OpportunityCard opportunityCard){
        if(this.opportunities.contains(opportunityCard)){
            opportunityCard.remove();
            this.opportunities.remove(opportunityCard);
            countBadge.setText(String.valueOf(opportunities.size()));
        }
        return this;
    }

    @Override
    public Element getAppendTarget() {
        return this.card.getAppendTarget();
    }

    @Override
    public HTMLDivElement element() {
        return card.element();
    }
}