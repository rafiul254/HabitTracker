package com.habittracker.service;

import java.util.Random;

public class QuoteService {
    private static final String[] MOTIVATIONAL_QUOTES = {
            "Success is the sum of small efforts repeated day in and day out.",
            "You don't have to be great to start, but you have to start to be great.",
            "The secret of getting ahead is getting started.",
            "A journey of a thousand miles begins with a single step.",
            "Don't watch the clock; do what it does. Keep going.",
            "The only way to do great work is to love what you do.",
            "Believe you can and you're halfway there.",
            "Your limitation—it's only your imagination.",
            "Great things never come from comfort zones.",
            "Dream it. Wish it. Do it."
    };

    private static final Random random = new Random();

    public String getRandomQuote() {
        return MOTIVATIONAL_QUOTES[random.nextInt(MOTIVATIONAL_QUOTES.length)];
    }

    public String getDailyQuote() {
        return getRandomQuote();
    }
}
