package org.tommap.stdio.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tommap.stdio.exception.EmailSendingException;
import org.tommap.stdio.service.IEmailService;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;

import static org.tommap.stdio.constant.EmailTemplateConstants.CREATE_HELPDESK_TICKET_HTML_TEMPLATE;
import static org.tommap.stdio.constant.EmailTemplateConstants.CREATE_HELPDESK_TICKET_TEXT_TEMPLATE;
import static org.tommap.stdio.constant.EmailTemplateConstants.EMAIL_VERIFICATION_SUBJECT;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements IEmailService {
    @Value("${email.sender}")
    private String emailSender;

    private final SesClient sesClient;

    @Override
    public void sendEmail(String recipient, String username, Long ticketId) {
        String htmlContent = String.format(CREATE_HELPDESK_TICKET_HTML_TEMPLATE, username, ticketId);
        String textContent = String.format(CREATE_HELPDESK_TICKET_TEXT_TEMPLATE, username, ticketId);

        try {
            SendEmailRequest emailRequest = SendEmailRequest.builder()
                    .source(emailSender)
                    .destination(Destination.builder()
                            .toAddresses(recipient)
                            .build())
                    .message(Message.builder()
                            .subject(Content.builder()
                                    .data(EMAIL_VERIFICATION_SUBJECT)
                                    .charset("UTF-8")
                                    .build())
                            .body(Body.builder()
                                    .html(Content.builder()
                                            .data(htmlContent)
                                            .charset("UTF-8")
                                            .build())
                                    .text(Content.builder()
                                            .data(textContent)
                                            .charset("UTF-8")
                                            .build())
                                    .build())
                            .build())
                    .build();

            SendEmailResponse response = sesClient.sendEmail(emailRequest);
            log.info("Helpdesk ticket confirmation email sent to user: {} successfully - messageId: {}", username, response.messageId());
        } catch (Exception ex) {
            log.error("Failed to send email verification to user: {} - reason: {}", username, ex.getMessage());

            throw new EmailSendingException(ex.getMessage());
        }
    }
}
