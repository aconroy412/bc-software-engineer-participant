package com.northstar.crm.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  private final String secret;

  public JwtService(@Value("${northstar.security.jwt-secret}") String secret) {
    this.secret = secret;
  }

  public String issueToken(String subject, String role) {
    // TODO: build JWT or lab stub "lab."+subject+"."+role — do not log raw tokens
    String signature = Integer.toHexString(secret.hashCode());
    return "lab." + subject + "." + role + "." + signature;
  }

  public String parseSubject(String token) {
    // TODO: validate signature/expiry; return subject

    String[] splits = token.split("\\.");

    // check that there are 3 parts
    if (splits.length != 4) {
      throw new IllegalArgumentException("Invalid format");
    }

    // check that it is valid
    if (!splits[0].equals("lab")) {
      throw new IllegalArgumentException("Does not start with lab");
    }
    if (!validateSignature(splits[3])) {
      throw new IllegalArgumentException("Invalid key");
    }



    return splits[1];
  }

  public String parseRole(String token) {
    // TODO: return AGENT or ADMIN claim
    String[] splits = token.split("\\.");

    // check that there are 3 parts
    if (splits.length != 4) {
      throw new IllegalArgumentException("Invalid format");
    }

    // check that it is valid
    if (!splits[2].equals("ADMIN") && !splits[2].equals("AGENT")){
      throw new IllegalArgumentException("Invalid role");
    }

    if (!splits[0].equals("lab")) {
      throw new IllegalArgumentException("Does not start with lab");
    }
    if (!validateSignature(splits[3])) {
      throw new IllegalArgumentException("Invalid key");
    }

    return splits[2];
  }

  private boolean validateSignature(String signature) {
    
    String expectedSig = Integer.toHexString(secret.hashCode());

    return expectedSig.equals(signature);
  }
}
