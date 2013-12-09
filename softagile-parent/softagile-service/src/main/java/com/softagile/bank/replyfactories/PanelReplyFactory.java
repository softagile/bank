package com.softagile.bank.replyfactories;

import java.util.ArrayList;
import java.util.List;

import com.softagile.bank.domain.Panel;
import com.softagile.bank.requestreply.PanelReply;

public class PanelReplyFactory {

    public static PanelReply createPanelReply( List<Panel> panelists) {
        PanelReply panelReply = new PanelReply();
        List<Long> panelIds = new ArrayList<Long>();
        if (panelists.size() > 0) {
            for (Panel panel : panelists) {
                panelIds.add(panel.getId());
            }
        }
        panelReply.setPanelIds(panelIds);
        return panelReply;

    }
    
    
}
