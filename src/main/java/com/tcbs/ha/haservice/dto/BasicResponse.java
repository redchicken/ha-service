package com.tcbs.ha.haservice.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BasicResponse {
  private String result;
  private String message;
}
