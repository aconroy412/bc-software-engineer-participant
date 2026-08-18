package com.northstar.crm.model;

public record CustomerDraft(
        String fullName,
        String email,
        String status
) {}