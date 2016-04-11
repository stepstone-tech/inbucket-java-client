/*
Copyright 2016 StepStone Services

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.stepstone.inbucket;

import com.stepstone.inbucket.models.MailboxEntry;
import com.stepstone.inbucket.models.Message;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


public class InbucketClientTest {

    @Test
    public void deleteMailbox() throws IOException {
        InbucketClient client = new InbucketTestClient("http://test.wojtun.pl", 0);
        List<MailboxEntry> mailbox =  client.getMailbox("test");
        Assert.assertEquals(mailbox.size(), 2);
        client.deleteMailbox("test");
        mailbox = client.getMailbox("test");
        Assert.assertEquals(mailbox.size(), 0);

    }

    @Test
    public void deleteMessage() throws IOException {
        InbucketClient client = new InbucketTestClient("http://test.wojtun.pl", 0);
        List<MailboxEntry> mailbox =  client.getMailbox("test");
        Assert.assertEquals(mailbox.size(), 2);
        MailboxEntry message = mailbox.get(0);
        client.deleteMessage("test",message.id);
        mailbox = client.getMailbox("test");
        Assert.assertEquals(mailbox.size(), 1);

    }

    @Test
    public void getMessage() throws IOException {
        InbucketClient client = new InbucketTestClient("http://test.wojtun.pl", 0);
        List<MailboxEntry> mailbox =  client.getMailbox("test");
        MailboxEntry search = mailbox.get(0);
        Message message = client.getMessage("test", search.id);
        Assert.assertEquals(message.body.text,"This is a test mailing.\r\n\r\nThis should be clickable: http://google.com/\r\n");
    }

    @Test
    public void getMessageSource() throws IOException {
        InbucketClient client = new InbucketTestClient("http://test.wojtun.pl", 0);
        List<MailboxEntry> mailbox =  client.getMailbox("test");
        MailboxEntry search = mailbox.get(0);
        String source = client.getMessageSource("test", search.id);
        Assert.assertEquals(source,"mailSource");
    }


}
