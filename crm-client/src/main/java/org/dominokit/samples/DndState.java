package org.dominokit.samples;

import org.dominokit.domino.ui.lists.ListItem;
import org.dominokit.samples.pipelines.OpportunityCard;
import org.dominokit.samples.pipelines.PipelineColumn;
import org.dominokit.samples.pipelines.PipelineList;
import static org.dominokit.domino.ui.utils.Domino.*;

public class DndState<S,P,D> {

    public static DndState<OpportunityCard, PipelineColumn, Opportunity> BOARD = new DndState<>();
    public static DndState<ListItem<Opportunity>, PipelineList, Opportunity> LIST = new DndState<>();

    private S source;
    private P parent;
    private D data;

    public void set(S source, P parent, D data){
        this.source = source;
        this.parent = parent;
        this.data = data;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public S getSource() {
        return source;
    }

    public void setSource(S source) {
        this.source = source;
    }

    public P getParent() {
        return parent;
    }

    public void setParent(P parent) {
        this.parent = parent;
    }
}
