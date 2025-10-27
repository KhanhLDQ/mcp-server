package org.tommap.stdio.constant;

public class EmailTemplateConstants {
    public static final String CREATE_HELPDESK_TICKET_HTML_TEMPLATE = """
        <html>
        <body>
            <h2>Hi %s!</h2>
            <p>Your helpdesk ticket has been created successfully.</p>
            <p><strong>Ticket ID:</strong> %d</p>
            <p>Best regards,<br>Support Team</p>
        </body>
        </html>
        """;

    public static final String CREATE_HELPDESK_TICKET_TEXT_TEMPLATE = """
        Hi %s!
        
        Your helpdesk ticket has been created successfully.
        
        Ticket ID: %d
        
        Best regards,
        Support Team
        """;

    public static final String EMAIL_VERIFICATION_SUBJECT = "HELPDESK TICKET CONFIRMATION EMAIL";
}
