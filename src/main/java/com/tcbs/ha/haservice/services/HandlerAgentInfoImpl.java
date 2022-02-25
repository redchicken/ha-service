package com.tcbs.ha.haservice.services;

import com.tcbs.ha.haservice.dto.AgentNotifyInfo;
import com.tcbs.ha.haservice.dto.ServiceHaInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class HandlerAgentInfoImpl implements HandlerAgentInfo{

  @Override
  public ServiceHaInfo notifyStatus(AgentNotifyInfo agentNotifyInfo) {
    return ServiceHaInfo.builder().build();
  }
}
