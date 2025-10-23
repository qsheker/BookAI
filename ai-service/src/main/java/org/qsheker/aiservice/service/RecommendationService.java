package org.qsheker.aiservice.service;

import org.qsheker.aiservice.clients.BookServiceClient;
import org.qsheker.aiservice.clients.UserServiceClient;
import org.qsheker.aiservice.web.dto.Book.Book;
import org.qsheker.aiservice.web.dto.User.User;
import org.qsheker.aiservice.web.dto.User.UserBookResponse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    private final UserServiceClient userServiceClient;
    private final BookServiceClient bookServiceClient;
    private final ChatModel chatModel;

    public RecommendationService(UserServiceClient userServiceClient,
                                 BookServiceClient bookServiceClient,
                                 ChatModel chatModel) {
        this.userServiceClient = userServiceClient;
        this.bookServiceClient = bookServiceClient;
        this.chatModel = chatModel;
    }

    public List<Book> getRecommendations(Long userId) {
        User user = userServiceClient.getUserById(userId);

        List<UserBookResponse> userBooks = userServiceClient.getUserBooks(userId);

        List<Long> userBookIds = userBooks.stream()
                .map(UserBookResponse::getBookId)
                .collect(Collectors.toList());

        List<Book> userBookDetails = bookServiceClient.getBooksBatch(userBookIds);

        List<Book> allBooks = bookServiceClient.getAllBooks();

        return generateAiRecommendations(user, userBookDetails, allBooks);
    }

    private List<Book> generateAiRecommendations(User user, List<Book> userBooks, List<Book> allBooks) {
        String promptText = buildPrompt(user, userBooks, allBooks);
        Prompt prompt = new Prompt(new UserMessage(promptText));
        ChatResponse response = chatModel.call(prompt);

        String aiResponse = response.getResult().getOutput().getText();
        List<Long> recommendedBookIds = parseAiResponse(aiResponse);

        return allBooks.stream()
                .filter(book -> recommendedBookIds.contains(book.getId()))
                .collect(Collectors.toList());
    }

    private String buildPrompt(User user, List<Book> userBooks, List<Book> allBooks) {
        StringBuilder prompt = new StringBuilder();

        prompt.append("You are a book recommendation expert. Recommend exactly 5 books for the user based on their reading history.\n\n");

        prompt.append("USER PROFILE:\n");
        prompt.append("Username: ").append(user.getUsername()).append("\n");
        prompt.append("Email: ").append(user.getEmail()).append("\n\n");

        prompt.append("USER'S CURRENT BOOKS:\n");
        if (userBooks.isEmpty()) {
            prompt.append("This user has no books yet. Recommend popular starter books.\n");
        } else {
            for (Book book : userBooks) {
                prompt.append("- '").append(book.getTitle())
                        .append("' by ").append(book.getAuthor())
                        .append(" | ").append(book.getDescription())
                        .append("\n");
            }
        }

        prompt.append("\nAVAILABLE BOOKS FOR RECOMMENDATION:\n");
        for (int i = 0; i < Math.min(allBooks.size(), 50); i++) {
            Book book = allBooks.get(i);
            prompt.append("ID: ").append(book.getId())
                    .append(" | Title: '").append(book.getTitle())
                    .append("' | Author: ").append(book.getAuthor())
                    .append(" | Description: ").append(book.getDescription())
                    .append("\n");
        }

        prompt.append("\nINSTRUCTIONS:\n");
        prompt.append("1. Analyze the user's reading preferences from their current books\n");
        prompt.append("2. Select exactly 5 book IDs from the available books that match their interests\n");
        prompt.append("3. Consider genre, writing style, and themes\n");
        prompt.append("4. Return ONLY the 5 book IDs as comma-separated numbers\n");
        prompt.append("5. Format: 12, 45, 78, 23, 56\n\n");
        prompt.append("RECOMMENDED BOOK IDs:");

        return prompt.toString();
    }

    private List<Long> parseAiResponse(String aiResponse) {
        try {
            String numbersOnly = aiResponse.replaceAll("[^0-9,]", "").trim();

            return List.of(numbersOnly.split(","))
                    .stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Long::valueOf)
                    .limit(5)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.err.println("Error parsing AI response: " + e.getMessage());
            return List.of(1L, 2L, 3L, 4L, 5L);
        }
    }
}