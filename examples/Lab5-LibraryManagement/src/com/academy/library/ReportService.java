package com.academy.library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ReportService {

    private final LibraryService libraryService;

    public ReportService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void displaySummaryReport() {
        // TODO: compute totalBooks, borrowedBooks, availableBooks, totalMembers
        int totalBooks = libraryService.getBooks().size();
        int borrowedBooks = libraryService.getBorrowRecords().size();
        int availableBooks = totalBooks - borrowedBooks;
        int totalMembers = libraryService.getMembers().size();

        // TODO: findMostPopularCategory(); print Reports block matching solution format
        String mostPopular = findMostPopularCategory();

        System.out.printf("Reports%nBooks : %d%nBorrowed : %d%nAvailable : %d%nMembers : %d%n Most Popular Category : %s%n",
            totalBooks, borrowedBooks, availableBooks, totalMembers, mostPopular
        );
        // throw new UnsupportedOperationException("TODO");
    }

    public Path exportReportToFile(String fileName) throws IOException {
        // TODO: build same summary + category breakdown; Files.writeString; return Path
        throw new UnsupportedOperationException("TODO");

    }

    private String findMostPopularCategory() {
        // TODO: max entry by value from getCategoryBookCount(); orElse "N/A"
        String mostPopular = "N/A";


        Map<String, Integer> counts = libraryService.getCategoryBookCount();

        int maxCount = -1;

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostPopular = entry.getKey();
            }
        }

        return mostPopular;
    }
}
