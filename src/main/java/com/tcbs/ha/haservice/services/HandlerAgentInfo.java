package com.tcbs.ha.haservice.services;

import com.tcbs.ha.haservice.dto.AgentNotifyInfo;
import com.tcbs.ha.haservice.dto.ServiceHaInfo;

public interface HandlerAgentInfo {
  public ServiceHaInfo notifyStatus(AgentNotifyInfo agentNotifyInfo);
}
