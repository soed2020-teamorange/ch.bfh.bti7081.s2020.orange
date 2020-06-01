package ch.bfh.bti7081.s2020.orange.backend.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {

  public double calculate(long base, long power) {
    return Math.pow(base, power);
  }
  
}
