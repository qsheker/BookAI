package org.qsheker.aiservice.controller;

import org.qsheker.aiservice.service.RecommendationService;
import org.qsheker.aiservice.web.dto.Book.Book;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("api/v1/ai-service")
public class OllamaController {
    private final OllamaChatModel ollamaChatModel;
    private final RecommendationService recommendationService;

    public OllamaController(OllamaChatModel ollamaChatModel, RecommendationService recommendationService){
        this.ollamaChatModel = ollamaChatModel;
        this.recommendationService = recommendationService;
    }
    @GetMapping("/recommend/{userId}")
    public List<Book> recommend(@PathVariable("userId") Long id){
        return recommendationService.getRecommendations(id);
    }
    @GetMapping("/ask")
    public String ask(@RequestParam String question){
        return ollamaChatModel.call(question);
    }
    @GetMapping("/ask-stream")
    public Flux<String> askStream(@RequestParam String question){
        return ollamaChatModel.stream(question);
    }
}
