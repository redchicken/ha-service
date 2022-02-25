package com.tcbs.ha.haservice.controller;

import com.tcbs.ha.haservice.dto.AgentNotifyInfo;
import com.tcbs.ha.haservice.dto.ServiceHaInfo;
import com.tcbs.ha.haservice.services.HandlerAgentInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
@RequestMapping("/ha/controller")
@Slf4j
@RequiredArgsConstructor
public class HaController {

  private final HandlerAgentInfo handlerAgentInfo;

  @PostMapping("/agent-status")
  public ResponseEntity<ServiceHaInfo> notifyStatus(@RequestBody AgentNotifyInfo notifyInfomation){
    return ResponseEntity.ok(handlerAgentInfo.notifyStatus(notifyInfomation));
  }
}
