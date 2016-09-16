package com.garryiv.air_tickets.core.notification.email;

import com.garryiv.air_tickets.core.notification.MessageProperties;
import com.garryiv.air_tickets.core.notification.queue.NotificationQueue;
import com.garryiv.air_tickets.core.notification.template.TemplateInterpolator;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class MessageBuilderServiceImplTest {

    public static final String RECIPIENT = "recipient@acme.com";
    public static final String ATTACHMENT_NAME = "invoice.pdf";
    public static final String SUBJECT = "subj";

    @Test
    public void withAttachment() {
        ArgumentCaptor<Email> emailCaptor = ArgumentCaptor.forClass(Email.class);

        NotificationQueue notificationQueue = mock(NotificationQueue.class);

        TemplateInterpolator contentInterpolator = mock(TemplateInterpolator.class);
        when(contentInterpolator.interpolate(anyString(), any())).thenReturn(new byte[0]);

        TemplateInterpolator attachmentInterpolator = mock(TemplateInterpolator.class);
        when(attachmentInterpolator.interpolate(anyString(), any())).thenReturn(new byte[0]);

        MessageProperties messageProperties = buildTestProperties();

        EmailBuilderService messageBuilderService = new EmailBuilderServiceImpl(
                messageProperties,
                notificationQueue,
                contentInterpolator,
                attachmentInterpolator);

        messageBuilderService.newEmail("test")
                .withRecipient(RECIPIENT)
                .withSubject(SUBJECT)
                .withContext("var1", "val1")
                .withContext("var2", "val2")
                .enqueue();

        verify(notificationQueue).add(emailCaptor.capture());
        Email email = emailCaptor.getValue();

        assertNotNull(email);
        assertEquals(RECIPIENT, email.getRecipient());
        assertEquals(SUBJECT, email.getSubject());
        assertNotNull(email.getBody());
        assertNotNull(email.getAttachments());
        assertEquals(1, email.getAttachments().size());
        EmailAttachment attachment = email.getAttachments().get(0);
        assertNotNull(attachment);
        assertEquals(ATTACHMENT_NAME, attachment.getName());
        assertNotNull(ATTACHMENT_NAME, attachment.getContent());
    }

    private MessageProperties buildTestProperties() {
        MessageProperties messageProperties = new MessageProperties();
        MessageProperties.EmailTemplate emailTemplate = new MessageProperties.EmailTemplate();
        emailTemplate.getAttachments().put(ATTACHMENT_NAME, "invoice-template.odt");
        messageProperties.getTemplates().put("test", emailTemplate);
        return messageProperties;
    }

}
