package org.tommap.stdio.service;

public interface IEmailService {
    void sendEmail(String recipient, String username, Long ticketId);
}
