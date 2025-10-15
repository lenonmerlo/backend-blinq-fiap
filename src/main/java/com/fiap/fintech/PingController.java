// src/main/java/com/fiap/fintech/PingController.java
package com.fiap.fintech;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PingController {
    @GetMapping("/ping")
    String ok() { return "ok"; }
}
